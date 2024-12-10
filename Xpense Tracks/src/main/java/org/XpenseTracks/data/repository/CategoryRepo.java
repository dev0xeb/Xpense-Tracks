package org.XpenseTracks.data.repository;

import org.XpenseTracks.data.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepo extends MongoRepository<Category, String> {
}
