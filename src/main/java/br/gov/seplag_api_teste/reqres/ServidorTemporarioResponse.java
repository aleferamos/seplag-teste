package br.gov.seplag_api_teste.reqres;

import java.util.Date;

public record ServidorTemporarioResponse(
        PessoaResponse pessoa,
        Date dataAdmissao,
        Date dataDemissao
) {
}
