package br.gov.seplag_api_teste.controller;

import br.gov.seplag_api_teste.mappers.LotacaoMapper;
import br.gov.seplag_api_teste.reqres.LotacaoRequest;
import br.gov.seplag_api_teste.reqres.LotacaoResponse;
import br.gov.seplag_api_teste.service.LotacaoService;
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
@RequestMapping("/lotacao")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Lotação", description = "Endpoints relacionados a Lotação")
public class LotacaoController {
    private final LotacaoService service;
    private final LotacaoMapper mapper;

    @PostMapping("/salvar")
    @Operation(
            summary = "Salvar lotação",
            description = "End point responsável por salvar salvar lotação."
    )
    ResponseEntity<LotacaoResponse> salvar(@RequestBody LotacaoRequest lotacaoRequest) {
        var lotacaoSalva = service.salvar(mapper.toEntity(lotacaoRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(lotacaoSalva));
    }

    @PutMapping("/atualizar")
    @Operation(
            summary = "Atualizar lotação",
            description = "End point responsável por atualizar lotação."
    )
    ResponseEntity<LotacaoResponse> atualizar(@RequestBody LotacaoRequest lotacaoRequest) {
        var lotacaoSalva = service.atualizar(mapper.toEntity(lotacaoRequest));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.toResponse(lotacaoSalva));
    }

    @GetMapping("/buscar-por-id/{id}")
    @Operation(
            summary = "Buscar lotação por id",
            description = "End point responsável buscar lotação por id"
    )
    ResponseEntity<LotacaoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toResponse(service.obterPorId(id)));
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar lotações",
            description = "End point responsável listar lotações"
    )
    ResponseEntity<Page<LotacaoResponse>> listar(@Parameter(hidden = true) Pageable pageable) {
        var lotacoes = service.listar(pageable).map(mapper::toResponse);

        return ResponseEntity.ok(lotacoes);
    }

    @DeleteMapping("/excluir-por-id/{id}")
    @Operation(
            summary = "Excluir lotação",
            description = "End point responsável excluir lotação"
    )
    ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
