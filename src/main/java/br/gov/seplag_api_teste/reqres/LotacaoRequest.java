package br.gov.seplag_api_teste.reqres;

import java.util.Date;

public record LotacaoRequest(
        Long id,
        PessoaRequest pessoa,
        UnidadeRequest unidade,
        Date dataLotacao,
        Date dataRemocao,
        String portaria
) {
}
