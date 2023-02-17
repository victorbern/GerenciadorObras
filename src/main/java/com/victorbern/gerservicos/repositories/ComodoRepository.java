package com.victorbern.gerservicos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victorbern.gerservicos.models.Comodo;

public interface ComodoRepository extends JpaRepository<Comodo, Long>{
	
	List<Comodo> findByObraId(Long id);
}
