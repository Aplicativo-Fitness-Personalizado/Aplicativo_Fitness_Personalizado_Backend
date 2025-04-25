package com.generation.appfitness.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.appfitness.model.Treino;
import com.generation.appfitness.repository.RegiaoCorporalRepository;
import com.generation.appfitness.repository.TreinoRepository;


import jakarta.validation.Valid;


@RestController
@RequestMapping("/treinos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TreinoController {

	@Autowired
	private TreinoRepository treinoRepository;
	
	@Autowired
	private RegiaoCorporalRepository regiaoCorporalRepository;
	
	@GetMapping
	public ResponseEntity<List<Treino>> getAll(){
		return ResponseEntity.ok(treinoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Treino>getById(@PathVariable Long id){
		return treinoRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Treino>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(treinoRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<Treino> post(@Valid @RequestBody Treino treino){
		if (regiaoCorporalRepository.existsById(treino.getRegiaoCorporal().getId()))
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(treinoRepository.save(treino));
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Treino não existe!", null);
	}
	
	@PutMapping
	public ResponseEntity<Treino> put(@Valid @RequestBody Treino Treino){
		if (treinoRepository.existsById(Treino.getId())){
			
			if (regiaoCorporalRepository.existsById(Treino.getRegiaoCorporal().getId()))
				return ResponseEntity.status(HttpStatus.OK)
						.body(treinoRepository.save(Treino));
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Treino não existe!", null);
			
		}			
			
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
 	@DeleteMapping("/{id}")
 	public void delete(@PathVariable Long id) {
 		Optional<Treino> Treino = treinoRepository.findById(id);
 		
 		if(Treino.isEmpty())
 			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
 		
 		treinoRepository.deleteById(id);				
 	}
}
