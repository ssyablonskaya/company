package com.solvd.company.persistence.mybatisImpl;

import com.solvd.company.domain.PayrollAccount;
import com.solvd.company.persistence.MyBatisSessionHolder;
import com.solvd.company.persistence.PayrollAccountRepository;
import org.apache.ibatis.session.SqlSession;

public class PayrollAccountMyBatisRepository implements PayrollAccountRepository {

    @Override
    public void create(PayrollAccount payrollAccount) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
             PayrollAccountRepository payrollAccountRepository = session.getMapper(PayrollAccountRepository.class);
             payrollAccountRepository.create(payrollAccount);
        }
    }
}