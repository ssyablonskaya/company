package com.solvd.company.persistence.jdbcImpl;

import com.solvd.company.domain.Contact;
import com.solvd.company.domain.exception.ProcessException;
import com.solvd.company.persistence.ConnectionPool;
import com.solvd.company.persistence.ContactRepository;

import java.sql.*;

public class ContactRepositoryImpl implements ContactRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Contact contact) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "Insert into Contacts (phone_number, email, website) values (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, contact.getPhoneNumber());
            preparedStatement.setString(2, contact.getEmail());
            preparedStatement.setString(3, contact.getWebsite());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                contact.setId(resultSet.getLong(1));
            }
        } catch (SQLException ex) {
            throw new ProcessException("Can't create a Contact", ex);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

}
