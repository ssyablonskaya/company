package com.solvd.company.domain;

import java.time.LocalDate;

public class Employee {

    private Long id;
    //private Department department;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private Integer yearOfEmployment;
    private Position position;
    private PayrollAccount payrollAccount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getYearOfEmployment() {
        return yearOfEmployment;
    }

    public void setYearOfEmployment(Integer yearOfEmployment) {
        this.yearOfEmployment = yearOfEmployment;
    }

    public PayrollAccount getPayrollAccount() {
        return payrollAccount;
    }

    public void setPayrollAccount(PayrollAccount payrollAccount) {
        this.payrollAccount = payrollAccount;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


}
