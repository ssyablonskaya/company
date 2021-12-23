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

    @Override
    public void update(Long id, String phone, String email, String website) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ContactRepository contactRepository = session.getMapper(ContactRepository.class);
            contactRepository.update(id, phone, email, website);
        }
    }

    @Override
    public void delete(Contact contact) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ContactRepository contactRepository = session.getMapper(ContactRepository.class);
            contactRepository.delete(contact);
        }
    }

    //как у кс
    @Override
    public Contact findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ContactRepository contactRepository = session.getMapper(ContactRepository.class);
            Contact contact = contactRepository.findById(id);
            return contact;
        }
    }

    @Override
    public Contact findByPhone(String phone) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ContactRepository contactRepository = session.getMapper(ContactRepository.class);
            Contact contact = contactRepository.findByPhone(phone);
            return contact;
        }

    }
}