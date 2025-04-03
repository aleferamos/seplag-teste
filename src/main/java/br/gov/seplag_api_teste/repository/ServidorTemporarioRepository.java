package br.gov.seplag_api_teste.repository;

import br.gov.seplag_api_teste.entity.ServidorTemporario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServidorTemporarioRepository extends JpaRepository<ServidorTemporario, Long> { }
