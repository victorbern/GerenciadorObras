package com.victorbern.gerservicos.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorbern.gerservicos.dto.ServicoDTO;
import com.victorbern.gerservicos.exception.ResourceException;
import com.victorbern.gerservicos.models.Servico;
import com.victorbern.gerservicos.repositories.ServicoRepository;

@RestController
@RequestMapping(path="/servicos")
public class ServicoResource {
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<ServicoDTO> save(@RequestBody Servico servico){
		servicoRepository.save(servico);
		ServicoDTO servicoDTO = new ServicoDTO(servico);
		return new ResponseEntity<ServicoDTO>(servicoDTO, HttpStatus.OK);
	}
	
	@GetMapping
	@Transactional(readOnly=true)
	public ResponseEntity<List<ServicoDTO>> getAll(){
		List<Servico> servicos = new ArrayList<>();
		servicos = servicoRepository.findAll();
		List<ServicoDTO> servicosDTO = new ArrayList<>();
		for (Servico servico : servicos) {
			servicosDTO.add(new ServicoDTO(servico));
		}
		return new ResponseEntity<List<ServicoDTO>>(servicosDTO, HttpStatus.OK);
	}
	
	@GetMapping(path="/{id}")
	@Transactional(readOnly = true)
	public ResponseEntity<Optional<ServicoDTO>> getById(@PathVariable Long id){
		Optional<Servico> servico;
		Optional<ServicoDTO> servicoDTO;
		try {
			servico = servicoRepository.findById(id);
			if (!servico.isPresent()) {
				throw new ResourceException(HttpStatus.NOT_FOUND, "Serviço não encontrado");
			}
			servicoDTO = Optional.of(new ServicoDTO(servico.get()));
			return new ResponseEntity<Optional<ServicoDTO>>(servicoDTO, HttpStatus.OK);
		} catch (ResourceException re) {
			return new ResponseEntity<Optional<ServicoDTO>>(re.getHttpStatus());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();			
		}
	}
	
	@DeleteMapping(path="/{id}")
	@Transactional
	public ResponseEntity<Optional<ServicoDTO>> deleteById(@PathVariable Long id){
		try {
			servicoRepository.deleteById(id);
			return new ResponseEntity<Optional<ServicoDTO>>(HttpStatus.OK);
		} catch (ResourceException re) {
			return new ResponseEntity<Optional<ServicoDTO>>(re.getHttpStatus());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
}
