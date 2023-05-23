USE master
DROP DATABASE IF EXISTS Mrgl_CRM

CREATE DATABASE Mrgl_CRM
GO
USE Mrgl_CRM

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
	[City] NVARCHAR(60) NOT NULL,
	[Country] NVARCHAR(40) NOT NULL,
	[Phone] NVARCHAR(20) NOT NULL,
	[Category] NVARCHAR(20) NOT NULL,
	[TaxNo] NVARCHAR(20) NOT NULL
);

CREATE TABLE CustomerTasks(
	[Id] INT PRIMARY KEY IDENTITY(1,1),
	[Date] DATETIME NOT NULL,
	[Description] NVARCHAR(100) NULL,
	[Remarks] NVARCHAR(255) NULL,
	[Status] TINYINT NOT NULL,
	[CustomerId] INT FOREIGN KEY REFERENCES Customers(Id)
);

CREATE TABLE UserCustomerTasksRel(
	[Id] INT PRIMARY KEY IDENTITY(1,1),
	[UserId] INT FOREIGN KEY REFERENCES Users(Id),
	[CustomerTaskId] INT FOREIGN KEY REFERENCES CustomerTasks(Id)
);



CREATE TABLE Installations(
	[Id] INT PRIMARY KEY IDENTITY(1,1),
	[CustomerTasksId] INT FOREIGN KEY REFERENCES CustomerTasks(Id),
	[Description] NVARCHAR(100) NOT NULL,
	[Remarks] NVARCHAR(255) NOT NULL
);

CREATE TABLE Devices(
	[Id] INT PRIMARY KEY IDENTITY(1,1),
	[InstallationId] INT FOREIGN KEY REFERENCES Installations(Id),
	[Description] NVARCHAR(100) NOT NULL,
	[Remarks] NVARCHAR(255) NOT NULL,
	[IP] NVARCHAR(15) NOT NULL,
	[SubnetMask] NVARCHAR(15) NOT NULL,
	[UserName] NVARCHAR(255) NOT NULL,
	[Password] NVARCHAR(255) NOT NULL,
	[IsPOE] BIT NOT NULL
);

CREATE TABLE Networks(
	[Id] INT PRIMARY KEY IDENTITY(1,1),
	[InstallationId] INT FOREIGN KEY REFERENCES Installations(Id),
	[Description] NVARCHAR(100) NOT NULL,
	[Remarks] NVARCHAR(255) NOT NULL,
	[NetworkIP] NVARCHAR(15) NOT NULL,
	[SubnetMask] NVARCHAR(15) NOT NULL,
	[DefaultGateway] NVARCHAR(15) NOT NULL,
	[HasPOE] BIT NOT NULL
);

CREATE TABLE WiFis(
	[Id] INT PRIMARY KEY IDENTITY(1,1),
	[InstallationId] INT FOREIGN KEY REFERENCES Installations(Id),
	[Description] NVARCHAR(100) NOT NULL,
	[Remarks] NVARCHAR(255) NOT NULL,
	[SSID] NVARCHAR(32) NOT NULL,
	[PSK] NVARCHAR(63) NOT NULL
);

CREATE TABLE Images(
	[Id] INT PRIMARY KEY IDENTITY(1,1),
	[InstallationId] INT FOREIGN KEY REFERENCES Installations(Id),
	[Description] NVARCHAR(100) NOT NULL,
	[Remarks] NVARCHAR(255) NOT NULL,
	[Data] VARBINARY(MAX) NOT NULL,
	[ImageType] TINYINT NOT NULL,
);

GO

CREATE PROCEDURE spResetDB AS
DELETE Images
DELETE WiFis
DELETE Networks
DELETE Devices
DELETE Installations
DELETE UserCustomerTasksRel
DELETE CustomerTasks
DELETE Customers
DELETE Users

INSERT INTO Users (LoginName, FirstName, LastName, EMail, Hash, Role) VALUES ('ADM', 'Admin', 'Admin', 'admin@wuav.dk', '$2a$10$o2rPhDPzNtmPo9mVNuohVOHkHP0uLau8XFaleRsulSk0XXU0fVjPO', 1)
INSERT INTO Users (LoginName, FirstName, LastName, EMail, Hash, Role) VALUES ('TECH', 'Tech', 'Tech', 'tech@wuav.dk', '$2a$10$6MXOEMhN/iQgQYLCVNmd1upeMuuYKm1hjOqLaIGe0mGFsIgxomDLq', 0)
INSERT INTO Users (LoginName, FirstName, LastName, EMail, Hash, Role) VALUES ('PM', 'PM', 'PM', 'pm@wuav.dk', '$2a$10$c2MvZKH/ZehQNb97IZ0BsuIz0UUP4.JhDFuyeoMXzeu12OIJ8qvKG', 2)
INSERT INTO Users (LoginName, FirstName, LastName, EMail, Hash, Role) VALUES ('SALG', 'Salg', 'Salg', 'salg@wuav.dk', '$2a$10$pmrLP.xrxBmTxMQBFdkrY.zlk9vNGgJMpMsFtift69VeMwx8efraW', 3)

Go

exec spResetDB

