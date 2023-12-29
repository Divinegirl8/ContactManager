package com.contact.Manager.exception;

public class ContactNotFound extends RuntimeException{
    public ContactNotFound(String message){
        super(message);
    }
}
