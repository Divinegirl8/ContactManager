package com.contact.Manager.service;

import com.contact.Manager.data.model.CallHistoryEntry;
import com.contact.Manager.data.model.Contact;
import com.contact.Manager.data.repository.CallHistoryRepository;
import com.contact.Manager.data.repository.ContactRepository;
import com.contact.Manager.exception.ContactNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService{
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    CallHistoryRepository callHistoryRepository;
    @Override
    public Contact addContact(String name, String number) {
     Contact contact = new Contact();
     contact.setName(name);
     contact.setNumber(number);
     contactRepository.save(contact);
     return contact;
    }

    @Override
    public List<Contact> findAllContacts() {
        return new ArrayList<>(contactRepository.findAll());
    }

    @Override
    public Contact findContact(String name) {
        Contact contact = contactRepository.findContactByName(name);

        if (contact == null) throw  new ContactNotFound(name+ " not found");
        return contact;
    }

    @Override
    public void deleteContact(String number) {
        Contact contact = contactRepository.findContactByNumber(number);
        if (contact == null) throw  new ContactNotFound(number + " not found");
        contactRepository.delete(contact);
    }

    @Override
    public void blockContact(String name) {
     Contact contact = contactRepository.findContactByName(name);
        if (contact == null) throw  new ContactNotFound(name+ " not found");

        contact.setBlockContact(true);
        contactRepository.save(contact);
    }

    @Override
    public void deleteAllContact() {
     List<Contact> contacts = findAllContacts();
     contactRepository.deleteAll(contacts);
    }

    @Override
    public void dialNumber(String number) {
        CallHistoryEntry callHistoryEntry = new CallHistoryEntry();
        callHistoryEntry.setNumber(number);
        callHistoryEntry.setLocalDateTime(LocalDateTime.now());
        callHistoryRepository.save(callHistoryEntry);

    }

    @Override
    public List<CallHistoryEntry> callHistory() {

        return new ArrayList<>(callHistoryRepository.findAll());

    }



}
