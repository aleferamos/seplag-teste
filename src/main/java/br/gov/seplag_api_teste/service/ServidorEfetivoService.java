package br.gov.seplag_api_teste.service;

import br.gov.seplag_api_teste.entity.ServidorEfetivo;
import br.gov.seplag_api_teste.repository.ServidorEfetivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServidorEfetivoService {
    private final ServidorEfetivoRepository repository;

    public ServidorEfetivo salvar(ServidorEfetivo servidorEfetivo){
        return repository.save(servidorEfetivo);
    }
}
