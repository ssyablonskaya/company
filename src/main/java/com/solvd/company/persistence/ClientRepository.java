package com.solvd.company.persistence;

import com.solvd.company.domain.Client;

import java.util.List;

public interface ClientRepository {

    void create(Client client);

    Object findByName(String name);

    List<Client> findAll();

}
