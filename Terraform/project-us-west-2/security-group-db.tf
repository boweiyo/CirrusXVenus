resource "aws_security_group" "db-sg" {
  vpc_id = aws_vpc.bowei-vpc.id
  ingress {
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    description = "Allow traffic to DB"
    cidr_blocks = [aws_vpc.bowei-vpc.cidr_block]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
  tags = {
    Name = "bowei-db-sg"
  }
}
