package com.solvd.company.persistence;

import com.solvd.company.domain.Company;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyRepository {

    void create(@Param("addressId") Long addressId, @Param("contactId") Long contactId, @Param("company") Company company);

    void delete(Long deleteId);

    List<Company> findAll();

}
