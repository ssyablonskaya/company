package com.solvd.company.persistence.jdbcImpl;

import com.solvd.company.domain.Address;
import com.solvd.company.domain.exception.ProcessException;
import com.solvd.company.persistence.AddressRepository;
import com.solvd.company.persistence.ConnectionPool;

import java.sql.*;

public class AddressRepositoryImpl implements AddressRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Address address) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "Insert into Addresses (country, city, street, house) values (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement
                    = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, address.getCountry());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getStreet());
            preparedStatement.setString(4, address.getHouse());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                address.setId(resultSet.getLong(1));
            }
        } catch (SQLException ex) {
            throw new ProcessException("Can't create an Address", ex);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

    }

}
