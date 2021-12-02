package com.solvd.company.persistence.impl;

import com.solvd.company.domain.TaxAdministration;
import com.solvd.company.domain.exception.ProcessException;
import com.solvd.company.persistence.ConnectionPool;
import com.solvd.company.persistence.TaxAdministrationRepository;

import java.sql.*;

public class TaxAdministrationRepositoryImpl implements TaxAdministrationRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Long companyId, TaxAdministration taxAdministration) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "Insert into Tax_administration (company_id, number, bank, bank_account) values (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, companyId);
            preparedStatement.setString(2, taxAdministration.getNumber());
            preparedStatement.setString(3, taxAdministration.getBank());
            preparedStatement.setString(4, taxAdministration.getBankAccount());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                taxAdministration.setId(resultSet.getLong(1));
            }
        } catch (SQLException ex) {
            throw new ProcessException("Can't create a Tax Administration", ex);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

}
