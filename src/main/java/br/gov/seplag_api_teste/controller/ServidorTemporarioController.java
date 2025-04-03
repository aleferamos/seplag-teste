package br.gov.seplag_api_teste.controller;

import br.gov.seplag_api_teste.mappers.ServidorTemporarioMapper;
import br.gov.seplag_api_teste.reqres.ServidorTemporarioRequest;
import br.gov.seplag_api_teste.reqres.ServidorTemporarioResponse;
import br.gov.seplag_api_teste.service.ServidorTemporarioService;
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
@RequestMapping("/servidor-temporario")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Servidor Temporario", description = "Endpoints relacionados a servidores temporarios")
public class ServidorTemporarioController {
    private final ServidorTemporarioService service;
    private final ServidorTemporarioMapper mapper;

    @PostMapping("/salvar")
    @Operation(
            summary = "Salvar servidor temporario",
            description = "End point responsável por salvar um servidor temporario."
    )
    ResponseEntity<ServidorTemporarioResponse> salvar(@RequestBody ServidorTemporarioRequest servidorTemporarioRequest) {
        var servidorEfeitoSalvo = service.salvar(mapper.toEntity(servidorTemporarioRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(servidorEfeitoSalvo));
    }

    @PutMapping("/atualizar")
    @Operation(
            summary = "Atualizar servidor temporario",
            description = "End point responsável por atualizar um servidor temporario."
    )
    ResponseEntity<ServidorTemporarioResponse> atualizar(@RequestBody ServidorTemporarioRequest servidorTemporarioRequest) {
        var servidorEfeitoSalvo = service.atualizar(mapper.toEntity(servidorTemporarioRequest));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.toResponse(servidorEfeitoSalvo));
    }

    @GetMapping("/buscar-por-id/{id}")
    @Operation(
            summary = "Buscar servidor temporario por id",
            description = "End point responsável buscar servidor temporario por id"
    )
    ResponseEntity<ServidorTemporarioResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toResponse(service.obterPorId(id)));
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar Servidor",
            description = "End point responsável buscar servidor Listar Servidor"
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
    ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
