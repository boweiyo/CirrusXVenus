# data "aws_vpc" "bowei-vpc" {
#   id = "vpc-083d4965e27af431a"
# }

# data "aws_subnet_ids" "bowei-frontend-subnets" {
#   vpc_id = aws_vpc.bowei-vpc.id
# }

# data "aws_availability_zone" "bowei" {
#   availability_zone = "ap-southeast-1a"
# }

# data "aws_subnet" "bowei-frontend-subnet" {
#   for_each = data.aws_subnet_ids.bowei-frontend-subnets.ids
#   id       = each.value
# }

resource "aws_launch_template" "bowei-frontend-80" {
  name_prefix = "bowei-frontend-80"
  # us-west-2
  # image_id      = "ami-02b92c281a4d3dc79"
  # ap-southeast-1
  image_id      = "ami-0ca285d4c2cda3300"
  instance_type = "t3a.medium"
  user_data     = filebase64("frontend-launch.sh")
  # vpc_security_group_ids = [aws_security_group.bowei-vpc-sg.id]
  network_interfaces {
    associate_public_ip_address = false
  }
  update_default_version = true
}
resource "aws_autoscaling_group" "bowei-frontend-asg" {
  # availability_zones = ["us-west-2"]
  # availability_zones        = ["ap-southeast-1a", "ap-southeast-1b"]
  vpc_zone_identifier       = [aws_subnet.bowei-vpc-pb-2a.id, aws_subnet.bowei-vpc-pb-2b.id]
  name                      = "bowei-frontend-asg"
  max_size                  = 3
  min_size                  = 1
  health_check_grace_period = 180
  health_check_type         = "ELB"
  force_delete              = true
  termination_policies      = ["OldestInstance"]
  launch_template {
    id      = aws_launch_template.bowei-frontend-80.id
    version = "$Default"
  }
  target_group_arns = [aws_lb_target_group.bowei-frontend-tg-80.arn]
}
resource "aws_autoscaling_policy" "bowei-frontend-asp" {
  name                   = "bowei-frontend-asp"
  autoscaling_group_name = aws_autoscaling_group.bowei-frontend-asg.name
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

resource "aws_lb" "bowei-frontend-lb" {
  name               = "bowei-frontend-lb"
  internal           = false
  load_balancer_type = "network"
  # subnets            = [for subnet in data.aws_subnet.bowei-frontend-subnet : subnet.id]
  # subnets = [for subnet in data.aws_subnet.test : subnet.id]
  #   subnets = ["subnet-0469d11c433129499"]
  subnets = [aws_subnet.bowei-vpc-pb-2a.id, aws_subnet.bowei-vpc-pb-2b.id]
}
resource "aws_lb_listener" "bowei-frontend-lb-listener-80" {
  load_balancer_arn = aws_lb.bowei-frontend-lb.arn
  port              = "80"
  protocol          = "TCP"
  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.bowei-frontend-tg-80.arn
  }
}
resource "aws_lb_target_group" "bowei-frontend-tg-80" {
  name     = "bowei-frontend-tg-80"
  port     = 80
  protocol = "TCP"
  vpc_id   = aws_vpc.bowei-vpc.id
}
