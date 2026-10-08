package com.univassouras.vasscommerce.model;

import com.univassouras.vasscommerce.dto.ProdutoCreateRequest;
import com.univassouras.vasscommerce.entity.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Contrato da regra de negocio de Produto.
 * O controller depende somente desta interface (IoC/DI).
 */
public interface ProdutoModel {

    Produto criar(ProdutoCreateRequest req);               // regra de criacao

    Optional<Produto> obterPorId(Long id);                 // consulta por id

    List<Produto> buscar(String nome,
                         BigDecimal valorMinimo,
                         BigDecimal valorMaximo);          // filtros opcionais
}
