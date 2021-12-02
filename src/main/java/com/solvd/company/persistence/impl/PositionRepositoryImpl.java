package com.solvd.company.persistence.impl;

import com.solvd.company.domain.Position;
import com.solvd.company.domain.exception.DataNotFoundException;
import com.solvd.company.domain.exception.ProcessException;
import com.solvd.company.persistence.ConnectionPool;
import com.solvd.company.persistence.PositionRepository;

import java.sql.*;
import java.util.Optional;

public class PositionRepositoryImpl implements PositionRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Position position) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "Insert into Positions (name, salary) values (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, position.getName());
            preparedStatement.setBigDecimal(2, position.getSalary());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                position.setId(resultSet.getLong(1));
            }
        } catch (SQLException ex) {
            throw new ProcessException("Can't create a Position", ex);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Position> findByName(String name) {
        Position result = null;

        Connection connection = CONNECTION_POOL.getConnection();
        String selectQueryByName = "SELECT * FROM Positions WHERE name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQueryByName);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = new Position();
                result.setId(resultSet.getLong(1));
            }
        } catch (SQLException ex) {
            throw new DataNotFoundException("Can't find Position by it's name", ex);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(result);
    }
}
