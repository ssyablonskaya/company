package com.solvd.company.persistence.impl;

import com.solvd.company.domain.Department;
import com.solvd.company.domain.exception.ProcessException;
import com.solvd.company.persistence.ConnectionPool;
import com.solvd.company.persistence.DepartmentRepository;

import java.sql.*;

public class DepartmentRepositoryImpl implements DepartmentRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Long companyId, Department department) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "Insert into Departments (company_id, name) values (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, companyId);
            preparedStatement.setString(2, department.getName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                department.setId(resultSet.getLong(1));
            }
        } catch (SQLException ex) {
            throw new ProcessException("Can't create department", ex);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

}
