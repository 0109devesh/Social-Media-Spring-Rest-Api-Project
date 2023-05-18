package com.smapi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.smapi.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

	 User findByUsername(String username);
	
}
