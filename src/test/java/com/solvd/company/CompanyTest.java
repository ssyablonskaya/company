package com.solvd.company;

import com.solvd.company.domain.Address;
import com.solvd.company.domain.Company;
import com.solvd.company.domain.Contact;
import com.solvd.company.persistence.AddressRepository;
import com.solvd.company.persistence.CompanyRepository;
import com.solvd.company.persistence.ContactRepository;
import com.solvd.company.persistence.mybatisImpl.AddressMyBatisRepository;
import com.solvd.company.persistence.mybatisImpl.CompanyMyBatisRepository;
import com.solvd.company.persistence.mybatisImpl.ContactMyBatisRepository;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CompanyTest {


    private final CompanyRepository companyRepository = new CompanyMyBatisRepository();
    private final Company companyTest = new Company();
    private final ContactRepository contactRepository = new ContactMyBatisRepository();
    private final Contact contactTest = new Contact();
    private final AddressRepository addressRepository = new AddressMyBatisRepository();
    private final Address addressTest = new Address();

    @BeforeTest
    public void beforeTest() {
        System.out.println("Test is running...\n");
    }

    @BeforeGroups(groups = "findBySmth")
    public void beforeGroups() {
        System.out.println("Group \"findBySmth\" is testing...\n");
    }

    @Test
    public void verifyCreateCompanyTest() {
        addressTest.setCountry("Test");
        addressTest.setCity("Test");
        addressTest.setStreet("Test");
        addressTest.setHouse("test");
        addressRepository.create(addressTest);

        contactTest.setPhoneNumber("0000");
        contactTest.setEmail("test email");
        contactTest.setWebsite("test website");
        contactRepository.create(contactTest);

        companyTest.setName("Test Company");
        companyTest.setSphere("test sphere");
        companyTest.setAddress(addressTest);
        companyTest.setContact(contactTest);

        companyRepository.create(companyTest.getAddress().getId(), companyTest.getContact().getId(), companyTest);
        Company filledCompany = companyRepository.findCompanyById(companyTest.getId());
        Assert.assertNotNull(filledCompany, "Company wasn't created");

    }

    @Test(groups = "findBySmth")
    public void verifyFindByIdCompanyTest() {
        Company findCompanyById = companyRepository.findAllCompanyInfoAddressesContactsById(9L);
        Assert.assertNotNull(findCompanyById, "Company's info about address and contacts wasn't found by id");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("\nTest was ended.");
    }

}
