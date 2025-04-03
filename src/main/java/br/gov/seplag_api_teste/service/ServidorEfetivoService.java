package br.gov.seplag_api_teste.service;

import br.gov.seplag_api_teste.entity.ServidorEfetivo;
import br.gov.seplag_api_teste.exception.BusinessException;
import br.gov.seplag_api_teste.exception.NotFoundException;
import br.gov.seplag_api_teste.repository.ServidorEfetivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class ServidorEfetivoService {
    private final ServidorEfetivoRepository repository;

    public ServidorEfetivo salvar(ServidorEfetivo servidorEfetivo){
        validarServidor(servidorEfetivo);
        return repository.save(servidorEfetivo);
    }

    public ServidorEfetivo atualizar(ServidorEfetivo servidorEfetivo){
        var servidorEncontrado = obterPorId(servidorEfetivo.getId());

        if(!servidorEncontrado.getMatricula().equals(servidorEfetivo.getMatricula())){
            validarServidor(servidorEfetivo);
        }

        if(!ObjectUtils.isEmpty(servidorEfetivo.getPessoa())){
            servidorEfetivo.getPessoa().setId(servidorEncontrado.getPessoa().getId());
        }

        return repository.save(servidorEfetivo);
    }

    public ServidorEfetivo obterPorId(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Servidor não encontrado."));
    }

    public void excluir(Long id){
        var servidorEncontrado = obterPorId(id);

        repository.delete(servidorEncontrado);
    }

    private void validarServidor(ServidorEfetivo servidorEfetivo){
        if(Boolean.TRUE.equals(repository.existsByMatricula(servidorEfetivo.getMatricula()))){
            throw new BusinessException("Servidor com essa matricula já está cadastrado.");
        }
    }

    public Page<ServidorEfetivo> listar(Pageable pageable){
        return repository.findAll(pageable);
    }
}
