package br.gov.seplag_api_teste.reqres;

public record EnderecoRequest(
        Long id,
        String tipoLogradouro,
        String logradouro,
        Integer numero,
        String bairro,
        CidadeRequest cidade
) {
}
