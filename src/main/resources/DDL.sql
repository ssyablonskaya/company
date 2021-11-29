create database if not exists business;
use business;

create table if not exists Companies (
	id serial,
    address_id bigint unsigned not null,
    contact_id bigint unsigned not null,
	name varchar(101) not null,
	sphere varchar(101) not null,
    primary key(id),
	constraint fk_companies_address_id foreign key(address_id) references Addresses(id)
		on update no action
        on delete cascade,
	constraint fk_companies_contact_id foreign key(contact_id) references Contacts(id)
		on update no action
        on delete cascade
);

create table if not exists Departments (
	id serial,
    company_id bigint unsigned not null,
	name varchar(45) not null,
    primary key(id),
	constraint fk_departments_company_id foreign key(company_id) references Companies(id)
		on update no action
        on delete cascade
);

create table if not exists Positions (
	id serial,
	name varchar(45) not null,
	salary double unsigned not null,
    primary key(id)
);
ALTER TABLE Positions MODIFY salary DECIMAL(7,2);

create table if not exists Payroll_accounts (
	id serial,
	bank varchar(101) not null,
	account_number varchar(45) not null,
    primary key(id)
);
create unique index unique_idx_payrollaccounts_accountnumber on Payroll_accounts(account_number);

#NEW
create table if not exists Employees (
	id serial,
    department_id bigint unsigned not null,
    position_id bigint unsigned not null,
    payroll_account_id bigint unsigned not null,
    first_name varchar(45) not null,
    last_name varchar(45) not null,
	dob timestamp not null,
	year_of_employment year not null,
    primary key(id),
	constraint fk_employees_department_id foreign key(department_id) references Departments(id)
		on update no action
        on delete cascade,
	constraint fk_employees_position_id foreign key(position_id) references Positions(id)
		on update no action
        on delete cascade,
	constraint fk_employees_payroll_account_id foreign key(payroll_account_id) references Payroll_accounts(id)
		on update no action
        on delete cascade
);

create table if not exists Addresses (
	id serial,
	country varchar(45) not null,
	city varchar(45) not null,
    street varchar(45) not null,
    house varchar(45) not null,
    primary key(id)
);

create table if not exists Contacts (
	id serial,
	phone_number varchar(20) not null default 'no info',
	email varchar(101) not null default 'no info',
    website varchar(101) not null default 'no info',
    primary key(id)
);

create table if not exists Tax_administration (
	id serial,
    company_id bigint unsigned not null,
    number varchar(45) not null,
	bank varchar(101) not null,
	bank_account varchar(101) not null,
    primary key(id),
    	constraint fk_taxadministration_company_id foreign key(company_id) references Companies(id)
		on update no action
        on delete cascade
);
create unique index unique_idx_taxadministration_number on Tax_administration(number);
create unique index unique_idx_taxadministration_bankaccount on Tax_administration(bank_account);

create table if not exists Services (
	id serial,
    company_id bigint unsigned not null,
    name varchar(101) not null,
	price double unsigned not null,
	duration_days int unsigned not null,
    primary key(id),
	constraint fk_services_company_id foreign key(company_id) references Companies(id)
		on update no action
        on delete cascade
);
ALTER TABLE Services MODIFY price DECIMAL(7,2);

create table if not exists Clients (
	id serial,
    name varchar(101) not null,
    primary key(id)
);

create table if not exists Company_clients (
id serial,
company_id bigint unsigned not null,
client_id bigint unsigned not null,
date_of_cooperation timestamp not null default CURRENT_TIMESTAMP,
primary key(id),
constraint fk_companyclients_company_id foreign key(company_id) references Companies(id)
	on update no action
    on delete cascade,
constraint fk_companyclients_clienty_id foreign key(client_id) references Clients(id)
	on update no action
    on delete cascade
);

