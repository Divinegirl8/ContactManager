package com.contact.Manager.service;

import com.contact.Manager.data.model.User;
import com.contact.Manager.data.model.UserMessage;

import java.util.List;

public interface MessageService {
    void composeMessage(String number, String writeUp);
    void deleteMessage(String number, String writeUp);

    List<UserMessage> findAllMessage();

    void deleteAllMessages();

    UserMessage findMessage(String number);



}
