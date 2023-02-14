package com.victorbern.gerservicos.dto;

import java.util.ArrayList;
import java.util.List;

import com.victorbern.gerservicos.models.Comodo;
import com.victorbern.gerservicos.models.Servico;

public class ComodoDTO {
	private Long id;
	private String nomeComodo;
	private String descricao;
	private Long obra_id;
	private List<Servico> servicos = new ArrayList<>();
	
	public ComodoDTO() {
		
	}
	
	public ComodoDTO(Long id, String nomeComodo, String descricao, Long obra_id) {
		this.id = id;
		this.nomeComodo = nomeComodo;
		this.descricao = descricao;
		this.obra_id = obra_id;
	}
	
	public ComodoDTO(Comodo comodo) {
		this.id = comodo.getId();
		this.nomeComodo = comodo.getNomeComodo();
		this.descricao = comodo.getDescricao();
		this.obra_id = comodo.getObra().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeComodo() {
		return nomeComodo;
	}

	public void setNomeComodo(String nomeComodo) {
		this.nomeComodo = nomeComodo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getObra_id() {
		return obra_id;
	}

	public void setObra_id(Long obra_id) {
		this.obra_id = obra_id;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
	
}
