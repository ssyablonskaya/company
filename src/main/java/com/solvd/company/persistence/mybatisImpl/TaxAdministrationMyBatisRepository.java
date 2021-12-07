package com.solvd.company.persistence.mybatisImpl;

import com.solvd.company.domain.TaxAdministration;
import com.solvd.company.persistence.MyBatisSessionHolder;
import com.solvd.company.persistence.TaxAdministrationRepository;
import org.apache.ibatis.session.SqlSession;

public class TaxAdministrationMyBatisRepository implements TaxAdministrationRepository {

    @Override
    public void create(Long companyId, TaxAdministration taxAdministration) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            TaxAdministrationRepository taxAdministrationRepository = session.getMapper(TaxAdministrationRepository.class);
            taxAdministrationRepository.create(companyId, taxAdministration);
        }
    }
}
