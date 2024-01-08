package com.contact.Manager.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Contact {
    @Id
    private String number;
    private String name;
    private boolean blockContact;

}
