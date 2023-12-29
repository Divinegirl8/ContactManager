package com.contact.Manager.data.repository;

import com.contact.Manager.data.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test void save_One_Count_Is_One(){
        userRepository.save(new User());
        assertEquals(1,userRepository.count());
    }

}