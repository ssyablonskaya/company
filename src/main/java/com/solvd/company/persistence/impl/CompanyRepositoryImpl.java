package com.solvd.company.persistence.impl;

import com.solvd.company.domain.Company;
import com.solvd.company.domain.exception.ProcessException;
import com.solvd.company.persistence.CompanyRepository;
import com.solvd.company.persistence.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepositoryImpl implements CompanyRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String FIND_ALL = "SELECT "
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


/*    @Override
    public List<Company> findAll() {
        List<Company> companies;

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            companies = mapCompanies(resultSet);
        } catch (SQLException ex) {
            throw new ProcessException("Can't find all Companies", ex);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return companies;
    }*/

/*    public static List<Company> mapCompanies(ResultSet resultSet) throws SQLException {
        List<Company> companies = new ArrayList<>();
        while (resultSet.next()) {
            Long id = resultSet.getLong("company_id");

            Company company = findById(id, companies);
            company.setName(resultSet.getString("company_name"));
            company.setSphere(resultSet.getString("company_sphere"));
            //....more more

            List<Department> departments = DepartmentRepositoryImpl.mapRaw(resultSet, company.getDepartments());
            company.setDepartments(departments);
        }
        return companies;
    }*/

/*    private static Company findById(Long id, List<Company> companies) {
        return companies.stream()
                .filter(company -> company.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Company createdCompany = new Company();
                    createdCompany.setId(id);
                    companies.add(createdCompany);
                    return createdCompany;
                });
    }*/


}
