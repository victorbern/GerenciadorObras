package com.victorbern.gerservicos.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.victorbern.gerservicos.dto.ClienteDTO;
import com.victorbern.gerservicos.models.Cliente;
import com.victorbern.gerservicos.repositories.ClienteRepository;

@RunWith(MockitoJUnitRunner.class)
public class ClienteResourceTest {
	
	@Mock
	private ClienteRepository clienteRepository;
	
	@InjectMocks
	private ClienteResource clienteResource;
	
	private Cliente cliente;
	
	@Before
	public void setUp() {
		cliente = new Cliente();
		cliente.setId(1L);
		cliente.setNomeCliente("Cliente 1");
		cliente.setCelularCliente("123456789");
		cliente.setEmailCliente("cliente1@email.com");
	}
	
	@Test
	public void testSave() {
		when(clienteRepository.save(cliente)).thenReturn(cliente);
		
		ResponseEntity<ClienteDTO> response = clienteResource.save(cliente);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1L, response.getBody().getId().longValue());
		assertEquals("Cliente 1", response.getBody().getNomeCliente());
		assertEquals("12345679", response.getBody().getCelularCliente());
		assertEquals("cliente1@gmail.com", response.getBody().getEmailCliente());
	}
}
