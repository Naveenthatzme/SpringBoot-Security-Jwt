package com.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	
	User findByUserMail(String userMail);
}
