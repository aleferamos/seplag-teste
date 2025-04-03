package br.gov.seplag_api_teste.reqres;

public record FotoPessoaRequest(
        PessoaRequest pessoa,
        String fotoAsBase64
) {
}
