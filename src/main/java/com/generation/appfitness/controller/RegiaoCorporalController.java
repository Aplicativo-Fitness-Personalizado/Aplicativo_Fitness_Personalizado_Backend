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

import com.generation.appfitness.model.RegiaoCorporal;
import com.generation.appfitness.repository.RegiaoCorporalRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/regiao_corporal")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegiaoCorporalController {

	@Autowired
	private RegiaoCorporalRepository regiaoCorporalRepository;

	@GetMapping
	public ResponseEntity<List<RegiaoCorporal>> getAll() {
		return ResponseEntity.ok(regiaoCorporalRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<RegiaoCorporal> getById(@PathVariable Long id) {
		return regiaoCorporalRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@PostMapping
	public ResponseEntity<RegiaoCorporal> post(@Valid @RequestBody RegiaoCorporal regiaoCorporal) {
		return ResponseEntity.status(HttpStatus.CREATED).body(regiaoCorporalRepository.save(regiaoCorporal));
	}

	@PutMapping
	public ResponseEntity<RegiaoCorporal> put(@Valid @RequestBody RegiaoCorporal regiaoCorporal) {
		return regiaoCorporalRepository.findById(regiaoCorporal.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
						.body(regiaoCorporalRepository.save(regiaoCorporal)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<RegiaoCorporal> regiaoCorporal = regiaoCorporalRepository.findById(id);

		if (regiaoCorporal.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		regiaoCorporalRepository.deleteById(id);
	}

}
