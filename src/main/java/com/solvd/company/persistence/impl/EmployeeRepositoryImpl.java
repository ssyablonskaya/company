package com.solvd.company.persistence.impl;

import com.solvd.company.domain.Employee;
import com.solvd.company.domain.exception.ProcessException;
import com.solvd.company.persistence.ConnectionPool;
import com.solvd.company.persistence.EmployeeRepository;

import java.sql.*;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    public void create(Long departmentId, Long positionId, Long payrollAccountId, Employee employee) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "Insert into Employees(department_id, position_id, payroll_account_id, first_name, last_name, dob, year_of_employment) values (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, departmentId);
            preparedStatement.setLong(2, positionId);
            preparedStatement.setLong(3, payrollAccountId);
            preparedStatement.setString(4, employee.getFirstName());
            preparedStatement.setString(5, employee.getLastName());
            preparedStatement.setDate(6, Date.valueOf(employee.getDob()));
            preparedStatement.setInt(7, employee.getYearOfEmployment());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                employee.setId(resultSet.getLong(1));
            }
        } catch (SQLException ex) {
            throw new ProcessException("Can't create an Employee", ex);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

}
