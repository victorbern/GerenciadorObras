package com.victorbern.gerservicos.dto;

import com.victorbern.gerservicos.models.Endereco;

public class EnderecoDTO {
	
	private Long id;
	private String logradouro;
	private String numero;
	private String bairro;
	private String cidade;
	private String cep;
	private String tipoEndereco;
	
	public EnderecoDTO() {
		
	}
	
	public EnderecoDTO(Long id, String logradouro, String numero, String bairro, String cidade, String cep, String tipoEndereco) {
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.cep = cep;
		this.tipoEndereco = tipoEndereco;
	}
	
	public EnderecoDTO(Endereco endereco) {
		this.id = endereco.getId();
		this.logradouro = endereco.getLogradouro();
		this.numero = endereco.getNumero();
		this.bairro = endereco.getBairro();
		this.cidade = endereco.getCidade();
		this.cep = endereco.getCep();
		this.tipoEndereco = endereco.getTipoEndereco();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
}
