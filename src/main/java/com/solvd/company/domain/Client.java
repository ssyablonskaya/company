package com.solvd.company.domain;

import java.time.LocalDate;

public class Client {

    private Long id;
    private String name;
    private LocalDate dateOfCooperation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfCooperation() {
        return dateOfCooperation;
    }

    public void setDateOfCooperation(LocalDate dateOfCooperation) {
        this.dateOfCooperation = dateOfCooperation;
    }
}
