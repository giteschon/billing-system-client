create database BillingSystem
go
use BillingSystem
go


--use master drop database BillingSystem
create table Category
(
IDCategory int primary key identity,
[Name] nvarchar(50)
)



create table [User]
(
IDUser int primary key identity,
[Username] nvarchar(50) unique,
[Password] nvarchar(50),
OperatorCode nvarchar(5),
[Name] nvarchar(50),
[Surname] nvarchar(50)
)

create table Task
(
IDTask int primary key identity,
[Name] nvarchar(50),
CategoryID int foreign key references Category(IDCategory)

)

create table City
(
IDCity int primary key identity,
PostalCode nvarchar(5),
[Name] nvarchar(50)
)

create table Fee
(
IDFee int primary key identity,
[Name] nvarchar(50)
)



create table Client
(
IDClient int primary key identity,
[Name] nvarchar(50),
[Address] nvarchar(100),
CityID int foreign key references City(IDCity),
Oib nvarchar(11) unique,
Mbs nvarchar(13) unique,
Mb nvarchar(9) unique,
FeeID int foreign key references Fee(IDFee),
PriceFee money
)

create table TimeLog
(
IDTimeLog int primary key identity,
TaskID int foreign key references Task(IDTask),
DateModified date default getdate(),
TaskDate date default getdate(),
TimeSpentOnTask int,
UserID int foreign key references [User](IDUser),
ClientID int foreign key references Client(IDClient)
)

create table Record
(
IDRecord int primary key identity,
[Entry] nvarchar(500)
)

create table Invoice
(
IDInvoice int primary key identity,
InvoiceNo nvarchar(20) unique,
UserID int foreign key references [User](IDUser),
InvoiceDate date,
ClientID int foreign key references Client(IDClient),
InvoiceSum money,
InvoicePeriodStart date,
InvoicePeriodEnd date

)

create table ItemType
(
IDItemType int primary key identity,
[Name] nvarchar(100)
)

create table Item
(
IDItem int primary key identity,
ItemTypeID int foreign key references ItemType(IDItemType),
Price money,
InvoiceID int foreign key references Invoice(IDInvoice),

)

go

insert into Category values
('Administration'),
('Booking'),
('Internal')

insert into [User] values
('admin','admin','sa','Admin', 'Adminovic'),
('pero', '123', 'PP', 'Pero', 'Peric'),
('ana','ana1', 'AB', 'Ana', 'Banic'),
('a','a','AA','a','a')

insert into Task values
('post administration', 1),
('lunch break', 3),
('booking ingoing invoices',2),
('booking outgoing invoices',2)


insert into Fee values
('Monthly'),
('Hourly')

go

insert into City values
('10000', 'Zagreb'),
('42000', 'Varaždin'),
('21000', 'Split')

insert into ItemType values
('Accounting services'),
('Disbursements')





insert into Client values
('Algebra', 'Ilica 264', 1, '1111', '2222','123',2,100),
('My Client', 'Zagrebacka 2', 2, '2528', '379','859',1,20)

go



create proc addCategory
@category nvarchar(50)
as
declare @id int
insert into Category values (@category)
set @id=(select IDCategory from Category where [Name]=@category)


go
create proc editCategory
@idCategory int,
@category nvarchar(50)
as
update Category set [Name]=@category where IDCategory=@idCategory

go
create proc addTask
@categoryId int,
@task nvarchar(50)
as
insert into Task ([Name], CategoryID)values (@task,@categoryId)

go
create proc editTask
@categoryId int,
@task nvarchar(50),
@idTask int
as
update Task set
CategoryID=@categoryId,
[Name]=@task
where IDTask=@idTask


go
create proc addUser
@username nvarchar(50),
@password nvarchar(50),
@operatorCode nvarchar(50),
@name nvarchar(50),
@surname nvarchar(50)
as
insert into [User] values (@username,@password,@operatorCode,@name,@surname)

go
create proc editUser
@idUser int,
@username nvarchar(50),
@password nvarchar(50),
@operatorCode nvarchar(50),
@name nvarchar(50),
@surname nvarchar(50)
as
update [User] set
Username=@username,
[Password]=@password,
OperatorCode=@operatorCode,
[Name]=@name,
Surname=@surname
where IDUser=@idUser

go
create proc addClient
@name nvarchar(50),
@address nvarchar(50),
@cityId int,
@oib nvarchar(11),
@mbs nvarchar(13),
@mb nvarchar(9),
@feeId int,
@price money

as
insert into Client values (@name,@address,@cityId,@oib, @mbs,@mb,@feeId,@price)

GO
create proc editClient
@idClient int,
@name nvarchar(50),
@address nvarchar(50),
@cityId int,
@oib nvarchar(11),
@mbs nvarchar(13),
@mb nvarchar(9),
@feeId int
as
update Client set 
[Name]=@name,
[Address]=@address,
CityID=@cityId,
Oib=@oib,
Mbs=@mbs,
Mb=@mb,
FeeID=@feeId
where IDClient=@idClient

go
create proc addTimeLog
@taskId int,
@userId int,
@taskDate date,
@timespentOn int,
@clientID int
as
insert into TimeLog(TaskID, DateModified, TaskDate, TimeSpentOnTask, UserID, ClientID) values
(@taskId,GETDATE(),@taskDate,@timespentOn,@userId, @clientID)

go
create proc editTimeLog
@taskId int,
@userId int,
@taskDate date,
@timespentOn int,
@idTimeLog int,
@clientID int
as
update TimeLog set
TaskID=@taskId,
DateModified=GETDATE(),
TaskDate=@taskDate,
TimeSpentOnTask=@timespentOn,
UserID=@userId,
ClientID=@clientID
where IDTimeLog=@idTimeLog



go
create proc getFee
@idFee int
as
select * from Fee where IDFee=@idFee

go
create proc getFees
as
select * from Fee

go
create proc getClient
@idClient int
as
select c.IDClient,c.[Name],c.[Address],cy.IDCity,cy.[Name],cy.PostalCode,c.Oib,c.Mb,c.Mbs, f.IDFee,f.[Name] from Client as c
inner join City as cy
on c.CityID=cy.IDCity
inner join Fee as f
on f.IDFee=c.FeeID
 where IDClient=@idClient

go
create proc getClients
as
select c.IDClient,c.[Name],c.[Address],cy.IDCity,cy.[Name],cy.PostalCode,c.Oib,c.Mb,c.Mbs, f.IDFee,f.[Name], c.PriceFee from Client as c
inner join City as cy
on c.CityID=cy.IDCity
inner join Fee as f
on f.IDFee=c.FeeID

exec getClients
--drop proc getClients

go
create proc getUser
@idUser int
as
select * from [User] where IDUser=@idUser

go
create proc getUsers
as
select * from [User]

go
create proc getTask
@idTask int
as
select t.*, c.* from Task as t
inner join Category as c
on t.CategoryID=c.IDCategory
where IDTask=@idTask


go
create proc getTasks
as
select t.*, c.* from Task as t
inner join Category as c
on t.CategoryID=c.IDCategory

go
create proc getTasksForCategory
@idCategory int
as
select t.*, c.* from Task as t
inner join Category as c
on t.CategoryID=c.IDCategory
where t.CategoryID=@idCategory



exec getTasksForCategory 1

exec getTasks
go
create proc getCategory
@idCategory int
as
select * from Category where IDCategory=@idCategory

go
create proc getCategories
as
select * from Category

go
create proc getCity
@idCity int
as
select * from City where IDCity=@idCity

go
create proc getCities
as
select * from City

go
create proc getInvoice 
@idInvoice int
as
select i.*, u.*, c.*, ci.*,f.* from Invoice as i
inner join [User] as u
on u.IDUser=i.UserID
inner join Client as c
on c.IDClient=i.ClientID
inner join city as ci
on ci.IDCity=c.CityID
inner join Fee as f
on f.IDFee=c.FeeID
where IDInvoice=@idInvoice

go
create proc getInvoices
as
select i.*, u.*, c.*, ci.*,f.* from Invoice as i
inner join [User] as u
on u.IDUser=i.UserID
inner join Client as c
on c.IDClient=i.ClientID
inner join city as ci
on ci.IDCity=c.CityID
inner join Fee as f
on f.IDFee=c.FeeID



go
create proc getTimeLog
@idTimeLog int
as
select tl.*, t.*, c.*,u.*, cl.*, ci.[Name], ci.PostalCode, f.[Name] from TimeLog as tl
inner join Task as t
on tl.TaskID=t.IDTask
inner join Category as c
on c.IDCategory=t.CategoryID
inner join [User] as u
on u.IDUser=tl.UserID 
inner join Client as cl
on cl.IDClient=tl.ClientID
inner join City as ci
on ci.IDCity=cl.CityID
inner join Fee as f 
on f.IDFee=cl.FeeID
where IDTimeLog=@idTimeLog

go
create proc getTimeLogs
as
select tl.*, t.*, c.*,u.*, cl.*, ci.[Name], ci.PostalCode, f.[Name] from TimeLog as tl
inner join Task as t
on tl.TaskID=t.IDTask
inner join Category as c
on c.IDCategory=t.CategoryID
inner join [User] as u
on u.IDUser=tl.UserID 
inner join Client as cl
on cl.IDClient=tl.ClientID
inner join City as ci
on ci.IDCity=cl.CityID
inner join Fee as f 
on f.IDFee=cl.FeeID


go
create proc getItem
@idItem int
as
select * from Item  where IDItem=@idItem

go
create proc getItems
as
select * from Item

go
create proc getRecords
as
select * from Record

--go
--create proc getService
--@idService int
--as
--select * from [Services] where IDService=@idService

go
create proc getItemType
as
select * from ItemType

go
create proc getCredentials
@username nvarchar(50),
@password nvarchar(50)
as
select * from [User]   where Username=@username and [Password]=@password

--drop proc getCredentials



go
create proc addIncvoice
@idInvoice int out,
@userId int,
@clientId int,
@periodStart date,
@periodEnd date

as
begin
declare @id int
begin
insert into Invoice (UserID, InvoiceDate, ClientID,InvoicePeriodStart, InvoicePeriodEnd) values
(@userId,GETDATE(),@clientId,@periodStart,@periodEnd)
set @idInvoice=SCOPE_IDENTITY()
set @id=SCOPE_IDENTITY()
update Invoice set
InvoiceNo=convert(nvarchar(20),@id) + '/1/2018' where IDInvoice=@idInvoice
end
return @idInvoice
end

drop proc addIncvoice

declare @id2 int
exec addIncvoice @id2 output,2,1, '2018-10-01', '2018-10-31'
select @id2

select * from Invoice
go
create proc addInvoiceSum
@invoiceNo nvarchar(20),
@sum money
as
update Invoice set
InvoiceSum=@sum
 where InvoiceNo=@invoiceNo

go
--rac usluge
create proc addDefaultItem
@price money,
@invoiceId int
as
insert into Item values
(1,@price,@invoiceId)

go
create proc addOtherItems
@itemTypeId int,
@price money,
@invoiceId int
as
insert into Item values
(@itemTypeId,@price,@invoiceId)

select * from Invoice


--create table Specifications
--(
--IDSpecification int primary key identity,
--InvoiceID int foreign key references Invoice(IDInvoice),
--CategoryID int foreign key references Category(IDCategory),
--[Time] int
--)

--go
--create proc addSpecification
--@invoiceId int,
--@categoryId int,
--@time int
--as
--insert into Specifications values (@invoiceId,@categoryId,@time)

go
create proc generateSpecification
@clientId int,
@specStart date,
@specEnd date
as
select t.ClientID, @specStart,@specEnd, tt.[Name], t.TimeSpentOnTask from TimeLog as t
inner join Task as tt
on tt.IDTask=t.TaskID
where t.ClientID=@clientId and (@specStart <= t.TaskDate) and (t.TaskDate <= @specEnd)


--drop proc generateSpecification
exec generateSpecification 1, '2018-10-01', '2018-10-31'

exec getCredentials 'admin', 'admin'

--select * from Invoice