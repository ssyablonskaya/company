package com.solvd.company;

import java.time.LocalDateTime;

public class Employee {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime dob;
    private Position position;
    private Integer yearOfEmployment;
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

    public LocalDateTime getDob() {
        return dob;
    }

    public void setDob(LocalDateTime dob) {
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
