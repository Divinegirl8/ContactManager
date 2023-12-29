package com.contact.Manager.data.repository;

import com.contact.Manager.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
}
