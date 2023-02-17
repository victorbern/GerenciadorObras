package com.victorbern.gerservicos.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorbern.gerservicos.dto.ComodoDTO;
import com.victorbern.gerservicos.models.Comodo;
import com.victorbern.gerservicos.models.Obra;
import com.victorbern.gerservicos.repositories.ComodoRepository;
import com.victorbern.gerservicos.repositories.ObraRepository;

@RestController
@RequestMapping(path = "/comodos")
public class ComodoResource {
	
	@Autowired
	private ComodoRepository comodoRepository;
	
	@Autowired
	private ObraRepository obraRepository;
	
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
	
	@GetMapping
	@Transactional(readOnly = true)
	public ResponseEntity<List<ComodoDTO>> getAll(){
		List<Comodo> comodos = new ArrayList<>();
		comodos = comodoRepository.findAll();
		List<ComodoDTO> comodosDTO = new ArrayList<>();
		for (Comodo comodo : comodos) {
			comodosDTO.add(new ComodoDTO(comodo));
		}
		return new ResponseEntity<>(comodosDTO, HttpStatus.OK);
	}
	
	@GetMapping(path="/{id}")
	@Transactional(readOnly = true)
	public ResponseEntity<Optional<ComodoDTO>> getById(@PathVariable Long id){
		Optional<Comodo> comodo;
		Optional<ComodoDTO> comodoDTO;
		
		try {
			comodo = comodoRepository.findById(id);
			comodoDTO = Optional.of(new ComodoDTO(comodo.get()));
			return new ResponseEntity<Optional<ComodoDTO>>(comodoDTO, HttpStatus.OK);
		} catch(NoSuchElementException nsee) {
			return new ResponseEntity<Optional<ComodoDTO>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path="/{id}")
	@Transactional
	public ResponseEntity<Optional<ComodoDTO>> deleteById(@PathVariable Long id){
		try {
			comodoRepository.deleteById(id);
			return new ResponseEntity<Optional<ComodoDTO>>(HttpStatus.OK);
		} catch(NoSuchElementException nsee) {
			return new ResponseEntity<Optional<ComodoDTO>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<ComodoDTO> update(@PathVariable Long id, @RequestBody Comodo novoComodo){
		return comodoRepository.findById(id).map(comodo -> {
			comodo.setNomeComodo(novoComodo.getNomeComodo());
			comodo.setDescricao(novoComodo.getDescricao());
			comodo.setObra(novoComodo.getObra());
			Comodo comodoAtualizado = comodoRepository.save(comodo);
			ComodoDTO comodoDTO = new ComodoDTO(comodoAtualizado);
			return ResponseEntity.ok().body(comodoDTO);
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping(path="obra/{id}")
	@Transactional
	public ResponseEntity<List<ComodoDTO>> findByObra(@PathVariable Long id){
		Optional<Obra> obra;
		List<Comodo> comodos = new ArrayList<>();
		List<ComodoDTO> comodosDTO = new ArrayList<>();
		try {
			obra = obraRepository.findById(id);
			
			comodos = comodoRepository.findByObraId(obra.get().getId());
			for (Comodo comodo : comodos) {
				comodosDTO.add(new ComodoDTO(comodo));
			}
			return new ResponseEntity<List<ComodoDTO>>(comodosDTO, HttpStatus.OK);
		} catch (Error erro) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
}
