package org.XpenseTracks.data.repository;

import org.XpenseTracks.data.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepo extends MongoRepository<Transaction, String> {
}
