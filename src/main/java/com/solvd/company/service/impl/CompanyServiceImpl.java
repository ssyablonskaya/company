package com.solvd.company.service.impl;

import com.solvd.company.domain.Company;
import com.solvd.company.persistence.AddressRepository;
import com.solvd.company.persistence.CompanyRepository;
import com.solvd.company.persistence.ContactRepository;
import com.solvd.company.persistence.jdbcImpl.AddressRepositoryImpl;
import com.solvd.company.persistence.jdbcImpl.CompanyRepositoryImpl;
import com.solvd.company.persistence.jdbcImpl.ContactRepositoryImpl;
import com.solvd.company.persistence.mybatisImpl.AddressMyBatisRepository;
import com.solvd.company.persistence.mybatisImpl.CompanyMyBatisRepository;
import com.solvd.company.persistence.mybatisImpl.ContactMyBatisRepository;
import com.solvd.company.service.CompanyService;
import com.solvd.company.service.DepartmentService;
import com.solvd.company.service.ServiceService;

import java.util.List;

public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final AddressRepository addressRepository;
    private final ContactRepository contactRepository;
    private final DepartmentService departmentService;
    private final ServiceService serviceService = new ServiceServiceImpl();

    public CompanyServiceImpl() {
        //this.companyRepository = new CompanyRepositoryImpl();
        this.companyRepository = new CompanyMyBatisRepository();
        //this.addressRepository = new AddressRepositoryImpl();
        this.addressRepository = new AddressMyBatisRepository();
        //this.contactRepository = new ContactRepositoryImpl();
        this.contactRepository = new ContactMyBatisRepository();
        this.departmentService = new DepartmentServiceImpl();
    }

    @Override
    public Company create(Company company) {
        if (company.getAddress() != null) {
            addressRepository.create(company.getAddress());
        }
        if (company.getContact() != null) {
            contactRepository.create(company.getContact());
        }

        company.setId(null);
        companyRepository.create(company.getAddress().getId(), company.getContact().getId(), company);

        if (company.getDepartments() != null) {
            company.getDepartments()
                    .forEach(department -> departmentService.create(company.getId(), department));
        }

        if (company.getServices() != null) {
            company.getServices()
                    .forEach(service -> serviceService.create(company.getId(), service));
        }
        return company;
    }

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

}
