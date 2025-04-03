package br.gov.seplag_api_teste.repository;

import br.gov.seplag_api_teste.entity.ServidorEfetivo;
import br.gov.seplag_api_teste.reqres.BuscarServidorEfetivoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivo, Long> {
    Boolean existsByMatricula(String matricula);

    @Query("select distinct new br.gov.seplag_api_teste.reqres.BuscarServidorEfetivoResponse(p.nome, p.idade, "
            + "l.unidade, fp.nomeArquivo) "
            + "from ServidorEfetivo se "
            + "join Lotacao l on l.pessoa.id = se.pessoa.id "
            + "join l.pessoa p "
            + "join FotoPessoa fp on fp.pessoa.id = se.pessoa.id "
            + "where l.unidade.id = :unidadeId")
    List<BuscarServidorEfetivoResponse> buscarServidoresEfetivosPorUnidade(Long unidadeId);
}
