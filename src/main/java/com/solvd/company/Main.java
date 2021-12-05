package com.solvd.company;

import com.solvd.company.domain.*;
import com.solvd.company.persistence.CompanyRepository;
import com.solvd.company.persistence.ServiceRepository;
import com.solvd.company.persistence.jdbcImpl.CompanyRepositoryImpl;
import com.solvd.company.persistence.jdbcImpl.ServiceRepositoryImpl;
import com.solvd.company.persistence.mybatisImpl.CompanyMyBatisRepository;
import com.solvd.company.service.CompanyService;
import com.solvd.company.service.TaxAdministrationService;
import com.solvd.company.service.impl.CompanyServiceImpl;
import com.solvd.company.service.impl.TaxAdministrationServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {

    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        PayrollAccount firstPA = new PayrollAccount();
        firstPA.setBank("MyBankGood");
        firstPA.setBankAccount("A" + UUID.randomUUID());

        PayrollAccount secondPA = new PayrollAccount();
        secondPA.setBank("GoodMyBank");
        secondPA.setBankAccount("A" + UUID.randomUUID());

        Address firstAddress = new Address();
        firstAddress.setCountry("Russia");
        firstAddress.setCity("Moscow");
        firstAddress.setStreet("Centralnaya");
        firstAddress.setHouse("271");

        Contact firstContact = new Contact();
        firstContact.setPhoneNumber("80923949951");
        firstContact.setEmail("cgmycompany@gmail.com");
        firstContact.setWebsite("ssmycompany.ru");

        Position firstPosition = new Position();
        firstPosition.setName("recruiter");
        firstPosition.setSalary(BigDecimal.valueOf(1300.44));

        Employee firstEmployee = new Employee();
        firstEmployee.setFirstName("Felixey");
        firstEmployee.setLastName("Brikhey");
        firstEmployee.setDob(LocalDate.of(1970, 3, 17));
        firstEmployee.setYearOfEmployment(2021);
        firstEmployee.setPosition(firstPosition);
        firstEmployee.setPayrollAccount(firstPA);

        Employee secondEmployee = new Employee();
        secondEmployee.setFirstName("Mileeey");
        secondEmployee.setLastName("Cyruy");
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
        firstService.setName("upgrading your communication skills2");
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
        firstClient.setName("Alexander Wang holding");
        firstClient.setDateOfCooperation(LocalDate.now());
        List<Client> clients = new ArrayList<>();
        clients.add(firstClient);

        Company firstCompany = new Company();
        firstCompany.setName("CompanyGood");
        firstCompany.setSphere("communication");
        firstCompany.setAddress(firstAddress);
        firstCompany.setContact(firstContact);
        firstCompany.setDepartments(departments);
        firstCompany.setServices(services);
        firstCompany.setClients(clients);

        TaxAdministration taxAdministration = new TaxAdministration();
        taxAdministration.setNumber("N" + UUID.randomUUID());
        taxAdministration.setBank("MyBank1");
        taxAdministration.setBankAccount("B" + UUID.randomUUID());
        taxAdministration.setCompany(firstCompany);

        CompanyService companyService = new CompanyServiceImpl();
        companyService.create(firstCompany);

        CompanyRepository companyRepository = new CompanyRepositoryImpl();
        //companyRepository.delete(23L);

        ServiceRepository serviceRepository = new ServiceRepositoryImpl();
        //serviceRepository.update(firstService, "making your communication skills better");

       /* TaxAdministrationService taxAdministrationService = new TaxAdministrationServiceImpl();
        taxAdministrationService.create(firstCompany.getId(), taxAdministration);*/

        //List<Company> companies = companyService.getAll();
        //LOGGER.debug(companies);

        LOGGER.info("Hi");

        CompanyRepository companyRepositoryMB = new CompanyMyBatisRepository();
        List<Company> companiesMB = companyRepositoryMB.findAll();
        LOGGER.debug(companiesMB);

        LOGGER.info("Bye");

    }

}
