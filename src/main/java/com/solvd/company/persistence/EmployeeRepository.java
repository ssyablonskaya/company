package com.solvd.company.persistence;

import com.solvd.company.domain.Employee;
import org.apache.ibatis.annotations.Param;

public interface EmployeeRepository {

    void create(@Param("departmentId") Long departmentId, @Param("positionId") Long positionId,
                @Param("payrollAccountId") Long payrollAccountId, @Param("employee") Employee employee);

}
