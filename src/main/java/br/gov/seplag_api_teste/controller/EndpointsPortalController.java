package br.gov.seplag_api_teste.controller;

import br.gov.seplag_api_teste.mappers.FotoPessoaMapper;
import br.gov.seplag_api_teste.reqres.BuscarServidorEfetivoResponse;
import br.gov.seplag_api_teste.reqres.FotoPessoaRequest;
import br.gov.seplag_api_teste.reqres.FotoPessoaResponse;
import br.gov.seplag_api_teste.service.FotoPessoaService;
import br.gov.seplag_api_teste.service.ServidorEfetivoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/endpoints-edital")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Endpoints edital", description = "Endpoints relacionados ao edital")
public class EndpointsPortalController {
    private final FotoPessoaService service;
    private final FotoPessoaMapper mapper;
    private final ServidorEfetivoService servidorEfetivoService;

    @PostMapping("/upload-fotos")
    @Operation(
            summary = "Realizar o upload de uma ou mais fotografias enviando-as para o Min.IO;",
            description = "Realizar o upload de uma ou mais fotografias enviando-as para o Min.IO;"
    )
    ResponseEntity<List<FotoPessoaResponse>> uploadFotos(@RequestBody List<FotoPessoaRequest> fotos) {
        var fotosSalvas = service.salvar(fotos.stream().map(mapper::toEntity).toList());
        return ResponseEntity.status(HttpStatus.CREATED).body(fotosSalvas.stream().map(mapper::toResponse).toList());
    }

    @GetMapping("/consultar-servidores-efetivos/{idUnidade}")
    @Operation(
            summary = "Consulta servidor efetivo",
            description = "Endpoint responsavel por consultar servidores efetivos por unidade. A recuperação das "
                    + "imagens deverá ser através de links temporários gerados pela biblioteca do Min.IO com tempo "
                    + "de expiração de 5 minutos."
    )
    ResponseEntity<List<BuscarServidorEfetivoResponse>> salvar(@PathVariable Long idUnidade) {
        return ResponseEntity.ok(servidorEfetivoService.buscarServidoresEfetivosPorUnidade(idUnidade));
    }
}
