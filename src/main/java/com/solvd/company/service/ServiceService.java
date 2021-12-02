package com.solvd.company.service;

import com.solvd.company.domain.Service;

public interface ServiceService {

    Service create(Long companyId, Service service);

}
