resource "aws_launch_template" "venus-backend-8080" {
  name_prefix   = "venus-backend-8080"
  image_id      = "ami-0ca285d4c2cda3300"
  instance_type = "t3a.medium"
  user_data     = filebase64("backend-launch.sh")
  network_interfaces {
    associate_public_ip_address = false
    security_groups             = [aws_security_group.venus-vpc-sg.id]
  }
  update_default_version = true
}
resource "aws_autoscaling_group" "venus-backend-asg" {
  vpc_zone_identifier       = [aws_subnet.venus-vpc-pvt-2a.id, aws_subnet.venus-vpc-pvt-2b.id]
  name                      = "venus-backend-asg"
  max_size                  = 3
  min_size                  = 1
  health_check_grace_period = 180
  health_check_type         = "ELB"
  force_delete              = true
  termination_policies      = ["OldestInstance"]
  launch_template {
    id      = aws_launch_template.venus-backend-8080.id
    version = "$Default"
  }
  target_group_arns = [aws_lb_target_group.venus-backend-tg-8080.arn]
}
resource "aws_autoscaling_policy" "venus-backend-asp" {
  name                   = "venus-backend-asp"
  autoscaling_group_name = aws_autoscaling_group.venus-backend-asg.name
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

resource "aws_lb" "venus-backend-lb" {
  name               = "venus-backend-lb"
  internal           = false
  load_balancer_type = "network"
  subnets            = [aws_subnet.venus-vpc-pb-2a.id, aws_subnet.venus-vpc-pb-2b.id]
}
resource "aws_lb_listener" "venus-backend-lb-listener-8080" {
  load_balancer_arn = aws_lb.venus-backend-lb.arn
  port              = "80"
  protocol          = "TCP"
  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.venus-backend-tg-8080.arn
  }
}
resource "aws_lb_target_group" "venus-backend-tg-8080" {
  name     = "venus-backend-tg-8080"
  port     = 8080
  protocol = "TCP"
  vpc_id   = aws_vpc.venus-vpc.id
}
