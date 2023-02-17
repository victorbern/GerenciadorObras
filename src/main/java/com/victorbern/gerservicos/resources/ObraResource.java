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
import com.victorbern.gerservicos.models.Obra;
import com.victorbern.gerservicos.repositories.ObraRepository;

@RestController
@RequestMapping(path = "/obras")
public class ObraResource {
	
	@Autowired
	private ObraRepository obraRepository;
	
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
	
}
