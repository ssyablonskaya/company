package com.solvd.company.persistence;

import com.solvd.company.domain.Contact;
import org.apache.ibatis.annotations.Param;

public interface ContactRepository {

    void create(Contact contact);

    void update(@Param("id") Long id, @Param("phoneNumber") String phone,
                @Param("email") String email, @Param("website") String website);

    void delete(Contact contact);

    Contact findById(Long id);

    Contact findByPhone(String phone);

}
