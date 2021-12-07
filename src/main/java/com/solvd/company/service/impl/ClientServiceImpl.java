package com.solvd.company.service.impl;

import com.solvd.company.domain.Client;
import com.solvd.company.persistence.ClientRepository;
import com.solvd.company.persistence.jdbcImpl.ClientRepositoryImpl;
import com.solvd.company.persistence.mybatisImpl.ClientMyBatisRepository;
import com.solvd.company.service.ClientService;

public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl() {
        //this.clientRepository = new ClientRepositoryImpl();
        this.clientRepository = new ClientMyBatisRepository();
    }

/*    @Override
    public Client findOrCreate(Client client) {
        return clientRepository.findByName(client.getName())
                .orElseGet(() -> create(client));
    }*/

    private Client create(Client client) {
        client.setId(null);
        clientRepository.create(client);
        return client;
    }

}
