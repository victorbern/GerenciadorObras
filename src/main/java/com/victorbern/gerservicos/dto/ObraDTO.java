package com.victorbern.gerservicos.dto;

import java.util.Date;

import com.victorbern.gerservicos.models.Cliente;
import com.victorbern.gerservicos.models.Endereco;
import com.victorbern.gerservicos.models.Obra;
import com.victorbern.gerservicos.models.Pagamento;

public class ObraDTO {
	
	private Long id;
	private float totalObra;
	private Date dataInicio;
	private ClienteDTO cliente;
	private PagamentoDTO pagamento;
	private EnderecoDTO endereco;
	
	public ObraDTO() {
		
	}

	public ObraDTO(Long id, float totalObra, Date dataInicio, Cliente cliente, Pagamento pagamento,
			Endereco endereco) {
		this.id = id;
		this.totalObra = totalObra;
		this.dataInicio = dataInicio;
		this.cliente = new ClienteDTO(cliente);
		this.pagamento = new PagamentoDTO(pagamento);
		this.endereco = new EnderecoDTO(endereco);
	}
	
	public ObraDTO(Obra ordem) {
		this.id = ordem.getId();
		this.totalObra = ordem.getTotalObra();
		this.dataInicio = ordem.getDataInicio();
		this.cliente = new ClienteDTO(ordem.getCliente());
		if (ordem.getPagamento()!=null) {
			this.pagamento = new PagamentoDTO(ordem.getPagamento());			
		}
		this.endereco = new EnderecoDTO(ordem.getEndereco());
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

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = new ClienteDTO(cliente);
	}

	public PagamentoDTO getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = new PagamentoDTO(pagamento);
	}

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = new EnderecoDTO(endereco);
	}
	
}
