package com.contact.Manager.utils;

import com.contact.Manager.data.model.User;

public class Mappers {
    public static User map(String number,String firstName, String lastName, String emailAddress){
        User user = new User();

        user.setNumber(number);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmailAddress(emailAddress);
        return user;
    }

}
