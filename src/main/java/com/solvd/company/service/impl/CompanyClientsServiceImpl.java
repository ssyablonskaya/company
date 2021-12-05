package com.solvd.company.service.impl;

import com.solvd.company.persistence.CompanyClientsRepository;
import com.solvd.company.persistence.CompanyRepository;
import com.solvd.company.persistence.jdbcImpl.CompanyClientsRepositoryImpl;
import com.solvd.company.persistence.jdbcImpl.CompanyRepositoryImpl;
import com.solvd.company.service.ClientService;
import com.solvd.company.service.CompanyClientsService;

public class CompanyClientsServiceImpl implements CompanyClientsService {

    private final CompanyRepository companyRepository;
    private final CompanyClientsRepository companyClientsRepository;
    private final ClientService clientService;

    public CompanyClientsServiceImpl() {
        this.companyRepository = new CompanyRepositoryImpl();
        this.companyClientsRepository = new CompanyClientsRepositoryImpl();
        this.clientService = new ClientServiceImpl();
    }

    /*    @Override
    public Company create(Company company) {
        company.setId(null);
        List<Client> companyClients = company.getClients();
        companyRepository.create(company);
        companyClients.stream()
                .filter(client -> client != null)
                .map(client -> clientService.findOrCreate(client))
                .forEach(client -> companyClientsRepository.create(company.getId(), client.getId()));
        return company;
    }*/

}
