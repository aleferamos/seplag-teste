package br.gov.seplag_api_teste.controller;

import br.gov.seplag_api_teste.mappers.ServidorTemporarioMapper;
import br.gov.seplag_api_teste.reqres.ServidorEfetivoResponse;
import br.gov.seplag_api_teste.reqres.ServidorTemporarioRequest;
import br.gov.seplag_api_teste.reqres.ServidorTemporarioResponse;
import br.gov.seplag_api_teste.service.ServidorTemporarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RequestMapping("/servidor-temporario")
@Tag(name = "Servidor Temporario", description = "Endpoints relacionados a servidores temporarios")
public class ServidorTemporarioController {
    private final ServidorTemporarioService service;
    private final ServidorTemporarioMapper mapper;

    @PostMapping("/salvar")
    @Operation(
            summary = "Salvar servidor efetivo",
            description = "End point responsável por salvar um servidor efetivo.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "pessoa": {
                                                "nome": "string",
                                                "dataNascimento": "2025-04-03",
                                                "sexo": "string",
                                                "nomeMae": "string",
                                                "nomePai": "string"
                                              },
                                              "dataAdmissao": "2025-04-03",
                                              "dataDemissao": "2025-04-03"
                                            }
                                            """
                            )
                    )
            )
    )
    ResponseEntity<ServidorTemporarioResponse> salvar(@RequestBody ServidorTemporarioRequest servidorTemporarioRequest) {
        var servidorEfeitoSalvo = service.salvar(mapper.toEntity(servidorTemporarioRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(servidorEfeitoSalvo));
    }

    @PutMapping("/atualizar")
    @Operation(
            summary = "Atualizar servidor efetivo",
            description = "End point responsável por atualizar um servidor efetivo."
    )
    ResponseEntity<ServidorTemporarioResponse> atualizar(@RequestBody ServidorTemporarioRequest servidorTemporarioRequest) {
        var servidorEfeitoSalvo = service.atualizar(mapper.toEntity(servidorTemporarioRequest));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.toResponse(servidorEfeitoSalvo));
    }

    @GetMapping("/buscar-por-id/{id}")
    @Operation(
            summary = "Buscar servidor efetivo por id",
            description = "End point responsável buscar servidor efetivo por id"
    )
    ResponseEntity<ServidorTemporarioResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toResponse(service.obterPorId(id)));
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar Servidor",
            description = "End point responsável buscar servidor Listar Servidor",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Servidor retornado com sucesso"
                    )
            }
    )
    ResponseEntity<Page<ServidorTemporarioResponse>> listar(@Parameter(hidden = true) Pageable pageable) {
        var servidores = service.listar(pageable).map(mapper::toResponse);

        return ResponseEntity.ok(servidores);
    }

    @DeleteMapping("/excluir-por-id/{id}")
    @Operation(
            summary = "Excluir servidor",
            description = "End point responsável excluir servidor"
    )
    ResponseEntity<Page<ServidorEfetivoResponse>> excluir(@PathVariable Long id) {
        service.excluir(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
