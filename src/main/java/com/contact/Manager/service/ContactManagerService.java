package com.contact.Manager.service;

import com.contact.Manager.data.model.CallHistoryEntry;
import com.contact.Manager.data.model.Contact;
import com.contact.Manager.data.model.UserMessage;
import com.contact.Manager.dtos.request.AddContactRequest;
import com.contact.Manager.dtos.request.ComposeMessageRequest;
import com.contact.Manager.dtos.request.DeleteMessageRequest;
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
    void composeMessage(ComposeMessageRequest composeMessageRequest);
    void deleteMessage(DeleteMessageRequest deleteMessageRequest);
    List<UserMessage> findAllMessages();

    void deleteAllMessage();
    UserMessage findMessage(String number);



}
