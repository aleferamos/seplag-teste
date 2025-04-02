package br.gov.seplag_api_teste.reqres;

import java.time.LocalDateTime;

public record ErrorResponse(
        String error,
        String message,
        LocalDateTime timestamp,
        String status
) {}