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

import com.victorbern.gerservicos.dto.ObraDTO;
import com.victorbern.gerservicos.exception.ResourceException;
import com.victorbern.gerservicos.models.Cliente;
import com.victorbern.gerservicos.models.Endereco;
import com.victorbern.gerservicos.models.Obra;
import com.victorbern.gerservicos.models.Pagamento;
import com.victorbern.gerservicos.repositories.ClienteRepository;
import com.victorbern.gerservicos.repositories.EnderecoRepository;
import com.victorbern.gerservicos.repositories.ObraRepository;
import com.victorbern.gerservicos.repositories.PagamentoRepository;

@RestController
@RequestMapping(path = "/obras")
public class ObraResource {
	
	@Autowired
	private ObraRepository obraRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public ObraResource(ObraRepository obraRepository) {
		this.obraRepository = obraRepository;	
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ObraDTO> save(@RequestBody Obra obra){
		obraRepository.save(obra);
		ObraDTO obraDTO = new ObraDTO(obra);
		return new ResponseEntity<>(obraDTO, HttpStatus.OK);
	}
	
	@GetMapping
	@Transactional(readOnly = true)
	public ResponseEntity<List<ObraDTO>> getAll(){
		List<Obra> obras = new ArrayList<>();
		obras = obraRepository.findAll();
		List<ObraDTO> obrasDTO = new ArrayList<>();
		for (Obra obra : obras) {
			obrasDTO.add(new ObraDTO(obra));
		}
		return new ResponseEntity<>(obrasDTO, HttpStatus.OK);
	}
	
	@GetMapping(path="/{id}")
	@Transactional(readOnly = true)
	public ResponseEntity<Optional<ObraDTO>> getById(@PathVariable Long id){
		Optional<Obra> obra;
		Optional<ObraDTO> obraDTO;
		
		try {
			obra = obraRepository.findById(id);
			obraDTO = Optional.of(new ObraDTO(obra.get()));
			return new ResponseEntity<Optional<ObraDTO>>(obraDTO, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			return new ResponseEntity<Optional<ObraDTO>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path="/{id}")
	@Transactional
	public ResponseEntity<Optional<ObraDTO>> deleteById(@PathVariable Long id){
		try {
			obraRepository.deleteById(id);
			return new ResponseEntity<Optional<ObraDTO>>(HttpStatus.OK);
		} catch(NoSuchElementException nsee) {
			return new ResponseEntity<Optional<ObraDTO>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<ObraDTO> update(@PathVariable Long id, @RequestBody Obra novaObra){
		return obraRepository.findById(id).map(obra -> {
			obra.setTotalObra(novaObra.getTotalObra());
			obra.setDataInicio(novaObra.getDataInicio());
			obra.setCliente(novaObra.getCliente());
			obra.setPagamento(novaObra.getPagamento());
			obra.setEndereco(novaObra.getEndereco());
			Obra obraAtualizada = obraRepository.save(obra);
			ObraDTO obraDTO = new ObraDTO(obraAtualizada);
			return ResponseEntity.ok().body(obraDTO);
		}).orElse(ResponseEntity.notFound().build());
	}
	
	// Buscar obras com base no cliente
	@GetMapping(path="cliente/{id}")
	@Transactional
	public ResponseEntity<List<ObraDTO>> findByCliente(@PathVariable Long id){
		Optional<Cliente> cliente;
		List<Obra> obras = new ArrayList<>();
		List<ObraDTO> obrasDTO = new ArrayList<>();
		try {
			cliente = clienteRepository.findById(id);
			if (!cliente.isPresent()) {
				throw new ResourceException(HttpStatus.NOT_FOUND, "Cliente não encontrado!");
			}
			obras = obraRepository.findByClienteId(cliente.get().getId());
			for (Obra obra : obras) {
				obrasDTO.add(new ObraDTO(obra));
			}
			
			return new ResponseEntity<List<ObraDTO>>(obrasDTO, HttpStatus.OK);
		} catch(ResourceException re) {
			return new ResponseEntity<List<ObraDTO>>(re.getHttpStatus());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	// Buscar obras com base no endereço
	@GetMapping(path="endereco/{id}")
	@Transactional
	public ResponseEntity<List<ObraDTO>> findByEndereco(@PathVariable Long id){
		Optional<Endereco> endereco;
		List<Obra> obras = new ArrayList<>();
		List<ObraDTO> obrasDTO = new ArrayList<>();
		try {
			endereco = enderecoRepository.findById(id);
			if (!endereco.isPresent()) {
				throw new ResourceException(HttpStatus.NOT_FOUND, "Endereço não encontrado");
			}
			obras = obraRepository.findByEnderecoId(endereco.get().getId());
			for (Obra obra : obras) {
				obrasDTO.add(new ObraDTO(obra));
			}
			return new ResponseEntity<List<ObraDTO>>(obrasDTO, HttpStatus.OK);
		} catch (ResourceException re) {
			return new ResponseEntity<List<ObraDTO>>(re.getHttpStatus());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	// Buscar obras com base no pagamento
	@GetMapping(path="pagamento/{id}")
	@Transactional
	public ResponseEntity<List<ObraDTO>> findByPagamento(@PathVariable Long id){
		Optional<Pagamento> pagamento;
		List<Obra> obras = new ArrayList<>();
		List<ObraDTO> obrasDTO = new ArrayList<>();
		try {
			pagamento = pagamentoRepository.findById(id);
			if (!pagamento.isPresent()) {
				throw new ResourceException(HttpStatus.NOT_FOUND, "Pagamento não encontrado");
			}
			obras = obraRepository.findByPagamentoId(pagamento.get().getId());
			for (Obra obra : obras) {
				obrasDTO.add(new ObraDTO(obra));
			}
			return new ResponseEntity<List<ObraDTO>>(obrasDTO, HttpStatus.OK);
		} catch (ResourceException re) {
			return new ResponseEntity<List<ObraDTO>>(re.getHttpStatus());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
}
