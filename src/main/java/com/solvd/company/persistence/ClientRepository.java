package com.solvd.company.persistence;

import com.solvd.company.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    void create(Client client);

    Optional<Client> findByName(String name);

    List<Client> findAll();

}
