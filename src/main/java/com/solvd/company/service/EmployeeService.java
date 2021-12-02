package com.solvd.company.service;

import com.solvd.company.domain.Employee;

public interface EmployeeService {

    Employee create(Long departmentId, Employee employee);

}
