CREATE TABLE tasks (
	id int not null generated always as identity, 
	title varchar(256) not null, 
	description varchar(1024), 
	due_date date, 
	status varchar(10), 
	creation_date date not null, 
	primary key (id));