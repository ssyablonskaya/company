package com.solvd.company.persistence.jdbcImpl;

import com.solvd.company.domain.Employee;
import com.solvd.company.domain.exception.ProcessException;
import com.solvd.company.persistence.ConnectionPool;
import com.solvd.company.persistence.EmployeeRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Employee> mapEmployees(ResultSet resultSet) {
        List<Employee> employees = new ArrayList<>();
        try {
            long id = resultSet.getLong("employee_id");
            if (id != 0) {
                Employee employee = checkExistence(id, employees);
                employee.setFirstName(resultSet.getString("employee_first_name"));
                employee.setLastName(resultSet.getString("employee_last_name"));
                employee.setDob(resultSet.getTimestamp("employee_dob").toLocalDateTime().toLocalDate());
                employee.setYearOfEmployment(resultSet.getInt("employee_year_of_employment"));
            }
        } catch (SQLException ex) {
            throw new ProcessException("Map exception", ex);
        }
        return employees;
    }

    private static Employee checkExistence(Long id, List<Employee> employees) {
        Employee result = null;
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                result = employee;
            }
        }
        if (result == null) {
            Employee createdEmployee = new Employee();
            createdEmployee.setId(id);
            employees.add(createdEmployee);
            result = createdEmployee;
        }
        return result;
    }

}
