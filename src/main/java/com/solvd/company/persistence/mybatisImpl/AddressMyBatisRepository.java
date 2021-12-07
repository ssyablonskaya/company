package com.solvd.company.persistence.mybatisImpl;

import com.solvd.company.domain.Address;
import com.solvd.company.persistence.AddressRepository;
import com.solvd.company.persistence.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

public class AddressMyBatisRepository implements AddressRepository {

    @Override
    public void create(Address address) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            AddressRepository addressRepository = session.getMapper(AddressRepository.class);
            addressRepository.create(address);
        }
    }
}