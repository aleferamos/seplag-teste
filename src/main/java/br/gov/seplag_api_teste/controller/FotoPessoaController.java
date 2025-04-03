package br.gov.seplag_api_teste.controller;

import br.gov.seplag_api_teste.mappers.FotoPessoaMapper;
import br.gov.seplag_api_teste.reqres.FotoPessoaRequest;
import br.gov.seplag_api_teste.reqres.FotoPessoaResponse;
import br.gov.seplag_api_teste.service.FotoPessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/foto-pessoa")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Foto Pessoa", description = "Endpoints relacionados a foto pessoa")
public class FotoPessoaController {

    private final FotoPessoaService service;
    private final FotoPessoaMapper mapper;

    @PostMapping("/salvar")
    @Operation(
            summary = "Salvar lotação",
            description = "End point responsável por salvar salvar lotação."
    )
    ResponseEntity<List<FotoPessoaResponse>> salvar(@RequestBody List<FotoPessoaRequest> fotos) {
        var fotosSalvas = service.salvar(fotos.stream().map(mapper::toEntity).toList());
        return ResponseEntity.status(HttpStatus.CREATED).body(fotosSalvas.stream().map(mapper::toResponse).toList());
    }
}
