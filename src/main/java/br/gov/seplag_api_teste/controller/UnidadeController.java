package br.gov.seplag_api_teste.controller;

import br.gov.seplag_api_teste.mappers.UnidadeMapper;
import br.gov.seplag_api_teste.reqres.UnidadeRequest;
import br.gov.seplag_api_teste.reqres.UnidadeResponse;
import br.gov.seplag_api_teste.service.UnidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/unidade")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Unidade", description = "Endpoints relacionados a unidade")
public class UnidadeController {
    private final UnidadeService service;
    private final UnidadeMapper mapper;

    @PostMapping("/salvar")
    @Operation(
            summary = "Salvar unidade",
            description = "End point responsável por salvar salvar unidade.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "nome": "string",
                                              "sigla": "string",
                                              "endereco": {
                                                "tipoLogradouro": "string",
                                                "logradouro": "string",
                                                "numero": 1073741824,
                                                "bairro": "string",
                                                "cidade": {
                                                  "id": 1
                                                }
                                              }
                                            }
                                            """
                            )
                    )
            )
    )
    ResponseEntity<UnidadeResponse> salvar(@RequestBody UnidadeRequest unidadeRequest) {
        var unidadeSalva = service.salvar(mapper.toEntity(unidadeRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(unidadeSalva));
    }

    @PutMapping("/atualizar")
    @Operation(
            summary = "Atualizar unidade",
            description = "End point responsável por atualizar unidade.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "id": 1,
                                              "nome": "string",
                                              "sigla": "string",
                                              "endereco": {
                                                "id": 1,
                                                "tipoLogradouro": "string",
                                                "logradouro": "string",
                                                "numero": 1073741824,
                                                "bairro": "string",
                                                "cidade": {
                                                  "id": 1,
                                                  "nome": "string",
                                                  "uf": "string"
                                                }
                                              }
                                            }
                                            """
                            )
                    )
            )
    )
    ResponseEntity<UnidadeResponse> atualizar(@RequestBody UnidadeRequest unidadeRequest) {
        var unidadeSalva = service.atualizar(mapper.toEntity(unidadeRequest));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.toResponse(unidadeSalva));
    }

    @GetMapping("/buscar-por-id/{id}")
    @Operation(
            summary = "Buscar unidade por id",
            description = "End point responsável buscar unidade por id"
    )
    ResponseEntity<UnidadeResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toResponse(service.obterPorId(id)));
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar unidades",
            description = "End point responsável listar unidades",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Unidade retornada com sucesso"
                    )
            }
    )
    ResponseEntity<Page<UnidadeResponse>> listar(@Parameter(hidden = true) Pageable pageable) {
        var unidades = service.listar(pageable).map(mapper::toResponse);

        return ResponseEntity.ok(unidades);
    }

    @DeleteMapping("/excluir-por-id/{id}")
    @Operation(
            summary = "Excluir unidade",
            description = "End point responsável excluir unidade"
    )
    ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
