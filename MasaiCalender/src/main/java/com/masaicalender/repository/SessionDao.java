package com.masaicalender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masaicalender.model.CurrentUserSession;

@Repository
public interface SessionDao extends JpaRepository<CurrentUserSession,String>{
	public CurrentUserSession findByUniqueId(String uniqueId);
}
