package com.victorbern.gerservicos.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorbern.gerservicos.dto.PagamentoDTO;
import com.victorbern.gerservicos.models.Pagamento;
import com.victorbern.gerservicos.repositories.PagamentoRepository;

@RestController
@RequestMapping(path = "/pagamentos")
public class PagamentoResource {
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public PagamentoResource(PagamentoRepository pagamentoRepository) {
		this.pagamentoRepository = pagamentoRepository;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<PagamentoDTO> save(@RequestBody Pagamento pagamento) {
		pagamentoRepository.save(pagamento);
		PagamentoDTO pagamentoDTO = new PagamentoDTO(pagamento);
		return new ResponseEntity<>(pagamentoDTO, HttpStatus.OK);
	}
	
	@GetMapping
	@Transactional(readOnly = true)
	public ResponseEntity<List<PagamentoDTO>> getAll() {
		List<Pagamento> pagamentos = new ArrayList<>();
		pagamentos = pagamentoRepository.findAll();
		List<PagamentoDTO> pagamentosDTO = new ArrayList<>();
		for (Pagamento pagamento : pagamentos) {
			pagamentosDTO.add(new PagamentoDTO(pagamento));
		}
		return new ResponseEntity<>(pagamentosDTO, HttpStatus.OK);
	}
	
	@GetMapping(path="/{id}")
	@Transactional(readOnly = true)
	public ResponseEntity<Optional<PagamentoDTO>> getById(@PathVariable Long id){
		Optional<Pagamento> pagamento;
		Optional<PagamentoDTO> pagamentoDTO;
		
		try {
			pagamento = pagamentoRepository.findById(id);
			pagamentoDTO = Optional.of(new PagamentoDTO(pagamento.get()));
			return new ResponseEntity<Optional<PagamentoDTO>>(pagamentoDTO, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			return new ResponseEntity<Optional<PagamentoDTO>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path="/{id}")
	@Transactional
	public ResponseEntity<Optional<PagamentoDTO>> deleteById(@PathVariable Long id){
		try {
			pagamentoRepository.deleteById(id);
			return new ResponseEntity<Optional<PagamentoDTO>>(HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			return new ResponseEntity<Optional<PagamentoDTO>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<PagamentoDTO> update(@PathVariable Long id, @RequestBody Pagamento novoPagamento){
		return pagamentoRepository.findById(id).map(pagamento -> {
			pagamento.setTotalPagamento(novoPagamento.getTotalPagamento());
			pagamento.setTipoPagamento(novoPagamento.getTipoPagamento());
			pagamento.setObras(novoPagamento.getObras());
			Pagamento pagamentoAtualizado = pagamentoRepository.save(pagamento);
			PagamentoDTO pagamentoDTO = new PagamentoDTO(pagamentoAtualizado);
			return ResponseEntity.ok().body(pagamentoDTO);
		}).orElse(ResponseEntity.notFound().build());
	}
	
}
