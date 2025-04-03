package br.gov.seplag_api_teste.reqres;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public record ServidorEfetivoRequest(
        Long id,
        PessoaRequest pessoa,
        String matricula
) {
}
