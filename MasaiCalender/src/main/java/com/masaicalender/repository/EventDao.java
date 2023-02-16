package com.masaicalender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masaicalender.model.Event;

@Repository
public interface EventDao extends JpaRepository<Event,Integer>{

}
