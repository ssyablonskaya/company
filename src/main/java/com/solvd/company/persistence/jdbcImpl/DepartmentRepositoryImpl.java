package com.solvd.company.persistence.jdbcImpl;

import com.solvd.company.domain.Department;
import com.solvd.company.domain.Employee;
import com.solvd.company.domain.exception.ProcessException;
import com.solvd.company.persistence.ConnectionPool;
import com.solvd.company.persistence.DepartmentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            throw new ProcessException("Can't create a Department", ex);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    public static List<Department> mapDepartments(ResultSet resultSet) {
        List<Department> departments = new ArrayList<>();
        try {
            Long id = resultSet.getLong("department_id");
            Department department = checkExistence(id, departments);
            department.setName(resultSet.getString("department_name"));

            List<Employee> employees = EmployeeRepositoryImpl.mapEmployees(resultSet);
            department.setEmployees(employees);
        } catch (SQLException ex) {
            throw new ProcessException("Map exception", ex);
        }
        return departments;
    }

    private static Department checkExistence(Long id, List<Department> departments) {
        Department result = null;
        for (Department department : departments) {
            if (department.getId().equals(id)) {
                result = department;
            }
        }
        if (result == null) {
            Department createdDepartment = new Department();
            createdDepartment.setId(id);
            departments.add(createdDepartment);
            result = createdDepartment;
        }
        return result;
    }

}
