package com.solvd.company.persistence.mybatisImpl;

import com.solvd.company.domain.Department;
import com.solvd.company.persistence.DepartmentRepository;
import com.solvd.company.persistence.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

public class DepartmentMyBatisRepository implements DepartmentRepository {

    @Override
    public void create(Long companyId, Department department) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            DepartmentRepository departmentRepository = session.getMapper(DepartmentRepository.class);
            departmentRepository.create(companyId, department);
        }
    }
}
