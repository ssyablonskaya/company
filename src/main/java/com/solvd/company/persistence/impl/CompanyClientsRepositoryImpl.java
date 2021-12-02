package com.solvd.company.persistence.impl;

import com.solvd.company.domain.Client;
import com.solvd.company.domain.exception.ProcessException;
import com.solvd.company.persistence.CompanyClientsRepository;
import com.solvd.company.persistence.ConnectionPool;

import java.sql.*;

public class CompanyClientsRepositoryImpl implements CompanyClientsRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Long companyId, Client client) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "Insert into Company_clients(company_id, client_id, date_of_cooperation) values (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertInto);
            preparedStatement.setLong(1, companyId);
            preparedStatement.setLong(2, client.getId());
            preparedStatement.setDate(3, Date.valueOf(client.getDateOfCooperation()));
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new ProcessException("Can't create Company_clients", ex);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

}
