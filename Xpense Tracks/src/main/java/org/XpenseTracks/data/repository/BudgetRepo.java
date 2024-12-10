package org.XpenseTracks.data.repository;

import org.XpenseTracks.data.model.Budget;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BudgetRepo extends MongoRepository<Budget, String> {
}
