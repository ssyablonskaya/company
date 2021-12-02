package com.solvd.company.persistence;

import com.solvd.company.domain.TaxAdministration;

public interface TaxAdministrationRepository {

    void create(Long companyId, TaxAdministration taxAdministration);

}
