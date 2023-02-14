package com.victorbern.gerservicos.dto;

import com.victorbern.gerservicos.models.Pagamento;

public class PagamentoDTO {

	private Long id;
	private float totalPagamento;
	private String tipoPagamento;
	
	public PagamentoDTO() {
		
	}
	
	public PagamentoDTO(Long id, float totalPagamento, String tipoPagamento) {
		this.id = id;
		this.totalPagamento = totalPagamento;
		this.tipoPagamento = tipoPagamento;
	}
	
	public PagamentoDTO(Pagamento pagamento) {
		this.id = pagamento.getId();
		this.totalPagamento = pagamento.getTotalPagamento();
		this.tipoPagamento = pagamento.getTipoPagamento();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getTotalPagamento() {
		return totalPagamento;
	}

	public void setTotalPagamento(float totalPagamento) {
		this.totalPagamento = totalPagamento;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
}
