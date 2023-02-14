package com.victorbern.gerservicos.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Obra {
	
	@GeneratedValue
	@Id
	@Column(name = "obra_id")
	private Long id;
	
	@Column(name = "totalObra")
	private float totalObra;
	
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
	
	@OneToMany(mappedBy = "obra")
	private List<Comodo> comodos = new ArrayList<>();
	
	public Obra() {
		
	}

	public Obra(float totalObra, Date dataInicio, Cliente cliente, Pagamento pagamento,
			Endereco endereco) {
		this.totalObra = totalObra;
		this.dataInicio = dataInicio;
		this.cliente = cliente;
		this.pagamento = pagamento;
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getTotalObra() {
		return totalObra;
	}

	public void setTotalObra(float totalObra) {
		this.totalObra = totalObra;
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

	public List<Comodo> getComodos() {
		return comodos;
	}

	public void setComodos(List<Comodo> comodos) {
		this.comodos = comodos;
	}
	
	public void addComodo(Comodo comodo) {
		if(comodo.getObra() != this) {
			comodo.setObra(this);
		}
		this.comodos.add(comodo);
	}
	
	public void removeComodo(Comodo comodo) {
		comodo.setObra(null);
		this.comodos.remove(comodo);
	}
	
}
