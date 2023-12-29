package com.contact.Manager.service;

import com.contact.Manager.data.repository.CallHistoryRepository;
import com.contact.Manager.data.repository.ContactRepository;
import com.contact.Manager.data.repository.UserRepository;
import com.contact.Manager.dtos.request.AddContactRequest;
import com.contact.Manager.dtos.request.RegisterRequest;
import com.contact.Manager.exception.ContactNotFound;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContactMangerServiceImplTest {

    @Autowired
    ContactMangerServiceImpl contactMangerService;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    CallHistoryRepository callHistoryRepository;

    @AfterEach
    void cleanUp(){
        contactRepository.deleteAll();
        userRepository.deleteAll();
        callHistoryRepository.deleteAll();
    }

    @Test void addProfile_Count_Is_One(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setNumber("09078675");
        registerRequest.setFirstName("firstname");
        registerRequest.setLastName("lastname");
        registerRequest.setEmailAddress("iam@");
        contactMangerService.addProfile(registerRequest);
        assertEquals(1,userRepository.count());
    }

    @Test void addContact_Count_Is_One(){
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setName("name");
        addContactRequest.setNumber("090876");
        contactMangerService.addContact(addContactRequest);
        assertEquals(1,contactRepository.count());
    }
    @Test void addContact_FindContact(){
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setName("name");
        addContactRequest.setNumber("090876");
        contactMangerService.addContact(addContactRequest);

        assertEquals(contactRepository.findContactByName("name"),contactMangerService.findContact("name"));
    }
    @Test void addContact_FindContact_With_Wrong_Name_Throw_Error(){
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setName("name");
        addContactRequest.setNumber("090876");
        contactMangerService.addContact(addContactRequest);

        assertThrows(ContactNotFound.class,()->contactMangerService.findContact("nam"));
    }
    @Test void addContact_Delete_Contact_FindContact_Throw_Error(){
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setName("name");
        addContactRequest.setNumber("090876");
        contactMangerService.addContact(addContactRequest);

        contactMangerService.deleteContact("090876");

        assertThrows(ContactNotFound.class,()->contactMangerService.findContact("name"));
    }

    @Test void addContact_BlockContact(){
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setName("name");
        addContactRequest.setNumber("090876");
        contactMangerService.addContact(addContactRequest);

        assertFalse(contactMangerService.findContact("name").isBlockContact());

        contactMangerService.blockContact("name");
        assertTrue(contactMangerService.findContact("name").isBlockContact());

    }


    @Test void addContactTwice_DeleteAllContact(){
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setName("name");
        addContactRequest.setNumber("090876");
        contactMangerService.addContact(addContactRequest);

        AddContactRequest addContactRequest2 = new AddContactRequest();
        addContactRequest2.setName("name2");
        addContactRequest2.setNumber("0908761");

       contactMangerService.addContact(addContactRequest2);
        assertEquals(2,contactRepository.count());
       contactMangerService.deleteAllContact();

       assertEquals(0,contactRepository.count());

    }

    @Test void addProfile_addContact_DialNumber() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setNumber("09078675");
        registerRequest.setFirstName("firstname");
        registerRequest.setLastName("lastname");
        registerRequest.setEmailAddress("iam@");
        contactMangerService.addProfile(registerRequest);

        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setName("name");
        addContactRequest.setNumber("090876");
        contactMangerService.addContact(addContactRequest);

        contactMangerService.dialNumber("090876");
        assertEquals(callHistoryRepository.findAll(),contactMangerService.callHistory());


    }



}