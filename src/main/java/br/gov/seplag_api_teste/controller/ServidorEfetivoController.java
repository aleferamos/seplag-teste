package br.gov.seplag_api_teste.controller;

import br.gov.seplag_api_teste.mappers.ServidorEfetivoMapper;
import br.gov.seplag_api_teste.reqres.ServidorEfetivoRequest;
import br.gov.seplag_api_teste.reqres.ServidorEfetivoResponse;
import br.gov.seplag_api_teste.service.ServidorEfetivoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/servidor-efetivo")
@Tag(name = "Servidor Efetivo", description = "Endpoints relacionados a servidores efetivos")
public class ServidorEfetivoController {
    private final ServidorEfetivoService service;
    private final ServidorEfetivoMapper mapper;

    @PostMapping("/salvar")
    @Operation(
            summary = "Salvar servidor efetivo",
            description = "End point responsável por salvar um servidor efetivo.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Servidor salvo com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ServidorEfetivoResponse.class)
                            )
                    )
            }
    )
    ResponseEntity<ServidorEfetivoResponse> salvar(@RequestBody ServidorEfetivoRequest servidorEfetivoRequest){
        var servidorEfeitoSalvo = service.salvar(mapper.toEntity(servidorEfetivoRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(servidorEfeitoSalvo));
    }

    @PutMapping("/atualizar")
    @Operation(
            summary = "Atualizar servidor efetivo",
            description = "End point responsável por atualizar um servidor efetivo.",
            responses = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Servidor atualizar com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ServidorEfetivoResponse.class)
                            )
                    )
            }
    )
    ResponseEntity<ServidorEfetivoResponse> atualizar(@RequestBody ServidorEfetivoRequest servidorEfetivoRequest){
        var servidorEfeitoSalvo = service.atualizar(mapper.toEntity(servidorEfetivoRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(servidorEfeitoSalvo));
    }

}
