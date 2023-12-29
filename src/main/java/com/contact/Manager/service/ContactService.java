package com.contact.Manager.service;

import com.contact.Manager.data.model.CallHistoryEntry;
import com.contact.Manager.data.model.Contact;

import java.util.List;

public interface ContactService {
    Contact addContact(String name, String number);
    List<Contact> findAllContacts();

    Contact findContact(String name);

    void deleteContact(String number);

    void blockContact(String name);

    void deleteAllContact();

    void dialNumber(String number);

    List<CallHistoryEntry>  callHistory();

}
