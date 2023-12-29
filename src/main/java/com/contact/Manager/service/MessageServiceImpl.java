package com.contact.Manager.service;

import com.contact.Manager.data.model.Contact;
import com.contact.Manager.data.model.UserMessage;
import com.contact.Manager.data.repository.ContactRepository;
import com.contact.Manager.data.repository.MessageRepository;
import com.contact.Manager.exception.MessageNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    ContactRepository contactRepository;
    @Override
    public void composeMessage(String number, String writeUp) {
        Contact contact = contactRepository.findContactByNumber(number);
        if (contact == null) {
           contact = new Contact();
           contact.setNumber(number);
            contactRepository.save(contact);
        }


        UserMessage userMessage = new UserMessage();
        userMessage.setNumber(contact.getNumber());
        userMessage.setComposeMessage(writeUp);
        messageRepository.save(userMessage);

    }

    @Override
    public void deleteMessage(String number, String writeUp) {

        UserMessage userMessage = messageRepository.findByNumber(number);

        if (userMessage != null && writeUp.equals(userMessage.getComposeMessage())) {
            messageRepository.delete(userMessage);
        } else {
            throw new MessageNotFound("Message not found");
        }
    }

    @Override
    public List<UserMessage> findAllMessage() {
        return new ArrayList<>(messageRepository.findAll());
    }

    @Override
    public void deleteAllMessages() {
        List<UserMessage> messages = findAllMessage();
        messageRepository.deleteAll(messages);
    }

    @Override
    public UserMessage findMessage(String number) {
        UserMessage userMessage = messageRepository.findByNumber(number);

        if (userMessage == null) throw new MessageNotFound("message not found");
        return userMessage;

    }


}
