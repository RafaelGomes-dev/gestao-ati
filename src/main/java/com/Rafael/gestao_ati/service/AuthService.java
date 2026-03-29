package com.Rafael.gestao_ati.service;

import com.Rafael.gestao_ati.model.Usuario;
import com.Rafael.gestao_ati.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String login(String email, String senha) {
        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new RuntimeException("Senha incorreta");
        }
        return jwtService.gerarToken(usuario.getEmail(), usuario.getRole().name());
    }

    public void registrar(String email, String senha, Usuario.Role role) {
        if (repository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email ja cadastrado");
        }
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(passwordEncoder.encode(senha));
        usuario.setRole(role);
        repository.save(usuario);
    }

}
