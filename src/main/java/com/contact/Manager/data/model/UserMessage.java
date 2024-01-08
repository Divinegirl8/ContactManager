package com.contact.Manager.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class UserMessage {
    @Id
    private String number;
    private String composeMessage;
    private LocalDateTime localDateTime;
}
