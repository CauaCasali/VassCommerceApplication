package com.univassouras.vasscommerce.service;

import com.univassouras.vasscommerce.entity.Categoria;
import com.univassouras.vasscommerce.entity.Produto;
import com.univassouras.vasscommerce.exception.ResourceNotFoundException;
import com.univassouras.vasscommerce.repository.CategoriaRepository;
import com.univassouras.vasscommerce.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;

    public CategoriaService(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<Categoria> listar(String nome) {
        if (nome == null || nome.isBlank()) {
            return categoriaRepository.findAll();
        }
        return categoriaRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria nao encontrada: " + id));
    }

    public List<Produto> listarProdutos(Long categoriaId) {
        buscarPorId(categoriaId);
        return produtoRepository.findByCategoriaId(categoriaId);
    }
}
