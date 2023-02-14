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

import com.victorbern.gerservicos.dto.EnderecoDTO;
import com.victorbern.gerservicos.models.Endereco;
import com.victorbern.gerservicos.repositories.EnderecoRepository;

@RestController
@RequestMapping(path = "/enderecos")
public class EnderecoResource {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public EnderecoResource(EnderecoRepository enderecoRepository) {
		this.enderecoRepository = enderecoRepository;		
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<EnderecoDTO> save(@RequestBody Endereco endereco){
		enderecoRepository.save(endereco);
		EnderecoDTO enderecoDTO = new EnderecoDTO(endereco);
		return new ResponseEntity<>(enderecoDTO, HttpStatus.OK);
	}
	
	@GetMapping
	@Transactional(readOnly = true)
	public ResponseEntity<List<EnderecoDTO>> getAll(){
		List<Endereco> enderecos = new ArrayList<>();
		enderecos = enderecoRepository.findAll();
		List<EnderecoDTO> enderecosDTO = new ArrayList<>();
		for (Endereco endereco : enderecos) {
			enderecosDTO.add(new EnderecoDTO(endereco));
		}
		return new ResponseEntity<>(enderecosDTO, HttpStatus.OK);
	}
	
	@GetMapping(path="/{id}")
	@Transactional(readOnly = true)
	public ResponseEntity<Optional<EnderecoDTO>> getById(@PathVariable Long id) {
		Optional<Endereco> endereco;
		Optional<EnderecoDTO> enderecoDTO;
		
		try {
			endereco = enderecoRepository.findById(id);
			enderecoDTO = Optional.of(new EnderecoDTO(endereco.get()));
			return new ResponseEntity<Optional<EnderecoDTO>>(enderecoDTO, HttpStatus.OK);
		}catch (NoSuchElementException nsee) {
			return new ResponseEntity<Optional<EnderecoDTO>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path="/{id}")
	@Transactional
	public ResponseEntity<Optional<EnderecoDTO>> deleteById(@PathVariable Long id){
		try {
			enderecoRepository.deleteById(id);
			return new ResponseEntity<Optional<EnderecoDTO>>(HttpStatus.OK);
		}catch (NoSuchElementException nsee) {
			return new ResponseEntity<Optional<EnderecoDTO>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<EnderecoDTO> update(@PathVariable Long id, @RequestBody Endereco novoEndereco) {
		return enderecoRepository.findById(id).map(endereco -> {
			endereco.setLogradouro(novoEndereco.getLogradouro());
			endereco.setNumero(novoEndereco.getNumero());
			endereco.setBairro(novoEndereco.getBairro());
			endereco.setCidade(novoEndereco.getCidade());
			endereco.setCep(novoEndereco.getCep());
			endereco.setTipoEndereco(novoEndereco.getTipoEndereco());
			Endereco enderecoAtualizado = enderecoRepository.save(endereco);
			EnderecoDTO enderecoDTO = new EnderecoDTO(enderecoAtualizado);
			return ResponseEntity.ok().body(enderecoDTO);
		}).orElse(ResponseEntity.notFound().build());
	}
	
}
