package com.solvd.company.persistence.impl;

import com.solvd.company.domain.PayrollAccount;
import com.solvd.company.domain.exception.ProcessException;
import com.solvd.company.persistence.ConnectionPool;
import com.solvd.company.persistence.PayrollAccountRepository;

import java.sql.*;

public class PayrollAccountRepositoryImpl implements PayrollAccountRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(PayrollAccount payrollAccount) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "Insert into Payroll_accounts (bank, account_number) values (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, payrollAccount.getBank());
            preparedStatement.setString(2, payrollAccount.getBankAccount());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                payrollAccount.setId(resultSet.getLong(1));
            }
        } catch (SQLException ex) {
            throw new ProcessException("Can't create a Payroll account", ex);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

}
