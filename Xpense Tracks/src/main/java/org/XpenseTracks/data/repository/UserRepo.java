package org.XpenseTracks.data.repository;

import org.XpenseTracks.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
}
