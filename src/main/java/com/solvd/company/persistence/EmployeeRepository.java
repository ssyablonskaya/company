package com.solvd.company.persistence;

import com.solvd.company.domain.Employee;

public interface EmployeeRepository {

    void create(Long departmentId, Long positionId, Long payrollAccountId, Employee employee);

}
