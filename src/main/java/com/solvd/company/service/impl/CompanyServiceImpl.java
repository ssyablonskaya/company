package com.solvd.company.service.impl;

import com.solvd.company.domain.Company;
import com.solvd.company.persistence.AddressRepository;
import com.solvd.company.persistence.CompanyRepository;
import com.solvd.company.persistence.ContactRepository;
import com.solvd.company.persistence.impl.AddressRepositoryImpl;
import com.solvd.company.persistence.impl.CompanyRepositoryImpl;
import com.solvd.company.persistence.impl.ContactRepositoryImpl;
import com.solvd.company.service.CompanyService;
import com.solvd.company.service.DepartmentService;
import com.solvd.company.service.ServiceService;

import java.util.List;

public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository = new CompanyRepositoryImpl();
    private final AddressRepository addressRepository = new AddressRepositoryImpl();
    private final ContactRepository contactRepository = new ContactRepositoryImpl();
    private final DepartmentService departmentService = new DepartmentServiceImpl();
    private final ServiceService serviceService = new ServiceServiceImpl();

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
