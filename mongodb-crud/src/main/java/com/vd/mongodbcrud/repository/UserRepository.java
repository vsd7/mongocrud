package com.vd.mongodbcrud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vd.mongodbcrud.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

	
}
