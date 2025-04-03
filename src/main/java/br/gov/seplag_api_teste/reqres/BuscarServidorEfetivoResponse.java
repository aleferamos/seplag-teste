package br.gov.seplag_api_teste.reqres;

import br.gov.seplag_api_teste.entity.Unidade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuscarServidorEfetivoResponse {
    private String nome;
    private Integer idade;
    private Unidade unidadeLotacao;
    private List<String> linksFotosTemporarias;

    public BuscarServidorEfetivoResponse(
            String nome,
            Integer idade,
            Unidade unidadeLotacao
    ) {
        this.nome = nome;
        this.idade = idade;
        this.unidadeLotacao = unidadeLotacao;
        this.linksFotosTemporarias = new ArrayList<>();
    }
}
