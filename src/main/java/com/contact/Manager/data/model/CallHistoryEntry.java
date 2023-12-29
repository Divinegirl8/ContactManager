package com.contact.Manager.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class CallHistoryEntry {
    @Id
    private String number;
    private LocalDateTime localDateTime;
}
