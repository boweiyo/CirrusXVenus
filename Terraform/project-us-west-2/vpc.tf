
resource "aws_vpc" "venus-vpc" {
  cidr_block           = "10.0.0.0/16"
  enable_dns_support   = true
  enable_dns_hostnames = true
  tags = {
    Name = "venus-vpc"
  }
}

resource "aws_internet_gateway" "venus-vpc-igw" {
  vpc_id = aws_vpc.venus-vpc.id
  tags = {
    Name = "venus-vpc-igw"
  }
}

resource "aws_route_table" "venus-vpc-rt-pb1" {

  vpc_id = aws_vpc.venus-vpc.id
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.venus-vpc-igw.id
  }
  tags = {
    Name = "venus-vpc-rt-pb1"
  }
}

resource "aws_route_table" "venus-vpc-rt-pb2" {

  vpc_id = aws_vpc.venus-vpc.id
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.venus-vpc-igw.id
  }
  tags = {
    Name = "venus-vpc-rt-pb2"
  }
}
