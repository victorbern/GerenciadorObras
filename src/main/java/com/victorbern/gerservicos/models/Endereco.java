package com.victorbern.gerservicos.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Endereco {

	@GeneratedValue
	@Id
	@Column(name = "endereco_id")
	private Long id;
	
	@Column(name = "logradouro")
	private String logradouro;
	
	@Column(name = "numero")
	private String numero;
	
	@Column(name = "bairro")
	private String bairro;
	
	@Column(name = "cidade")
	private String cidade;
	
	@Column(name = "cep")
	private String cep;
	
	@Column(name = "tipoEndereco")
	private String tipoEndereco;
	
	@OneToMany(mappedBy = "endereco")
	private List<Obra> obras = new ArrayList<>();
	
	public Endereco() {
		
	}

	public Endereco(String logradouro, String numero, String bairro, String cidade, String cep,
			String tipoEndereco) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.cep = cep;
		this.tipoEndereco = tipoEndereco;
	}

	public Long getId() {
		return id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(String tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public List<Obra> getObras() {
		return obras;
	}

	public void setObras(List<Obra> obras) {
		this.obras = obras;
	}
	
	public void addObra(Obra obra) {
		if(obra.getEndereco() != this) {
			obra.setEndereco(this);
		}
		this.obras.add(obra);
	}
	
	public void removeObra(Obra obra) {
		obra.setEndereco(null);
		this.obras.remove(obra);
	}

}
