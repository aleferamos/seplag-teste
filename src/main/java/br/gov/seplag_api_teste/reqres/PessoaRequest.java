package br.gov.seplag_api_teste.reqres;

import java.util.Date;

public record PessoaRequest(
        Long id,
        String nome,
        Date dataNascimento,
        String sexo,
        String nomeMae,
        String nomePai
) {
}
