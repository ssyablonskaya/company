package com.solvd.company.persistence.mybatisImpl;

import com.solvd.company.domain.Employee;
import com.solvd.company.persistence.EmployeeRepository;
import com.solvd.company.persistence.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

public class EmployeeMyBatisRepository implements EmployeeRepository {

    @Override
    public void create(Long departmentId, Long positionId, Long payrollAccountId, Employee employee) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = session.getMapper(EmployeeRepository.class);
            employeeRepository.create(departmentId, positionId, payrollAccountId, employee);
        }
    }
}
