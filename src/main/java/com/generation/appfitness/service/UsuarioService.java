package com.generation.appfitness.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.appfitness.model.UsuarioLogin;
import com.generation.appfitness.model.Usuario;
import com.generation.appfitness.repository.UsuarioRepository;
import com.generation.appfitness.security.JwtService;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public Optional<Usuario> cadastrarUsuario(Usuario usuario) {
        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
            return Optional.empty();
        }
        usuario.setSenha(criptografarSenha(usuario.getSenha()));
        return Optional.of(usuarioRepository.save(usuario));
    }

    public Optional<Usuario> atualizarUsuario(Usuario usuario) {
        if (usuarioRepository.findById(usuario.getId()).isPresent()) {
            Optional<Usuario> buscaUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
            if ((buscaUsuario.isPresent()) && (buscaUsuario.get().getId() != usuario.getId())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
            }

            usuario.setSenha(criptografarSenha(usuario.getSenha()));
            return Optional.ofNullable(usuarioRepository.save(usuario));
        }

        return Optional.empty();

    }

    public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {

        // Gera o Objeto de autenticação
        var credenciais = new UsernamePasswordAuthenticationToken(usuarioLogin.get().getUsuario(),
                usuarioLogin.get().getSenha());
        // Autentica o Usuario
        Authentication authentication = authenticationManager.authenticate(credenciais);
        // Se a autenticação foi efetuada com sucesso
        if (authentication.isAuthenticated()) {

            // Busca os dados do usuário
            Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());

            // Se o usuário foi encontrado
            if (usuario.isPresent()) {

                // Preenche o Objeto usuarioLogin com os dados encontrados
                usuarioLogin.get().setId(usuario.get().getId());
                usuarioLogin.get().setNome(usuario.get().getNome());
                usuarioLogin.get().setToken(gerarToken(usuarioLogin.get().getUsuario()));
                usuarioLogin.get().setSenha("");

                // Retorna o Objeto preenchido
                return usuarioLogin;

            }

        }

        return Optional.empty();

    }

    private String criptografarSenha(String senha) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode(senha);

    }

    private String gerarToken(String usuario) {
        return "Bearer " + jwtService.generateToken(usuario);
    }

    public Optional<Map<String, Object>> calcularIMC(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            if (usuario.getPeso() > 0 && usuario.getAltura() > 0) {
                double imc = usuario.getPeso() / (usuario.getAltura() * usuario.getAltura());

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
                resposta.put("usuario", usuario.getNome());
                resposta.put("imc", String.format("%.2f", imc));
                resposta.put("classificacao", classificacao);

                return Optional.of(resposta);
            }
        }

        return Optional.empty();
    }

}