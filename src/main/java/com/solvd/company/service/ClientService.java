package com.solvd.company.service;

import com.solvd.company.domain.Client;

public interface ClientService {

    Client findOrCreate(Client client);

}
