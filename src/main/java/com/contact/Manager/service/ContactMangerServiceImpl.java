package com.contact.Manager.service;

import com.contact.Manager.data.model.CallHistoryEntry;
import com.contact.Manager.data.model.Contact;
import com.contact.Manager.data.model.User;
import com.contact.Manager.data.repository.CallHistoryRepository;
import com.contact.Manager.data.repository.ContactRepository;
import com.contact.Manager.data.repository.UserRepository;
import com.contact.Manager.dtos.request.AddContactRequest;
import com.contact.Manager.dtos.request.RegisterRequest;
import com.contact.Manager.exception.ContactExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.contact.Manager.utils.Mappers.*;

@Service
public class ContactMangerServiceImpl implements ContactManagerService {
    @Autowired
    private CallHistoryRepository callHistoryRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactServiceImpl contactService;
    @Autowired
    private UserRepository userRepository;


    @Override
    public void addProfile(RegisterRequest registerRequest) {
        User user = map(registerRequest.getNumber(),registerRequest.getFirstName(),registerRequest.getLastName(),registerRequest.getEmailAddress());
        userRepository.save(user);
    }

    @Override
    public Contact addContact(AddContactRequest addContactRequest) {
        if (contactExists(addContactRequest.getNumber())) {
            throw new ContactExist("Contact with the same name or number already exists");
        }

        if (nameExist(addContactRequest.getName())) {
            throw new ContactExist("Contact with the same name or number already exists");
        }

        Contact contact = contactService.addContact(addContactRequest.getName(), addContactRequest.getNumber());
        contactRepository.save(contact);
        return contact;
    }

    private boolean contactExists(String number){
        Contact contact = contactRepository.findContactByNumber(number);
        return contact != null;
    }

    private boolean nameExist(String name){
        Contact contact = contactRepository.findContactByName(name);
        return contact != null;
    }


    @Override
    public Contact findContact(String name) {
        return contactService.findContact(name);
    }

    @Override
    public void deleteContact(String number) {
     contactService.deleteContact(number);
    }

    @Override
    public void blockContact(String name) {
    contactService.blockContact(name);
    }

    @Override
    public void deleteAllContact() {
     contactService.deleteAllContact();
    }

    @Override
    public void dialNumber(String number) {
     contactService.dialNumber(number);
    }

    @Override
    public List<CallHistoryEntry> callHistory(){
        return contactService.callHistory();
    }
}
