package br.gov.seplag_api_teste.service;

import br.gov.seplag_api_teste.entity.ServidorTemporario;
import br.gov.seplag_api_teste.exception.NotFoundException;
import br.gov.seplag_api_teste.repository.ServidorTemporarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServidorTemporarioService {
    private final ServidorTemporarioRepository repository;

    public ServidorTemporario salvar(ServidorTemporario servidorTemporario) {
        return repository.save(servidorTemporario);
    }

    public ServidorTemporario atualizar(ServidorTemporario servidorTemporario) {
        return repository.save(servidorTemporario);
    }

    public ServidorTemporario obterPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Servidor não encontrado."));
    }

    public void excluir(Long id) {
        var servidorEncontrado = obterPorId(id);

        repository.delete(servidorEncontrado);
    }

    public Page<ServidorTemporario> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
