package com.solvd.company.service.impl;

import com.solvd.company.domain.Client;
import com.solvd.company.domain.Company;
import com.solvd.company.persistence.CompanyClientsRepository;
import com.solvd.company.persistence.CompanyRepository;
import com.solvd.company.persistence.impl.CompanyClientsRepositoryImpl;
import com.solvd.company.persistence.impl.CompanyRepositoryImpl;
import com.solvd.company.service.ClientService;
import com.solvd.company.service.CompanyClientsService;

import java.util.List;

public class CompanyClientsServiceImpl implements CompanyClientsService {

    private final CompanyRepository companyRepository = new CompanyRepositoryImpl();
    private final CompanyClientsRepository companyClientsRepository = new CompanyClientsRepositoryImpl();
    private final ClientService clientService = new ClientServiceImpl();

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
