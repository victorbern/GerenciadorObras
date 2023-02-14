package com.victorbern.gerservicos.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Comodo {
	
	@GeneratedValue
	@Id
	@Column(name = "comodo_id")
	private Long id;
	
	@Column(name = "nomeComodo")
	private String nomeComodo;
	
	@Column(name = "descricao")
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "obra_id")
	private Obra obra;
	
	@OneToMany(mappedBy = "comodo")
	private List<Servico> servicos = new ArrayList<>();
	
	public Comodo() {

	}

	public Comodo(String nomeComodo, String descricao, Obra obra) {
		this.nomeComodo = nomeComodo;
		this.descricao = descricao;
		this.obra = obra;
	}

	public Long getId() {
		return id;
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

	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
	
	public void addServico(Servico servico) {
		if(servico.getComodo() != this) {
			servico.setComodo(this);
		}
		this.servicos.add(servico);
	}
	
	public void removeServico(Servico servico) {
		servico.setComodo(null);
		this.servicos.remove(servico);
	}
	
}
