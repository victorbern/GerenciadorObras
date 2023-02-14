package com.victorbern.gerservicos.dto;

import com.victorbern.gerservicos.models.Cliente;

public class ClienteDTO {
	
	private Long id;
	private String nomeCliente;
	private String celularCliente;
	private String emailCliente;
	
	public ClienteDTO() {
		
	}

	public ClienteDTO(Long id, String nomeCliente, String celularCliente, String emailCliente) {
		this.id = id;
		this.nomeCliente = nomeCliente;
		this.celularCliente = celularCliente;
		this.emailCliente = emailCliente;
	}
	
	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nomeCliente = cliente.getNomeCliente();
		this.celularCliente = cliente.getCelularCliente();
		this.emailCliente = cliente.getEmailCliente();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCelularCliente() {
		return celularCliente;
	}

	public void setCelularCliente(String celularCliente) {
		this.celularCliente = celularCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}
		
}
