package com.victorbern.gerservicos.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorbern.gerservicos.repositories.ClienteRepository;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteRepository clienteRepositorio;
	
	public ClienteResource(ClienteRepository clienteRepositorio) {
		this.clienteRepositorio = clienteRepositorio;
	}
}
