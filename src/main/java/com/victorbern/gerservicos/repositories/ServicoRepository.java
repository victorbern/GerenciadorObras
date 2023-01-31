package com.victorbern.gerservicos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victorbern.gerservicos.models.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long>{

}
