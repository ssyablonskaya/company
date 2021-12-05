package com.solvd.company.persistence;

import com.solvd.company.domain.Department;
import org.apache.ibatis.annotations.Param;

public interface DepartmentRepository {

    void create(@Param("companyId") Long companyId, @Param("department") Department department);

}
