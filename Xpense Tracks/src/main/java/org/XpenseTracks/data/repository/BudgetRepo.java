package org.XpenseTracks.data.repository;

import org.XpenseTracks.data.model.Budget;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BudgetRepo extends MongoRepository<Budget, String> {
//    Optional<Budget> findById(String id);
}
