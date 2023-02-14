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

import com.victorbern.gerservicos.dto.ClienteDTO;
import com.victorbern.gerservicos.models.Cliente;
import com.victorbern.gerservicos.repositories.ClienteRepository;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public ClienteResource(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ClienteDTO> save(@RequestBody Cliente cliente){
		clienteRepository.save(cliente);
		ClienteDTO clienteDTO = new ClienteDTO(cliente);
		return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
	}
	
	@GetMapping
	@Transactional(readOnly = true)
	public ResponseEntity<List<ClienteDTO>> getAll(){
		List<Cliente> clientes = new ArrayList<>();
		clientes = clienteRepository.findAll();
		List<ClienteDTO> clientesDTO = new ArrayList<>();
		for (Cliente cliente : clientes) {
			clientesDTO.add(new ClienteDTO(cliente));
		}
		return new ResponseEntity<>(clientesDTO, HttpStatus.OK);
	}
	
	@GetMapping(path="/{id}")
	@Transactional(readOnly = true)
	public ResponseEntity<Optional<ClienteDTO>> getById(@PathVariable Long id) {
		Optional<Cliente> cliente;
		Optional<ClienteDTO> clienteDTO;
		
		try {
			cliente = clienteRepository.findById(id);
			clienteDTO = Optional.of(new ClienteDTO(cliente.get()));
			return new ResponseEntity<Optional<ClienteDTO>>(clienteDTO, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			return new ResponseEntity<Optional<ClienteDTO>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path="/{id}")
	@Transactional
	public ResponseEntity<Optional<ClienteDTO>> deleteById(@PathVariable Long id){
		try {
			clienteRepository.deleteById(id);
			return new ResponseEntity<Optional<ClienteDTO>>(HttpStatus.OK);
		} catch(NoSuchElementException nsee) {
			return new ResponseEntity<Optional<ClienteDTO>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody Cliente novoCliente){
		return clienteRepository.findById(id).map(cliente -> {
			cliente.setNomeCliente(novoCliente.getNomeCliente());
			cliente.setCelularCliente(novoCliente.getCelularCliente());
			cliente.setEmailCliente(novoCliente.getEmailCliente());
			Cliente clienteAtualizado = clienteRepository.save(cliente);
			ClienteDTO clienteDTO = new ClienteDTO(clienteAtualizado);
			return ResponseEntity.ok().body(clienteDTO);
		}).orElse(ResponseEntity.notFound().build());
	}
}
