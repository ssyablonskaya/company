use business;
SET SQL_SAFE_UPDATES = 0;

Insert into Positions(name, salary) values
	('director', 3500),
    ('manager', 2500),
    ('HR', 2000),
    ('assistant', 1500),
    ('accountant', 2000),
    ('cleaner', 950);
#select * from positions

SELECT name, salary, salary+100 AS new_salary FROM Positions;
UPDATE Positions SET salary=salary+100 WHERE name <> 'director' AND id > 0;
UPDATE Positions SET name = 'specialist' WHERE id = 2;
DELETE FROM Positions WHERE id > 6;
    
Insert into Addresses(country, city, street, house) values 
	('Belarus', 'Minsk', 'Mogilevskaya', '1'),
    ('Belarus', 'Minsk', 'Servisnaya', '2b'),
    ('Belarus', 'Molodechno', 'Krinichnaya', '77'),
    ('USA', 'Los Angeles', 'Cornflower', '133a');
#select * from Addresses

Insert into Contacts(phone_number, email, website) values
	('1324512', 'biba_n_boba@gmail.com', 'bibaandboba.com'),
	('+5145245251', 'solvd@gmail.com', 'solvd.com'),
    ('', 'utility-service@mail.ru', 'yourutiservice.by'),
    ('53151515', 'iteasy@gmail.com', default);
#select * from Contacts

UPDATE Contacts SET phone_number = '7788-7788' WHERE website like 'youruti%' AND id > 0;

Insert into Companies(address_id, contact_id, name, sphere) values
	(1, 4, 'Solvd', 'IT'),
    (4, 7, 'Biba n Boba corp.', 'IT'),
    (2, 5, 'Utility-service No1', 'utility'),
    (3, 6, 'ITeasy', 'IT');
#select * from Companies

Insert into Clients(name) values
	('ITsoftic'),
    ('Factory Good'),
    ('Mischelin'),
    ('SeeYouSoon company'),
    ('ITneSila'),
    ('Utility-service №6'),
    ('Air-Shop'),
    ('Sneakers for you'),
    ('Santa Lucia'),
    ('Hala Madrid corporation'),
    ('ComeOnYouGunners prod.'),
    ('It consult Greece'),
    ('We make your home beautiful');
#SELECT * FROM Clients;

Insert into Company_clients(company_id, client_id, date_of_cooperation) values
	(1, 5, str_to_date('01.10.2016', '%d.%m.%Y')),
    (1, 3, str_to_date('18.10.2017', '%d.%m.%Y')),
    (1, 9, str_to_date('21.07.2018', '%d.%m.%Y')),
    (1, 1, str_to_date('11.09.2021', '%d.%m.%Y')),
    (3, 6, default),
    (3, 13, str_to_date('10.10.2021', '%d.%m.%Y')),
    (2, 4, str_to_date('01.10.2007', '%d.%m.%Y')),
    (2, 10, str_to_date('01.10.2010', '%d.%m.%Y')),
    (2, 7, str_to_date('01.12.2020', '%d.%m.%Y')),
    (2, 11, default),
    (4, 12, str_to_date('01.10.2016', '%d.%m.%Y')),
    (4, 12, str_to_date('01.10.2018', '%d.%m.%Y')),
    (4, 5, str_to_date('01.10.2020', '%d.%m.%Y'));
#SELECT * FROM Company_clients;
#TRUNCATE TABLE Company_clients;

Insert into Departments(company_id, name) values
	(1, 'development'),
    (1, 'test automation'),
    (1, 'manual testing'),
    (1, 'HR'),
    (1, 'accounting'),
    (2, 'test automation'),
    (2, 'manual testing'),
    (3, 'constracting'),
    (3, 'accounting'),
    (4, 'development');
#SELECT * FROM Departments
UPDATE Departments SET name = 'constructing' WHERE id = 8;

Insert into Payroll_accounts(bank, account_number) values
	('BelAgroPromBank', '81482422BY'),
    ('SBER', '24646363BY'),
    ('SBER', '24566363BY'),
    ('SBER', '5453363BY'),
    ('SBER', '66646363BY'),
    ('America Bank', '24646363US'),
    ('America Bank', '24646334363US'),
    ('America Bank', '2464636363US'),
    ('America Bank', '2464636345US'),
    ('UtiBank', '22242646363BY'),
    ('UtiBank', '1123446363BY'),
    ('TechBank', '241343BY'),
    ('TechBank', '76373BY');
#SELECT * FROM Payroll_accounts

Insert into Employees(department_id, position_id, payroll_account_id, first_name, last_name, dob, year_of_employment) values
	(5, 6, 2, 'Alexa', 'Perfo', str_to_date('01.11.1972', '%d.%m.%Y'), 2018),
    (3, 2, 3, 'Stefan', 'Berg', str_to_date('01.08.1997', '%d.%m.%Y'), 2020),
    (4, 3, 4, 'July', 'Stone', str_to_date('11.11.2001', '%d.%m.%Y'), 2018),
    (5, 5, 5, 'John', 'Snow', str_to_date('21.01.1990', '%d.%m.%Y'), 2016),
    (6, 1, 6, 'John', 'Cena', str_to_date('21.01.1988', '%d.%m.%Y'), 2010),
    (6, 2, 7, 'James', 'Parrow', str_to_date('21.11.1991', '%d.%m.%Y'), 2011),
    (6, 4, 8, 'Wess', 'Robertson', str_to_date('21.01.1983', '%d.%m.%Y'), 2016),
    (8, 2, 10, 'Isac', 'Green', str_to_date('15.01.1990', '%d.%m.%Y'), 2015),
    (9, 5, 11, 'Simona', 'Future', str_to_date('21.02.1995', '%d.%m.%Y'), 2016),
    (10, 2, 12, 'John', 'Assi', str_to_date('11.01.1988', '%d.%m.%Y'), 2016),
    (10, 4, 13, 'Patrik', 'Stewart', str_to_date('23.11.2000', '%d.%m.%Y'), 2019),
    (1, 1, 1, 'Max', 'Becker', str_to_date('01.11.2001', '%d.%m.%Y'), 2018);
#SELECT * FROM Employees

Insert into Tax_administration(company_id, number, bank, bank_account) values
	(1, 'N1001', 'SBER', 'COMP2434345BY'),
    (2, 'N1002', 'USA BANK', 'COMP2434345US'),
    (3, 'N1003', 'SBER', 'UTI2434345BY'),
    (4, 'N1004', 'Idea Bank', 'COMP476345BY');
#SELECT * FROM Tax_administration;

Insert into Services(company_id, name, price, duration_days) values
	(1, 'software development', 4500, 21),
    (1, 'software testing', 1500, 11),
    (2, 'software testing', 2500, 12),
    (3, 'painting walls', 450, 2),
    (3, 'changing of meters', 100, 1),
    (4, 'easy software development', 2500, 16);
#SELECT * FROM Services;


    