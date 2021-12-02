package com.solvd.company.persistence;

import com.solvd.company.domain.Client;

public interface CompanyClientsRepository {

    void create(Long companyId, Client client);

}
