package br.gov.seplag_api_teste.reqres;

import java.util.Date;

public record LotacaoResponse(
        Long id,
        PessoaRequest pessoa,
        UnidadeRequest unidade,
        Date dataLotacao,
        Date dataRemocao,
        String portaria
) {
}
