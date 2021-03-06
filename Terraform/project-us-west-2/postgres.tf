resource "aws_db_subnet_group" "db-subnet-grp" {
  subnet_ids = [aws_subnet.venus-vpc-pvt-2a.id, aws_subnet.venus-vpc-pvt-2b.id]
}

resource "aws_db_instance" "postgres" {
  allocated_storage      = 20
  storage_type           = "gp2"
  engine                 = "postgres"
  instance_class         = "db.t3.micro"
  identifier             = "venus-rds"
  db_name                = "smartbankapp"
  username               = "postgres"
  password               = "postgres"
  db_subnet_group_name   = aws_db_subnet_group.db-subnet-grp.name
  vpc_security_group_ids = [aws_security_group.db-sg.id]
  skip_final_snapshot    = "true"
  multi_az               = "true"
}
