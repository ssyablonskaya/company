package com.solvd.company.service;

import com.solvd.company.domain.TaxAdministration;

public interface TaxAdministrationService {

    TaxAdministration create(Long companyId, TaxAdministration taxAdministration);

}
