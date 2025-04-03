package br.gov.seplag_api_teste.mappers;

import br.gov.seplag_api_teste.entity.Lotacao;
import br.gov.seplag_api_teste.reqres.LotacaoRequest;
import br.gov.seplag_api_teste.reqres.LotacaoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LotacaoMapper {
    Lotacao toEntity(LotacaoRequest lotacaoRequest);

    LotacaoResponse toResponse(Lotacao lotacao);
}
