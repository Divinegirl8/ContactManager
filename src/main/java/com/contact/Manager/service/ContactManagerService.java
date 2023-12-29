package com.contact.Manager.service;

import com.contact.Manager.data.model.CallHistoryEntry;
import com.contact.Manager.data.model.Contact;
import com.contact.Manager.dtos.request.AddContactRequest;
import com.contact.Manager.dtos.request.RegisterRequest;

import java.util.List;

public interface ContactManagerService {
    void addProfile(RegisterRequest registerRequest);
    Contact addContact(AddContactRequest addContactRequest);
    Contact findContact(String name);
    void deleteContact(String number);
    void blockContact(String name);
    void deleteAllContact();
    void dialNumber(String number);
    List<CallHistoryEntry> callHistory();



}
