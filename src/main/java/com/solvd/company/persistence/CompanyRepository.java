package com.solvd.company.persistence;

import com.solvd.company.domain.Company;

import java.util.List;

public interface CompanyRepository {

    void create(Long addressId, Long contactId, Company company);

    //List<Company> findAll();

}
