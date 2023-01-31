package com.victorbern.gerservicos.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrdemServico {
	
	@GeneratedValue
	@Id
	private Long id;
	
	@Column(name = "totalOrdemServico")
	private float totalOrdemServico;
	
	@Column(name = "dataInicio")
	private Date dataInicio;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "pagamento_id")
	private Pagamento pagamento;
	
	@ManyToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;
	
	@ManyToOne
	@JoinColumn(name = "servico_id")
	private Servico servico;
	
	public OrdemServico() {
		
	}

	public OrdemServico(float totalOrdemServico, Date dataInicio, Cliente cliente, Pagamento pagamento,
			Endereco endereco, Servico servico) {
		this.totalOrdemServico = totalOrdemServico;
		this.dataInicio = dataInicio;
		this.cliente = cliente;
		this.pagamento = pagamento;
		this.endereco = endereco;
		this.servico = servico;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getTotalOrdemServico() {
		return totalOrdemServico;
	}

	public void setTotalOrdemServico(float totalOrdemServico) {
		this.totalOrdemServico = totalOrdemServico;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
	
}
