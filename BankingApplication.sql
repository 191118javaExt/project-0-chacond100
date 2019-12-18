drop table if exists Users cascade;
create table Users(
	ID serial primary key,
	firstName varchar (50) not null,
	lastName varchar (50) not null,
	username varchar (50) not null,
	password varchar (50) not null
	);
	
drop table if exists Accounts cascade;
create table Accounts(
	ID serial primary key,
	Type varchar (50),
	Balance numeric (50,0)
	);

ALTER TABLE Accounts ADD COLUMN Status varchar;

drop table if exists Mappings cascade;
create table Mappings(
	ID serial primary key,
	UserID integer,
	AccountID integer,
	foreign key (UserID) references Users(ID) on delete cascade,
	foreign key (AccountID) references Accounts(ID) on delete cascade
	);
	