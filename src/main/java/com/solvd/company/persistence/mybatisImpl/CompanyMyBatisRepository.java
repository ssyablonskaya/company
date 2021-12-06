package com.solvd.company.persistence.mybatisImpl;

import com.solvd.company.domain.Company;
import com.solvd.company.persistence.CompanyRepository;
import com.solvd.company.persistence.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CompanyMyBatisRepository implements CompanyRepository {

    @Override
    public void create(Long addressId, Long contactId, Company company) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            CompanyRepository companyRepository = session.getMapper(CompanyRepository.class);
            companyRepository.create(addressId, contactId, company);
        }
    }

    @Override
    public void delete(Long deleteId) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            CompanyRepository companyRepository = session.getMapper(CompanyRepository.class);
            companyRepository.delete(deleteId);
        }
    }

    @Override
    public List<Company> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            CompanyRepository companyRepository = session.getMapper(CompanyRepository.class);
            return companyRepository.findAll();
            //List<Company> companies = companyRepository.findAll();
            //return companies;
        }
    }
}
