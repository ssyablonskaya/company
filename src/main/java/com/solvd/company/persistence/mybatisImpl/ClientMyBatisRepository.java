package com.solvd.company.persistence.mybatisImpl;

import com.solvd.company.domain.Client;
import com.solvd.company.domain.Position;
import com.solvd.company.persistence.ClientRepository;
import com.solvd.company.persistence.MyBatisSessionHolder;
import com.solvd.company.persistence.PositionRepository;
import com.sun.corba.se.spi.activation.Repository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class ClientMyBatisRepository implements ClientRepository {

    @Override
    public void create(Client client) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ClientRepository clientRepository = session.getMapper(ClientRepository.class);
            clientRepository.create(client);
        }
    }

    @Override
    public Optional<Client> findByName(String name) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ClientRepository clientRepository = session.getMapper(ClientRepository.class);
             return (Optional<Client>) clientRepository.findByName(name);
        }
    }

    @Override
    public List<Client> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ClientRepository clientRepository = session.getMapper(ClientRepository.class);
            return clientRepository.findAll();
        }
    }
}
