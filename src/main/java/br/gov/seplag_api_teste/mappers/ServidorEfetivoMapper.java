package br.gov.seplag_api_teste.mappers;

import br.gov.seplag_api_teste.entity.ServidorEfetivo;
import br.gov.seplag_api_teste.reqres.ServidorEfetivoRequest;
import br.gov.seplag_api_teste.reqres.ServidorEfetivoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServidorEfetivoMapper {
    ServidorEfetivo toEntity(ServidorEfetivoRequest servidorEfetivoRequest);
    ServidorEfetivoResponse toResponse(ServidorEfetivo servidorEfetivo);
}
