package com.contact.Manager.data.repository;

import com.contact.Manager.data.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<Contact,String> {
   Contact findContactByName(String name);
   Contact findContactByNumber(String number);
}
