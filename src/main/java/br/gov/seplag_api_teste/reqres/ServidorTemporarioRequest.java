package br.gov.seplag_api_teste.reqres;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties
public record ServidorTemporarioRequest(
        Long id,
        PessoaRequest pessoa,
        Date dataAdmissao,
        Date dataDemissao
) {
}
