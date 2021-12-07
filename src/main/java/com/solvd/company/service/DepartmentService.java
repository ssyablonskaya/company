package com.solvd.company.service;

import com.solvd.company.domain.Department;

public interface DepartmentService {

    Department create(Long companyId, Department department);


}
