package br.gov.seplag_api_teste.reqres;

public record LoginResponse(String token, long expiresIn, String refreshToken) { }
