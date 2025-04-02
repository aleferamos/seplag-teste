package br.gov.seplag_api_teste.reqres;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginRequest
        (
                @Schema(example = "seplag@email.com")
                String email,
                @Schema(example = "seplag")
                String senha
        ) {
}
