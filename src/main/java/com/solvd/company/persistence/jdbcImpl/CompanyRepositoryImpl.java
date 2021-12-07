package com.solvd.company.persistence.jdbcImpl;

import com.solvd.company.domain.Company;
import com.solvd.company.domain.Department;
import com.solvd.company.domain.exception.DataNotFoundException;
import com.solvd.company.domain.exception.ProcessException;
import com.solvd.company.persistence.CompanyRepository;
import com.solvd.company.persistence.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepositoryImpl implements CompanyRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String FIND_ALL_DEPS_N_EMPLOYEES = "SELECT "
            + "c.id AS company_id, c.name AS company_name, c.sphere AS company_sphere, "
            + "d.id AS department_id, d.name AS department_name, "
            + "e.id AS employee_id, e.first_name AS employee_first_name, e.last_name AS employee_last_name, e.dob AS employee_dob,"
            + "e.year_of_employment AS employee_year_of_employment "
            + "FROM Companies c "
            + "LEFT JOIN Departments d ON c.id = d.company_id "
            + "LEFT JOIN Employees e ON d.id = e.department_id;";


    @Override
    public void create(Long addressId, Long contactId, Company company) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "Insert into Companies(address_id, contact_id, name, sphere) values (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, addressId);
            preparedStatement.setLong(2, contactId);
            preparedStatement.setString(3, company.getName());
            preparedStatement.setString(4, company.getSphere());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                company.setId(resultSet.getLong(1));
            }
        } catch (SQLException ex) {
            throw new ProcessException("Can't create a Company", ex);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Company> findAll() {
        List<Company> companies = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_DEPS_N_EMPLOYEES);
            ResultSet resultSet = preparedStatement.executeQuery();
            companies = mapCompanies(resultSet);
        } catch (SQLException ex) {
            throw new DataNotFoundException("Can't find departments and employees ", ex);
        }
        return companies;
    }


    public static List<Company> mapCompanies(ResultSet resultSet) {
        List<Company> companies = new ArrayList<>();
        List<Department> departments = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Long id = resultSet.getLong("company_id");
                Company company = checkExistence(id, companies);
                company.setName(resultSet.getString("company_name"));
                company.setSphere(resultSet.getString("company_sphere"));

                departments.addAll(DepartmentRepositoryImpl.mapDepartments(resultSet));
                company.setDepartments(departments);
            }
        } catch (SQLException ex) {
            throw new ProcessException("Map exception", ex);
        }
        return companies;
    }

    private static Company checkExistence(Long id, List<Company> companies) {
        Company result = null;
        for (Company company : companies) {
            if (company.getId().equals(id)) {
                result = company;
            }
        }
        if (result == null) {
            Company createdCompany = new Company();
            createdCompany.setId(id);
            companies.add(createdCompany);
            result = createdCompany;
        }
        return result;
    }

    @Override
    public void delete(Long deleteId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "DELETE FROM Companies WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setLong(1, deleteId);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new ProcessException("Can't delete a Company", ex);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
