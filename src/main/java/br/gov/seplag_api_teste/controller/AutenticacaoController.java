package br.gov.seplag_api_teste.controller;

import br.gov.seplag_api_teste.entity.Usuario;
import br.gov.seplag_api_teste.exception.RefreshTokenException;
import br.gov.seplag_api_teste.reqres.DateResponse;
import br.gov.seplag_api_teste.reqres.LoginRequest;
import br.gov.seplag_api_teste.reqres.LoginResponse;
import br.gov.seplag_api_teste.reqres.RefreshTokenRequest;
import br.gov.seplag_api_teste.service.AutenticacaoService;
import br.gov.seplag_api_teste.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoints relacionados a autenticação")
public class AutenticacaoController {
    private final JwtService jwtService;

    private final AutenticacaoService autenticacaoService;

    public AutenticacaoController(JwtService jwtService, AutenticacaoService autenticacaoService) {
        this.jwtService = jwtService;
        this.autenticacaoService = autenticacaoService;
    }

    @PostMapping("/login")
    @Operation(
            summary = "Fazer login",
            description = "End point responsável por fazer login e retornar o token",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Login feito com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = LoginResponse.class)
                            )
                    )
            }
    )
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest loginUserDto) {
        Usuario authenticatedUser = autenticacaoService.authenticate(loginUserDto);

        String accessToken = jwtService.generateToken(authenticatedUser);
        String refreshToken = jwtService.generateRefreshToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse(accessToken, jwtService.getExpirationTime(), refreshToken);

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/refresh")
    @Operation(
            summary = "Refresh token",
            description = "End point responsável por dar refresh e renovar o token",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Token renovado com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = LoginResponse.class)
                            )
                    )
            }
    )
    public ResponseEntity<LoginResponse> refresh(@RequestBody RefreshTokenRequest request) {
        String refreshToken = request.refreshToken();

        if (ObjectUtils.isEmpty(refreshToken) || !jwtService.isRefreshTokenValid(refreshToken)) {
            throw new RefreshTokenException("Refresh token inválido ou expirado");
        }

        String username = jwtService.extractUsername(refreshToken);
        UserDetails user = jwtService.loadUserByUsername(username);

        String newAccessToken = jwtService.generateToken(user);
        String newRefreshToken = jwtService.generateRefreshToken(user);

        return ResponseEntity.ok(new LoginResponse(newAccessToken, jwtService.getExpirationTime(), newRefreshToken));
    }

    @GetMapping("/token-is-valid")
    @Operation(
            summary = "Token é valido",
            description = "End point responsável por verificar se token é válido",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Token válido",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = DateResponse.class)
                            )
                    )
            }
    )
    public ResponseEntity<DateResponse> authenticate(
            @RequestParam String token
    ) {
        return ResponseEntity.ok(DateResponse.builder().response(jwtService.isTokenValid(token)).build());
    }
}