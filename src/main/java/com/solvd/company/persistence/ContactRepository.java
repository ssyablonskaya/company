package com.solvd.company.persistence;

import com.solvd.company.domain.Contact;

public interface ContactRepository {

    void create(Contact contact);

}
