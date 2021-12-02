package com.solvd.company;

import com.solvd.company.domain.*;
import com.solvd.company.service.CompanyService;
import com.solvd.company.service.impl.CompanyServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        PayrollAccount firstPA = new PayrollAccount();
        firstPA.setBank("MyBank1");
        firstPA.setBankAccount("MB4252351");

        PayrollAccount secondPA = new PayrollAccount();
        secondPA.setBank("MyBank1");
        secondPA.setBankAccount("MB244252351");

        Address firstAddress = new Address();
        firstAddress.setCountry("Russia");
        firstAddress.setCity("Moscow");
        firstAddress.setStreet("Centralnaya");
        firstAddress.setHouse("271");

        Contact firstContact = new Contact();
        firstContact.setPhoneNumber("80923949951");
        firstContact.setEmail("mycompany@gmail.com");
        firstContact.setWebsite("mycompany1.ru");

        Position firstPosition = new Position();
        firstPosition.setName("recruiter");
        firstPosition.setSalary(BigDecimal.valueOf(1300.44));

        Employee firstEmployee = new Employee();
        firstEmployee.setFirstName("Felix");
        firstEmployee.setLastName("Brikh");
        firstEmployee.setDob(LocalDate.of(1970, 3, 17));
        firstEmployee.setYearOfEmployment(2021);
        firstEmployee.setPosition(firstPosition);
        firstEmployee.setPayrollAccount(firstPA);

        Employee secondEmployee = new Employee();
        secondEmployee.setFirstName("Miley");
        secondEmployee.setLastName("Cyrus");
        secondEmployee.setDob(LocalDate.of(1970, 4, 7));
        secondEmployee.setYearOfEmployment(2021);
        secondEmployee.setPosition(firstPosition);
        secondEmployee.setPayrollAccount(secondPA);
        List<Employee> employees = new ArrayList<>();
        employees.add(firstEmployee);
        employees.add(secondEmployee);

        Department firstDepartment = new Department();
        firstDepartment.setName("marketing");
        firstDepartment.setEmployees(employees);
        List<Department> departments = new ArrayList<>();
        departments.add(firstDepartment);

        Service firstService = new Service();
        firstService.setName("upgrading your communication skills");
        firstService.setPrice(BigDecimal.valueOf(290.00));
        firstService.setDurationDays(60);

        Service secondService = new Service();
        secondService.setName("meeting skills");
        secondService.setPrice(BigDecimal.valueOf(83.00));
        secondService.setDurationDays(6);

        List<Service> services = new ArrayList<>();
        services.add(firstService);
        services.add(secondService);

        Client firstClient = new Client();
        firstClient.setName("Alexander Wang holding two");
        List<Client> clients = new ArrayList<>();
        clients.add(firstClient);

        Company firstCompany = new Company();
        firstCompany.setName("MyCompany1");
        firstCompany.setSphere("communication");
        firstCompany.setAddress(firstAddress);
        firstCompany.setContact(firstContact);
        firstCompany.setDepartments(departments);
        firstCompany.setServices(services);
        firstCompany.setClients(clients);

        TaxAdministration taxAdministration = new TaxAdministration();
        taxAdministration.setNumber("new436634631");
        taxAdministration.setBank("MyBank1");
        taxAdministration.setBankAccount("mybank39599471");
        taxAdministration.setCompany(firstCompany);


        CompanyService companyService = new CompanyServiceImpl();
        companyService.create(firstCompany);


    }

}
