package com.solvd.company.service.impl;

import com.solvd.company.domain.Department;
import com.solvd.company.persistence.DepartmentRepository;
import com.solvd.company.persistence.jdbcImpl.DepartmentRepositoryImpl;
import com.solvd.company.persistence.mybatisImpl.DepartmentMyBatisRepository;
import com.solvd.company.service.DepartmentService;
import com.solvd.company.service.EmployeeService;

public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeService employeeService;

    public DepartmentServiceImpl() {
        //this.departmentRepository = new DepartmentRepositoryImpl();
        this.departmentRepository = new DepartmentMyBatisRepository();
        this.employeeService = new EmployeeServiceImpl();
    }

    @Override
    public Department create(Long companyId, Department department) {
        department.setId(null);
        departmentRepository.create(companyId, department);
        if (department.getEmployees() != null) {
            department.getEmployees()
                    .forEach(employee -> employeeService.create(department.getId(), employee));
        }


        return department;
    }

}
