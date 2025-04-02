package br.gov.seplag_api_teste.reqres;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public record ServidorEfetivoRequest(
        PessoaRequest pessoa,
        String matricula
) {
}
