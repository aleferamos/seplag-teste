package br.gov.seplag_api_teste.service;

import br.gov.seplag_api_teste.entity.Lotacao;
import br.gov.seplag_api_teste.exception.NotFoundException;
import br.gov.seplag_api_teste.repository.LotacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LotacaoService {
    private final LotacaoRepository repository;

    public Lotacao salvar(Lotacao unidade) {
        return repository.save(unidade);
    }

    public Lotacao atualizar(Lotacao unidade) {
        return repository.save(unidade);
    }

    public Lotacao obterPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Unidade não encontrada."));
    }

    public void excluir(Long id) {
        var lotacaoEncontrada = obterPorId(id);

        repository.delete(lotacaoEncontrada);
    }

    public Page<Lotacao> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
