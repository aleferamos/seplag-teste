package br.gov.seplag_api_teste.reqres;

public record EnderecoResponse(
        Long id,
        String tipoLogradouro,
        String logradouro,
        Integer numero,
        String bairro,
        CidadeRequest cidade
) {
}
