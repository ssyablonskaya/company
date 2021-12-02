package com.solvd.company.persistence;

import com.solvd.company.domain.Department;

public interface DepartmentRepository {

    void create(Long companyId, Department department);

}
