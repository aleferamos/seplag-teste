package br.gov.seplag_api_teste.mappers;

import br.gov.seplag_api_teste.entity.ServidorTemporario;
import br.gov.seplag_api_teste.reqres.ServidorTemporarioRequest;
import br.gov.seplag_api_teste.reqres.ServidorTemporarioResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServidorTemporarioMapper {
    ServidorTemporario toEntity(ServidorTemporarioRequest servidorTemporarioRequest);

    ServidorTemporarioResponse toResponse(ServidorTemporario servidorTemporario);
}
