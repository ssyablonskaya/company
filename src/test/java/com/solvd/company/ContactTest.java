package com.solvd.company;

import com.solvd.company.domain.Contact;
import com.solvd.company.persistence.ContactRepository;
import com.solvd.company.persistence.mybatisImpl.ContactMyBatisRepository;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class ContactTest {

    private final ContactRepository contactRepository = new ContactMyBatisRepository();
    public Contact contactTest = new Contact();

    @BeforeTest
    public void beforeTest() {
        System.out.println("Test is running...\n");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("\nTest was ended.");
    }

    @BeforeGroups(groups = "findBySmth")
    public void beforeGroups() {
        System.out.println("Group \"findBySmth\" is testing...\n");
    }

    @AfterGroups(groups = "findBySmth")
    public void afterGroups() {
        System.out.println("\nGroup \"findBySmth\" was ended.");
    }

    @BeforeClass
    public void createContactTestBefore() {
        contactTest.setPhoneNumber("1111");
        contactTest.setEmail("test@gmail.com");
        contactTest.setWebsite("test.com");
    }

    @AfterClass
    public void deleteContactTestAfter() {
        contactTest = null;
    }

    @Test
    public void testCaseVerifyCreateContact() {
        contactRepository.create(contactTest);
        Contact filledContact = contactRepository.findByPhone(contactTest.getPhoneNumber());
        Assert.assertNotNull(filledContact, "Contact wasn't created");
    }

    @Test(groups = "findBySmth")
    public void testCaseVerifyFindByIdContact() {
        Contact findContactById = contactRepository.findById(4L);
        Assert.assertNotNull(findContactById, "Contact wasn't found by id");
    }

    @Test(groups = "findBySmth")
    public void testCaseVerifyFindByPhoneContact() {
        Contact findContactByPhone = contactRepository.findByPhone("1111");
        Assert.assertNotNull(findContactByPhone, "Contact wasn't found by phone");
    }

    @DataProvider
    public Object[][] updateDataProvider() {
        return new Object[][]{
                {5L, "+1234", "update@gmail.com", "update.com"},
                {6L, "+7777", "data_provider2@gmail.com", "data.provider.com"}
        };
    }

    @Test(dataProvider = "updateDataProvider")
    public void testCaseVerifyUpdateContact(Long id, String phone, String email, String website) {
        contactRepository.update(id, phone, email, website);
        Contact updatedContact = contactRepository.findById(id);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(updatedContact.getPhoneNumber(), phone, "Contact's phone wasn't updated");
        softAssert.assertEquals(updatedContact.getEmail(), email, "Contact's email wasn't updated");
        //for checking SoftAssert
        //updatedContact.setWebsite("notupdated.com");
        softAssert.assertEquals(updatedContact.getWebsite(), website, "Contact's website wasn't updated");
        softAssert.assertAll();
    }

    @Test
    public void testCaseVerifyDeleteContact() {
        contactRepository.delete(contactTest);
        Contact deletedContact = contactRepository.findByPhone("1111");
        Assert.assertNull(deletedContact, "Contact wasn't deleted");
    }


}
