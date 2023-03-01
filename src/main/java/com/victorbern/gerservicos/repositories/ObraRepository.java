package com.victorbern.gerservicos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victorbern.gerservicos.models.Obra;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Long>{
	
	List<Obra> findByClienteId(Long id);
	List<Obra> findByEnderecoId(Long id);
	List<Obra> findByPagamentoId(Long id);
	
}
