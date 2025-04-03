package br.gov.seplag_api_teste.mappers;

import br.gov.seplag_api_teste.entity.FotoPessoa;
import br.gov.seplag_api_teste.reqres.FotoPessoaRequest;
import br.gov.seplag_api_teste.reqres.FotoPessoaResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FotoPessoaMapper {
    FotoPessoa toEntity(FotoPessoaRequest fotoPessoaRequest);
    FotoPessoaResponse toResponse(FotoPessoa foto);
}
