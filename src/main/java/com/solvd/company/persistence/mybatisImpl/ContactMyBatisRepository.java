package com.solvd.company.persistence.mybatisImpl;

import com.solvd.company.domain.Contact;
import com.solvd.company.persistence.ContactRepository;
import com.solvd.company.persistence.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

public class ContactMyBatisRepository implements ContactRepository {

    @Override
    public void create(Contact contact) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ContactRepository contactRepository = session.getMapper(ContactRepository.class);
            contactRepository.create(contact);
        }
    }
}