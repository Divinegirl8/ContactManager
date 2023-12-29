package com.contact.Manager.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class User {
    @Id
    private String number;
    private String firstName;
    private String lastName;
    private String emailAddress;
}
