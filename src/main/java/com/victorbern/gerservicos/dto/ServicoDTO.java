package com.victorbern.gerservicos.dto;

import com.victorbern.gerservicos.models.Servico;

public class ServicoDTO {
	
	private Long id;
	private String descricaoServico;
	private float valorMetro;
	private float totalMetragem;
	
	public ServicoDTO() {
		
	}
	
	public ServicoDTO(Long id, String descricaoServico, float valorMetro, float totalMetragem) {
		this.id = id;
		this.descricaoServico = descricaoServico;
		this.valorMetro = valorMetro;
		this.totalMetragem = totalMetragem;
	}
	
	public ServicoDTO(Servico servico) {
		this.id = servico.getId();
		this.descricaoServico = servico.getDescricaoServico();
		this.valorMetro = servico.getValorMetro();
		this.totalMetragem = servico.getTotalMetragem();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
}
