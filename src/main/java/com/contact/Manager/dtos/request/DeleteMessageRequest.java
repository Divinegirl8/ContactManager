package com.contact.Manager.dtos.request;

import lombok.Data;

@Data
public class DeleteMessageRequest {
    private String number;
    private String message;
}
