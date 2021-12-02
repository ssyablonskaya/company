package com.solvd.company.persistence;

import com.solvd.company.domain.Service;

public interface ServiceRepository {

    void create(Long companyId, Service service);

    void update();

    void delete();

}
