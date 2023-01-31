package com.victorbern.gerservicos.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Servico {

	@GeneratedValue
	@Id
	@Column(name = "servico_id")
	private Long idServico;
	
	@Column(name = "descricaoServico")
	private String descricaoServico;
	
	@Column(name = "valorMetro")
	private float valorMetro;
	
	@Column(name = "totalMetragem")
	private float totalMetragem;
	
	@Column(name = "localServico")
	private String localServico;
	
	@OneToMany(mappedBy = "servico")
	private List<OrdemServico> ordens;

	public Servico() {

	}

	public Servico(String descricaoServico, float valorMetro, float totalMetragem, String localServico,
			List<OrdemServico> ordens) {
		this.descricaoServico = descricaoServico;
		this.valorMetro = valorMetro;
		this.totalMetragem = totalMetragem;
		this.localServico = localServico;
		this.ordens = ordens;
	}

	public Long getIdServico() {
		return idServico;
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

	public String getLocalServico() {
		return localServico;
	}

	public void setLocalServico(String localServico) {
		this.localServico = localServico;
	}

	public List<OrdemServico> getOrdens() {
		return ordens;
	}

	public void setOrdens(List<OrdemServico> ordens) {
		this.ordens = ordens;
	}
	
	public void addOrdem(OrdemServico ordem) {
		if(ordem.getServico() != this) {
			ordem.setServico(this);
		}
		this.ordens.add(ordem);
	}
	
	public void removeOrdem(OrdemServico ordem) {
		ordem.setServico(null);
		this.ordens.remove(ordem);
	}
	
}
