insert into tasks (title, description, due_date, creation_date, status) 
values (
	'Task number 1', 	
	'Task that never expires', 	
	null, 	
	'2021-08-21 23:59:59', 	
	'PENDING');
	
insert into tasks (title, description, due_date, creation_date, status) 
values (
	'Task number 2', 	
	'Pending expired task with overdue date', 	
	'2021-08-20 23:59:59', 	
	'2021-08-19 10:10:00', 	
	'PENDING');
	
insert into tasks (title, description, due_date, creation_date, status) 
values (
	'Task number 3', 	
	'Finished expired task', 	
	'2021-08-20 23:59:59', 	
	'2021-08-19 10:10:00', 		
	'FINISHED');

insert into tasks (title, description, due_date, creation_date, status) 
values (
	'Task number 4', 	
	'Pending task', 	
	'2021-08-25 23:59:59', 	
	'2021-08-21 10:10:08', 	
	'PENDING');
