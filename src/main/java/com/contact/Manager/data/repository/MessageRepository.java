package com.contact.Manager.data.repository;

import com.contact.Manager.data.model.UserMessage;
import org.apache.logging.log4j.message.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<UserMessage,String> {
    UserMessage findByNumber(String number);

}
