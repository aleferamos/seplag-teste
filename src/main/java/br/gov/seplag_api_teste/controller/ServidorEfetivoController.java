package br.gov.seplag_api_teste.controller;

import br.gov.seplag_api_teste.mappers.ServidorEfetivoMapper;
import br.gov.seplag_api_teste.reqres.ServidorEfetivoRequest;
import br.gov.seplag_api_teste.reqres.ServidorEfetivoResponse;
import br.gov.seplag_api_teste.service.ServidorEfetivoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/servidor-efetivo")
@Tag(name = "Servidor Efetivo", description = "Endpoints relacionados a servidores efetivos")
public class ServidorEfetivoController {
    private final ServidorEfetivoService service;
    private final ServidorEfetivoMapper mapper;

    @PostMapping("/salvar")
    @Operation(
            summary = "Salvar servidor efetivo",
            description = "End point responsável por salvar um servidor efetivo."
    )
    ResponseEntity<ServidorEfetivoResponse> salvar(@RequestBody ServidorEfetivoRequest servidorEfetivoRequest) {
        var servidorEfeitoSalvo = service.salvar(mapper.toEntity(servidorEfetivoRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(servidorEfeitoSalvo));
    }

    @PutMapping("/atualizar")
    @Operation(
            summary = "Atualizar servidor efetivo",
            description = "End point responsável por atualizar um servidor efetivo."
    )
    ResponseEntity<ServidorEfetivoResponse> atualizar(@RequestBody ServidorEfetivoRequest servidorEfetivoRequest) {
        var servidorEfeitoSalvo = service.atualizar(mapper.toEntity(servidorEfetivoRequest));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.toResponse(servidorEfeitoSalvo));
    }

    @GetMapping("/buscar-por-id/{id}")
    @Operation(
            summary = "Buscar servidor efetivo por id",
            description = "End point responsável buscar servidor efetivo por id"
    )
    ResponseEntity<ServidorEfetivoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toResponse(service.obterPorId(id)));
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar Servidor",
            description = "End point responsável buscar servidor Listar Servidor"
    )
    ResponseEntity<Page<ServidorEfetivoResponse>> listar(@Parameter(hidden = true) Pageable pageable) {
        var servidores = service.listar(pageable).map(mapper::toResponse);

        return ResponseEntity.ok(servidores);
    }

    @DeleteMapping("/excluir-por-id/{id}")
    @Operation(
            summary = "Excluir servidor",
            description = "End point responsável excluir servidor"
    )
    ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
