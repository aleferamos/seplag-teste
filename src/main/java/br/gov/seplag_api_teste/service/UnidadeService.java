package br.gov.seplag_api_teste.service;

import br.gov.seplag_api_teste.entity.Unidade;
import br.gov.seplag_api_teste.exception.NotFoundException;
import br.gov.seplag_api_teste.repository.UnidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnidadeService {
    private final UnidadeRepository repository;

    public Unidade salvar(Unidade unidade) {
        return repository.save(unidade);
    }

    public Unidade atualizar(Unidade unidade) {
        return repository.save(unidade);
    }

    public Unidade obterPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Unidade não encontrada."));
    }

    public void excluir(Long id) {
        var unidadeEncontrada = obterPorId(id);

        repository.delete(unidadeEncontrada);
    }

    public Page<Unidade> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
