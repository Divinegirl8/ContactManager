package com.contact.Manager.data.repository;

import com.contact.Manager.data.model.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContactRepositoryTest {
    @Autowired
    ContactRepository contactRepository;

    @Test
    void save_One_Count_Is_One(){
        contactRepository.save(new Contact());
        assertEquals(1,contactRepository.count());
    }


}