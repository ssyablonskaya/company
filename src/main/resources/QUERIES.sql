use business;
SET SQL_SAFE_UPDATES = 0;

#update the phone number on the line where the website starts with youruti...
UPDATE Contacts SET phone_number = '7788-7788' WHERE website like 'youruti%' AND id > 0;

#update salary (+100) for all positions except director
UPDATE Positions SET salary=salary+100 WHERE name <> 'director';

#update Positions (one name) with id = 2
UPDATE Positions SET name = 'specialist' WHERE id = 2;

UPDATE Departments SET name = 'constructing' WHERE id = 8;


#delete all table rows with ids bigger than 6
DELETE FROM Positions WHERE id > 6;

#delete all data from the Company table
DELETE FROM Companies;

#will delete the table and all auto-incrementing ids (if added again, they will start with id = 1)
TRUNCATE TABLE Company_clients;

#subqueries: remove employees who work in the HR-department
DELETE FROM Employees WHERE department_id = (select id FROM Departments WHERE name = 'HR');


#display all fields from Positions
select * from positions;

#see how new salary will be displayed with +100 (+ Alias column)
SELECT name, salary, salary+100 AS new_salary FROM Positions;

#display companies and their departments (+ Alias a table), sorteed: Companies' id by DESC, deps by alphabet
SELECT c.id, c.name AS Company, d.name AS Department
FROM Companies c
INNER JOIN Departments d ON d.company_id = c.id 
ORDER BY c.id DESC, d.name ASC;


#count the amount of each company's deps
SELECT Companies.name, COUNT(Departments.name) AS Deps_amount 
FROM Companies JOIN Departments ON Companies.id = Departments.company_id
GROUP BY Companies.name;

#display all possible names of banks from 2 tables without duplicates (UNION) and sort by alphabet 
SELECT bank AS database_banks FROM Payroll_accounts
UNION SELECT bank FROM tax_administration
ORDER BY database_banks ASC;

#the same with duplicates (UNION ALL)
SELECT bank AS database_banks FROM Payroll_accounts
UNION ALL SELECT bank FROM tax_administration
ORDER BY database_banks ASC;


#display unique names of banks
SELECT DISTINCT bank FROM payroll_accounts;

#withdraw the ids of companies, the total cost of services of which is more than 2000 (sort the group with having)
SELECT company_id, SUM(price) AS SUM FROM Services
GROUP BY company_id
HAVING SUM(price) > 2000;

#select from the Services table those services, the cost of which is higher than the average service price for this company:
SELECT name, company_id, price, (SELECT AVG(price) FROM Services AS SUBSer WHERE  SUBSer.company_id = Servs.company_id) AS AVG_price
FROM Services AS Servs
WHERE price > (SELECT AVG(price) FROM Services AS SUBSer WHERE SUBSer.company_id = Servs.company_id);

#add to Company_clients companies' names and their clients'name, date ASC
SELECT company_clients.date_of_cooperation, Companies.name AS Company, Clients.name AS Client
FROM company_clients JOIN Companies ON Companies.id = Company_clients.company_id
JOIN Clients ON Clients.id = Company_clients.client_id
ORDER BY date_of_cooperation;

#IF-statement
SELECT id, name, price, IF(price > 3000, 'expensive service', IF(price < 1000, 'cheap service', 'medium price service')) 
AS description FROM Services;

#display the amount of employees under 21
SELECT COUNT(dob) AS U21_amount FROM Employees
WHERE  (YEAR(CURRENT_DATE) - YEAR(dob)) < 21;