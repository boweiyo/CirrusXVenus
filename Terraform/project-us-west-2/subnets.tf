resource "aws_subnet" "venus-vpc-pb-2a" {
  vpc_id                  = aws_vpc.venus-vpc.id
  cidr_block              = "10.0.1.0/24"
  availability_zone       = "us-west-2a"
  map_public_ip_on_launch = true

  tags = {
    Name = "venus-vpc-pb-2a"
  }
}
resource "aws_route_table_association" "pb-2a" {
  subnet_id      = aws_subnet.venus-vpc-pb-2a.id
  route_table_id = aws_route_table.venus-vpc-rt-pb1.id
}

resource "aws_subnet" "venus-vpc-pb-2b" {
  vpc_id                  = aws_vpc.venus-vpc.id
  cidr_block              = "10.0.3.0/24"
  availability_zone       = "us-west-2b"
  map_public_ip_on_launch = true

  tags = {
    Name = "venus-vpc-pb-2b"
  }
}
resource "aws_route_table_association" "pb-2b" {
  subnet_id      = aws_subnet.venus-vpc-pb-2b.id
  route_table_id = aws_route_table.venus-vpc-rt-pb2.id
}

resource "aws_eip" "nat_gateway" {
  vpc = true
}
resource "aws_nat_gateway" "venus-vpc-nat-gw" {
  allocation_id = aws_eip.nat_gateway.id
  subnet_id     = aws_subnet.venus-vpc-pb-2a.id

  tags = {
    Name = "venus-vpc-nat-gw-1"
  }
  depends_on = [aws_internet_gateway.venus-vpc-igw]
}

resource "aws_route_table" "venus-vpc-rt-pvt-1" {
  vpc_id = aws_vpc.venus-vpc.id
  route {
    cidr_block     = "0.0.0.0/0"
    nat_gateway_id = aws_nat_gateway.venus-vpc-nat-gw.id
  }
}

resource "aws_subnet" "venus-vpc-pvt-2a" {
  vpc_id            = aws_vpc.venus-vpc.id
  cidr_block        = "10.0.2.0/24"
  availability_zone = "us-west-2a"
  tags = {
    Name = "venus-vpc-pvt-2a"
  }
}
resource "aws_route_table_association" "pvt-2a" {
  subnet_id      = aws_subnet.venus-vpc-pvt-2a.id
  route_table_id = aws_route_table.venus-vpc-rt-pvt-1.id
}

resource "aws_route_table" "venus-vpc-rt-pvt-2" {
  vpc_id = aws_vpc.venus-vpc.id
  route {
    cidr_block     = "0.0.0.0/0"
    nat_gateway_id = aws_nat_gateway.venus-vpc-nat-gw.id
  }
}

resource "aws_subnet" "venus-vpc-pvt-2b" {
  vpc_id            = aws_vpc.venus-vpc.id
  cidr_block        = "10.0.4.0/24"
  availability_zone = "us-west-2b"
  tags = {
    Name = "venus-vpc-pvt-2b"
  }
}
resource "aws_route_table_association" "pvt-2b" {
  subnet_id      = aws_subnet.venus-vpc-pvt-2b.id
  route_table_id = aws_route_table.venus-vpc-rt-pvt-2.id
}
