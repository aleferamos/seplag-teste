package br.gov.seplag_api_teste.reqres;

import java.util.Date;

public record FotoPessoaResponse(
        Long id,
        PessoaResponse pessoa,
        Date data,
        String bucket,
        String hash,
        String nomeArquivo
) {
}
