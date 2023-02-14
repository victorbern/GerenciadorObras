package com.victorbern.gerservicos.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorbern.gerservicos.dto.ComodoDTO;
import com.victorbern.gerservicos.models.Comodo;
import com.victorbern.gerservicos.repositories.ComodoRepository;

@RestController
@RequestMapping(path = "/comodos")
public class ComodoResource {
	
	@Autowired
	private ComodoRepository comodoRepository;
	
	public ComodoResource(ComodoRepository comodoRepository) {
		this.comodoRepository = comodoRepository;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ComodoDTO> save(@RequestBody Comodo comodo){
		comodoRepository.save(comodo);
		ComodoDTO comodoDTO = new ComodoDTO(comodo);
		return new ResponseEntity<>(comodoDTO, HttpStatus.OK);
	}
}
