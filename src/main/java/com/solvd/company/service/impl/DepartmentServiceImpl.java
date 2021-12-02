package com.solvd.company.service.impl;

import com.solvd.company.domain.Department;
import com.solvd.company.persistence.DepartmentRepository;
import com.solvd.company.persistence.impl.DepartmentRepositoryImpl;
import com.solvd.company.service.DepartmentService;
import com.solvd.company.service.EmployeeService;

public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    public Department create(Long companyId, Department department) {

        if (department.getEmployees() != null) {
            department.getEmployees()
                    .forEach(employee -> employeeService.create(department.getId(), employee));
        }

        department.setId(null);
        departmentRepository.create(companyId, department);
        return department;
    }

}
