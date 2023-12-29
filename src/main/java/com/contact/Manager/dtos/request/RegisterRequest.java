package com.contact.Manager.dtos.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String number;
    private String firstName;
    private String lastName;
    private String emailAddress;
}
