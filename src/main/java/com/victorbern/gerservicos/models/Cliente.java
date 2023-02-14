package com.victorbern.gerservicos.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente {

	@GeneratedValue
	@Id
	@Column(name = "cliente_id")
	private Long id;
	
	@Column(name = "nomeCliente")
	private String nomeCliente;
	
	@Column(name = "celularCliente")
	private String celularCliente;
	
	@Column(name = "emailCliente")
	private String emailCliente;
	
	@OneToMany(mappedBy = "cliente")
	private List<Obra> obras = new ArrayList<>();

	public Cliente() {
		
	}
	
	public Cliente(String nomeCliente, String celularCliente, String emailCliente) {
		this.nomeCliente = nomeCliente;
		this.celularCliente = celularCliente;
		this.emailCliente = emailCliente;
	}

	public Long getId() {
		return this.id;
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
	
	public List<Obra> getObras() {
		return obras;
	}

	public void setObras(List<Obra> obras) {
		this.obras = obras;
	}
	
	public void addObra(Obra obra) {
		if(obra.getCliente() != this) {
			obra.setCliente(this);
		}
		this.obras.add(obra);
	}
	
	public void removeOrdem(Obra obra) {
		obra.setCliente(null);
		this.obras.remove(obra);
	}
	
}
