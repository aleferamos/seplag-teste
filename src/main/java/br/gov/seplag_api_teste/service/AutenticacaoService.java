package br.gov.seplag_api_teste.service;

import br.gov.seplag_api_teste.entity.Usuario;
import br.gov.seplag_api_teste.repository.UsuarioRepository;
import br.gov.seplag_api_teste.reqres.LoginRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {
    private final UsuarioRepository userRepository;

    private final AuthenticationManager authenticationManager;

    public AutenticacaoService(
            UsuarioRepository userRepository,
            AuthenticationManager authenticationManager
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public Usuario authenticate(LoginRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.email(),
                        input.senha()
                )
        );

        return userRepository.findByEmail(input.email())
                .orElseThrow();
    }
}