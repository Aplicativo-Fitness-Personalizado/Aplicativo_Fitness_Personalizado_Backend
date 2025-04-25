package com.generation.appfitness.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.appfitness.model.Usuario;
import com.generation.appfitness.model.UsuarioLogin;
import com.generation.appfitness.repository.UsuarioRepository;
import com.generation.appfitness.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> getAll() {

		return ResponseEntity.ok(usuarioRepository.findAll());

	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable Long id) {
		return usuarioRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> autenticarUsuario(@RequestBody Optional<UsuarioLogin> usuarioLogin) {

		return usuarioService.autenticarUsuario(usuarioLogin)
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> postUsuario(@RequestBody @Valid Usuario usuario) {

		return usuarioService.cadastrarUsuario(usuario)
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

	}

	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {

		return usuarioService.atualizarUsuario(usuario)
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

	}

	@GetMapping("/imc/{id}")
	public ResponseEntity<Object> calcularIMC(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		System.out.println(usuario.get().getPeso() <= 0 || usuario.get().getAltura() <= 0);

		if (usuario.isPresent()) {
			if (usuario.get().getPeso() > 0 || usuario.get().getAltura() > 0) {

				double imc = usuario.get().getPeso() / (usuario.get().getAltura() * usuario.get().getAltura());

				String classificacao;

				if (imc < 18.5) {
					classificacao = "Abaixo do peso";
				} else if (imc < 25) {
					classificacao = "Peso normal";
				} else if (imc < 30) {
					classificacao = "Sobrepeso";
				} else if (imc < 35) {
					classificacao = "Obesidade grau 1";
				} else if (imc < 40) {
					classificacao = "Obesidade grau 2";
				} else {
					classificacao = "Obesidade grau 3";
				}

				Map<String, Object> resposta = new HashMap<>();
				resposta.put("usuario: ", usuario.get().getNome());
				resposta.put("imc: ", String.format("%.2f", imc));
				resposta.put("classificacao: ", classificacao);

				return ResponseEntity.ok(resposta);

			} else {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		}

		return ResponseEntity.notFound().build();
	}

}
