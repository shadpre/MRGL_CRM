USE master
DROP DATABASE IF EXISTS MrglCRM 

CREATE DATABASE MrglCRM
GO
USE MrglCRM 

CREATE TABLE Users(
	[Id] INT PRIMARY KEY IDENTITY(1,1),
	[LoginName] NVARCHAR(5) NOT NULL,
	[FirstName] NVARCHAR(40) NOT NULL,
    [LastName] NVARCHAR(40) NOT NULL,
	[EMail] NVARCHAR(40) NOT NULL,
	[Hash] NVARCHAR(255) NOT NULL,
	[Role] TINYINT NOT NULL
);


CREATE TABLE Customers(
	[Id] INT PRIMARY KEY IDENTITY(1,1),
	[Name] NVARCHAR(60) NOT NULL,
	[Address1] NVARCHAR(60) NOT NULL,
	[Address2] NVARCHAR(60) NOT NULL,
	[Address3] NVARCHAR(60) NOT NULL,
	[Zipcode] NVARCHAR(20) NOT NULL,
	[Country] NVARCHAR(40) NOT NULL,
	[Phone] NVARCHAR(20) NOT NULL,
	[Category] NVARCHAR(20) NOT NULL,
	[TaxNo] NVARCHAR(20) NOT NULL
);

CREATE TABLE Orders(
	[Id] INT PRIMARY KEY IDENTITY(1,1),
	[OrderDate] DATETIME NOT NULL,
	[DeliveryDate] DATETIME NOT NULL,
	[OrderDescription] NVARCHAR(255) NOT NULL,
	[OrderRemarks] NVARCHAR(MAX) NOT NULL,
	[OrderStatus] TINYINT NOT NULL,
	[CustomerId] INT FOREIGN KEY REFERENCES Customers(Id)
);

CREATE TABLE Images(
	[Id] INT PRIMARY KEY IDENTITY(1,1),
	[OrderId] INT FOREIGN KEY REFERENCES Orders(Id),
	[UserId] INT FOREIGN KEY REFERENCES Users(ID),
	[Tittle] NVARCHAR(40) NOT NULL,
	[Description] NVARCHAR(MAX) NOT NULL,
	[ImageData] VARBINARY(MAX) NOT NULL,
	[ImageType] TINYINT NOT NULL
);

CREATE TABLE ShortComments(
	[Id] INT PRIMARY KEY IDENTITY(1,1),
	[OrderId] INT FOREIGN KEY REFERENCES Orders(Id),
	[UserId] INT FOREIGN KEY REFERENCES Users(ID),
	[Tittle] NVARCHAR(40) NOT NULL,
	[Description] NVARCHAR(255) NOT NULL,
);

CREATE TABLE LongComments(
	[Id] INT PRIMARY KEY IDENTITY(1,1),
	[OrderId] INT FOREIGN KEY REFERENCES Orders(Id),
	[UserId] INT FOREIGN KEY REFERENCES Users(ID),
	[Tittle] NVARCHAR(40) NOT NULL,
	[Description] NVARCHAR(MAX) NOT NULL,
);

-- Network

-- Devices
