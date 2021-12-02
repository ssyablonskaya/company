package com.solvd.company.persistence.impl;

import com.solvd.company.domain.Service;
import com.solvd.company.domain.exception.ProcessException;
import com.solvd.company.persistence.ConnectionPool;
import com.solvd.company.persistence.ServiceRepository;

import java.sql.*;

public class ServiceRepositoryImpl implements ServiceRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Long companyId, Service service) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "Insert into Services(company_id, name, price, duration_days) values (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, companyId);
            preparedStatement.setString(2, service.getName());
            preparedStatement.setBigDecimal(3, service.getPrice());
            preparedStatement.setInt(4, service.getDurationDays());


            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                service.setId(resultSet.getLong(1));
            }
        } catch (SQLException ex) {
            throw new ProcessException("Can't create a Service", ex);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update() {
        Connection connection = CONNECTION_POOL.getConnection();
        String update = "UPDATE Services SET name = ? where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            String name = "plastering the walls";
            long id = 4L;

            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new ProcessException("Can't update a Service", ex);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete() {
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "DELETE FROM Services WHERE duration_days = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            int durationDays = 1;
            preparedStatement.setInt(1, durationDays);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new ProcessException("Can't delete a Service", ex);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

}
