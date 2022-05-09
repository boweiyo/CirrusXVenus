
resource "aws_vpc" "bowei-vpc" {
  cidr_block           = "10.0.0.0/16"
  enable_dns_support   = true
  enable_dns_hostnames = true
  tags = {
    Name = "bowei-vpc"
  }
}

resource "aws_internet_gateway" "bowei-vpc-igw" {
  vpc_id = aws_vpc.bowei-vpc.id
  tags = {
    Name = "bowei-vpc-igw"
  }
}

resource "aws_route_table" "bowei-vpc-rt-pb1" {

  vpc_id = aws_vpc.bowei-vpc.id
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.bowei-vpc-igw.id
  }
  tags = {
    Name = "bowei-vpc-rt-pb1"
  }
}

resource "aws_route_table" "bowei-vpc-rt-pb2" {

  vpc_id = aws_vpc.bowei-vpc.id
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.bowei-vpc-igw.id
  }
  tags = {
    Name = "bowei-vpc-rt-pb2"
  }
}
