package br.gov.seplag_api_teste.reqres;

import br.gov.seplag_api_teste.entity.Unidade;

public record BuscarServidorEfetivoResponse(
        String nome,
        Integer idade,
        Unidade unidadeLotacao,
        String linkFotoTemporario
) {
}
