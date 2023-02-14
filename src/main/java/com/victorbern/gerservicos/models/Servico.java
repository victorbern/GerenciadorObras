package com.victorbern.gerservicos.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Servico {

	@GeneratedValue
	@Id
	@Column(name = "servico_id")
	private Long id;
	
	@Column(name = "descricaoServico")
	private String descricaoServico;
	
	@Column(name = "valorMetro")
	private float valorMetro;
	
	@Column(name = "totalMetragem")
	private float totalMetragem;
	
	@ManyToOne
	@JoinColumn(name = "comodo_id")
	private Comodo comodo;

	public Servico() {

	}

	public Servico(String descricaoServico, float valorMetro, float totalMetragem,
			List<Obra> ordens) {
		this.descricaoServico = descricaoServico;
		this.valorMetro = valorMetro;
		this.totalMetragem = totalMetragem;
	}

	public Long getId() {
		return id;
	}

	public String getDescricaoServico() {
		return descricaoServico;
	}

	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}

	public float getValorMetro() {
		return valorMetro;
	}

	public void setValorMetro(float valorMetro) {
		this.valorMetro = valorMetro;
	}

	public float getTotalMetragem() {
		return totalMetragem;
	}

	public void setTotalMetragem(float totalMetragem) {
		this.totalMetragem = totalMetragem;
	}
	
	public Comodo getComodo() {
		return comodo;
	}

	public void setComodo(Comodo comodo) {
		this.comodo = comodo;
	}
	
}
