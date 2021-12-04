package com.solvd.company.service.impl;

import com.solvd.company.domain.Service;
import com.solvd.company.persistence.ServiceRepository;
import com.solvd.company.persistence.impl.ServiceRepositoryImpl;

import com.solvd.company.service.ServiceService;

public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl() {
        this.serviceRepository = new ServiceRepositoryImpl();
    }

    @Override
    public Service create(Long companyId, Service service) {
        service.setId(null);
        serviceRepository.create(companyId, service);
        return service;
    }

}
