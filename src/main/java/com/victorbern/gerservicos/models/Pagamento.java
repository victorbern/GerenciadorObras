package com.victorbern.gerservicos.models;

import java.util.ArrayList;
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
	private List<Obra> obras = new ArrayList<>();
	
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

	public List<Obra> getObras() {
		return obras;
	}

	public void setObras(List<Obra> obras) {
		this.obras = obras;
	}
	
	public void addObra(Obra obra) {
		if(obra.getPagamento() != this) {
			obra.setPagamento(this);
		}
		this.obras.add(obra);
	}
	
	public void removeObra(Obra obra) {
		obra.setPagamento(null);
		this.obras.remove(obra);
	}
	
}
