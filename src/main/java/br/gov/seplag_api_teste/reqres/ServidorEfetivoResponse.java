package br.gov.seplag_api_teste.reqres;

public record ServidorEfetivoResponse(
        Long id,
        PessoaResponse pessoa,
        String matricula
) {
}
