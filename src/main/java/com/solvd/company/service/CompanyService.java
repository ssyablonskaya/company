package com.solvd.company.service;

import com.solvd.company.domain.Company;

import java.util.List;

public interface CompanyService {

    Company create(Company company);

    List<Company> getAll();

}
