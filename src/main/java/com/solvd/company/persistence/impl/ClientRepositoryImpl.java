package com.solvd.company.persistence.impl;

import com.solvd.company.domain.Client;
import com.solvd.company.domain.exception.ProcessException;
import com.solvd.company.persistence.ClientRepository;
import com.solvd.company.persistence.ConnectionPool;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class ClientRepositoryImpl implements ClientRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Client client) {
        Connection connection = CONNECTION_POOL.getConnection();

        String insertInto = "Insert into Clients (name) values (?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, client.getName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                client.setId(resultSet.getLong(1));
            }
        } catch (
                SQLException ex) {
            throw new ProcessException("Can't create a Client", ex);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }


    @Override
    public Optional<Client> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Client> findAll() {
        return null;
    }

}
