package br.gov.seplag_api_teste.mappers;

import br.gov.seplag_api_teste.entity.Endereco;
import br.gov.seplag_api_teste.reqres.EnderecoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
    EnderecoResponse toResponse(Endereco endereco);
}
