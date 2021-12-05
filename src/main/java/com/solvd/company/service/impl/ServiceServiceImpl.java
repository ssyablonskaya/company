package com.solvd.company.service.impl;

import com.solvd.company.domain.Service;
import com.solvd.company.persistence.ServiceRepository;
import com.solvd.company.persistence.jdbcImpl.ServiceRepositoryImpl;

import com.solvd.company.persistence.mybatisImpl.ServiceMyBatisRepository;
import com.solvd.company.service.ServiceService;

public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl() {
        //this.serviceRepository = new ServiceRepositoryImpl();
        this.serviceRepository = new ServiceMyBatisRepository();
    }

    @Override
    public Service create(Long companyId, Service service) {
        service.setId(null);
        serviceRepository.create(companyId, service);
        return service;
    }

}
