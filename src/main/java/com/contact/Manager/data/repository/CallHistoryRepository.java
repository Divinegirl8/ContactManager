package com.contact.Manager.data.repository;

import com.contact.Manager.data.model.CallHistoryEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CallHistoryRepository extends MongoRepository<CallHistoryEntry, String> {
}
