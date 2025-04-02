package br.gov.seplag_api_teste.repository;

import br.gov.seplag_api_teste.entity.ServidorEfetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivo, Long> {
    Boolean existsByMatricula(String matricula);
}
