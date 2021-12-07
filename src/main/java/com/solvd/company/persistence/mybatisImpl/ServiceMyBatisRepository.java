package com.solvd.company.persistence.mybatisImpl;

import com.solvd.company.domain.Service;
import com.solvd.company.persistence.MyBatisSessionHolder;
import com.solvd.company.persistence.ServiceRepository;
import org.apache.ibatis.session.SqlSession;

public class ServiceMyBatisRepository implements ServiceRepository {

    @Override
    public void create(Long companyId, Service service) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ServiceRepository serviceRepository = session.getMapper(ServiceRepository.class);
            serviceRepository.create(companyId, service);
        }
    }

    @Override
    public void update(Service service, String name) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ServiceRepository serviceRepository = session.getMapper(ServiceRepository.class);
            serviceRepository.update(service, name);
        }
    }
}