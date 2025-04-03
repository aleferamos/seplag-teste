package br.gov.seplag_api_teste.repository;

import br.gov.seplag_api_teste.entity.FotoPessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FotoPessoaRepository extends JpaRepository<FotoPessoa, Long> {
    List<FotoPessoa> findAllByPessoaId(Long id);
}
