drop table if exists Users cascade;
create table Users(
	user_ID serial primary key,
	firstName varchar (50) not null,
	lastName varchar (50) not null,
	username varchar (50) not null,
	password varchar (50) not null,
	acc_ID integer references Accounts(acc_ID)
	);

drop table if exists Accounts cascade;
create table Accounts(
	acc_ID serial primary key,
	balance numeric (50,0)
	);
