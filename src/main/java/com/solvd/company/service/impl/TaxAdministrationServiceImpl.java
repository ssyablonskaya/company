package com.solvd.company.service.impl;

import com.solvd.company.domain.TaxAdministration;
import com.solvd.company.persistence.TaxAdministrationRepository;
import com.solvd.company.persistence.impl.TaxAdministrationRepositoryImpl;
import com.solvd.company.service.TaxAdministrationService;

public class TaxAdministrationServiceImpl implements TaxAdministrationService {

    private final TaxAdministrationRepository taxAdministrationRepository;

    public TaxAdministrationServiceImpl() {
        this.taxAdministrationRepository = new TaxAdministrationRepositoryImpl();
    }

    @Override
    public TaxAdministration create(Long companyId, TaxAdministration taxAdministration) {
        taxAdministration.setId(null);
        taxAdministrationRepository.create(companyId, taxAdministration);
        return taxAdministration;
    }

}
