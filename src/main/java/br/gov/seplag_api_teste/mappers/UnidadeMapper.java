package br.gov.seplag_api_teste.mappers;

import br.gov.seplag_api_teste.entity.Unidade;
import br.gov.seplag_api_teste.reqres.UnidadeRequest;
import br.gov.seplag_api_teste.reqres.UnidadeResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UnidadeMapper {
    Unidade toEntity(UnidadeRequest unidadeRequest);

    UnidadeResponse toResponse(Unidade servidorTemporario);
}
