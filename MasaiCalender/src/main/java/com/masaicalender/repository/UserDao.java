package com.masaicalender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masaicalender.model.User;

@Repository
public interface UserDao extends JpaRepository<User,String>{
	
}
