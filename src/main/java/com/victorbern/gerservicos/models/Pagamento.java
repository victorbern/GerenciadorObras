package com.victorbern.gerservicos.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Pagamento {

	@GeneratedValue
	@Id
	@Column(name = "pagamento_id")
	private Long id;
	
	@Column(name = "totalPagamento")
	private float totalPagamento;
	
	@Column(name = "tipoPagamento")
	private String tipoPagamento;
	
	@OneToMany(mappedBy = "pagamento")
	private List<OrdemServico> ordens;
	
	public Pagamento() {
		
	}
	
	public Pagamento(float totalPagamento, String tipoPagamento) {
		this.totalPagamento = totalPagamento;
		this.tipoPagamento = tipoPagamento;
	}

	public Long getId() {
		return id;
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

	public List<OrdemServico> getOrdens() {
		return ordens;
	}

	public void setOrdens(List<OrdemServico> ordens) {
		this.ordens = ordens;
	}
	
	public void addOrdem(OrdemServico ordem) {
		if(ordem.getPagamento() != this) {
			ordem.setPagamento(this);
		}
		this.ordens.add(ordem);
	}
	
	public void removeOrdem(OrdemServico ordem) {
		ordem.setPagamento(null);
		this.ordens.remove(ordem);
	}
	
}
