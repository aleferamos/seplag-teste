package br.gov.seplag_api_teste.controller;

import br.gov.seplag_api_teste.mappers.ServidorEfetivoMapper;
import br.gov.seplag_api_teste.reqres.ServidorEfetivoRequest;
import br.gov.seplag_api_teste.reqres.ServidorEfetivoResponse;
import br.gov.seplag_api_teste.service.ServidorEfetivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/servidor-efetivo")
public class ServidorEfetivoController {
    private final ServidorEfetivoService service;
    private final ServidorEfetivoMapper mapper;

    ResponseEntity<ServidorEfetivoResponse> salvar(@RequestBody ServidorEfetivoRequest servidorEfetivoRequest){
        var servidorEfeitoSalvo = service.salvar(mapper.toEntity(servidorEfetivoRequest));

        return ResponseEntity.ok(mapper.toResponse(servidorEfeitoSalvo));
    }
}
