resource "aws_launch_template" "venus-frontend-80" {
  name_prefix   = "venus-frontend-80"
  image_id      = "ami-0ca285d4c2cda3300"
  instance_type = "t3a.medium"
  user_data     = filebase64("frontend-launch.sh")
  # vpc_security_group_ids = [aws_security_group.venus-vpc-sg.id]
  network_interfaces {
    associate_public_ip_address = false
  }
  update_default_version = true
}
resource "aws_autoscaling_group" "venus-frontend-asg" {
  vpc_zone_identifier       = [aws_subnet.venus-vpc-pb-2a.id, aws_subnet.venus-vpc-pb-2b.id]
  name                      = "venus-frontend-asg"
  max_size                  = 3
  min_size                  = 1
  health_check_grace_period = 180
  health_check_type         = "ELB"
  force_delete              = true
  termination_policies      = ["OldestInstance"]
  launch_template {
    id      = aws_launch_template.venus-frontend-80.id
    version = "$Default"
  }
  target_group_arns = [aws_lb_target_group.venus-frontend-tg-80.arn]
}
resource "aws_autoscaling_policy" "venus-frontend-asp" {
  name                   = "venus-frontend-asp"
  autoscaling_group_name = aws_autoscaling_group.venus-frontend-asg.name
  policy_type            = "PredictiveScaling"
  predictive_scaling_configuration {
    metric_specification {
      target_value = 10
      predefined_load_metric_specification {
        predefined_metric_type = "ASGTotalCPUUtilization"
        resource_label         = "test"
      }
      customized_scaling_metric_specification {
        metric_data_queries {
          id = "scaling"
          metric_stat {
            metric {
              metric_name = "CPUUtilization"
              namespace   = "AWS/EC2"
              dimensions {
                name  = "AutoScalingGroupName"
                value = "test"
              }
            }
            stat = "Average"
          }
        }
      }
    }
  }
}

resource "aws_lb" "venus-frontend-lb" {
  name               = "venus-frontend-lb"
  internal           = false
  load_balancer_type = "network"
  subnets            = [aws_subnet.venus-vpc-pb-2a.id, aws_subnet.venus-vpc-pb-2b.id]
}
resource "aws_lb_listener" "venus-frontend-lb-listener-80" {
  load_balancer_arn = aws_lb.venus-frontend-lb.arn
  port              = "80"
  protocol          = "TCP"
  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.venus-frontend-tg-80.arn
  }
}
resource "aws_lb_target_group" "venus-frontend-tg-80" {
  name     = "venus-frontend-tg-80"
  port     = 80
  protocol = "TCP"
  vpc_id   = aws_vpc.venus-vpc.id
}

//listener for TLS:443
data "aws_acm_certificate" "acm-certificate" {
  domain_name = "venus.cloudtech-training.com"
}

resource "aws_lb_listener" "venus-lb-ssl-listener" {
  load_balancer_arn = aws_lb.venus-frontend-lb.arn
  port              = "443"
  protocol          = "TLS"
  ssl_policy        = "ELBSecurityPolicy-TLS13-1-2-2021-06"
  certificate_arn   = data.aws_acm_certificate.acm-certificate.arn
  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.venus-frontend-tg-80.arn
  }
}
