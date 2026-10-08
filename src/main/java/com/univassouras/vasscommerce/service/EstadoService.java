package com.univassouras.vasscommerce.service;

import com.univassouras.vasscommerce.entity.Cidade;
import com.univassouras.vasscommerce.entity.Estado;
import com.univassouras.vasscommerce.exception.ResourceNotFoundException;
import com.univassouras.vasscommerce.repository.CidadeRepository;
import com.univassouras.vasscommerce.repository.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    private final EstadoRepository estadoRepository;
    private final CidadeRepository cidadeRepository;

    public EstadoService(EstadoRepository estadoRepository, CidadeRepository cidadeRepository) {
        this.estadoRepository = estadoRepository;
        this.cidadeRepository = cidadeRepository;
    }

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Estado buscarPorId(Long id) {
        return estadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado nao encontrado: " + id));
    }

    public List<Cidade> listarCidades(Long estadoId) {
        buscarPorId(estadoId);
        return cidadeRepository.findByEstadoId(estadoId);
    }
}
