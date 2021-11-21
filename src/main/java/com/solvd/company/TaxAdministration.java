package com.solvd.company;

public class TaxAdministration {

    private Long id;
    private Company companyID;
    private String number;
    private String bank;
    private String bankAccount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompanyID() {
        return companyID;
    }

    public void setCompany(Company companyID) {
        this.companyID = companyID;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
}
