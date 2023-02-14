package com.victorbern.gerservicos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victorbern.gerservicos.models.Obra;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Long>{

}
