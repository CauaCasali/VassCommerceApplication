package com.univassouras.vasscommerce.model;

import com.univassouras.vasscommerce.dto.ProdutoCreateRequest;
import com.univassouras.vasscommerce.entity.Categoria;
import com.univassouras.vasscommerce.entity.Produto;
import com.univassouras.vasscommerce.exception.RegraDeNegocioException;
import com.univassouras.vasscommerce.mapper.ProdutoMapper;
import com.univassouras.vasscommerce.repository.CategoriaRepository;
import com.univassouras.vasscommerce.repository.ProdutoRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementacao que representa um backend relacional. Ativa com spring.profiles.active=sql.
 * Aqui ela usa o JPA/H2 que ja existe no projeto (o mesmo banco populado pelo DataSeeder).
 * Trocar por Postgres/MySQL so exigiria mudar a configuracao do datasource.
 */
@Service("produtoModelSql")
@Profile("sql")
public class ProdutoModelSql implements ProdutoModel {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoModelSql(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Produto criar(ProdutoCreateRequest req) {
        Categoria categoria = categoriaRepository.findById(req.categoriaId())
                .orElseThrow(() -> new RegraDeNegocioException(
                        "Categoria nao encontrada: " + req.categoriaId()));

        Produto p = ProdutoMapper.toEntity(req, null); // id gerado pelo banco
        p.setCategoria(categoria);
        return produtoRepository.save(p);
    }

    @Override
    public Optional<Produto> obterPorId(Long id) {
        return produtoRepository.findById(id);
    }

    @Override
    public List<Produto> buscar(String nome, BigDecimal min, BigDecimal max) {
        List<Specification<Produto>> filtros = new ArrayList<>();

        if (nome != null && !nome.isBlank()) {
            String like = "%" + nome.trim().toLowerCase() + "%";
            filtros.add((root, query, cb) -> cb.like(cb.lower(root.get("nome")), like));
        }
        if (min != null) {
            filtros.add((root, query, cb) -> cb.greaterThanOrEqualTo(root.<BigDecimal>get("valorUnitario"), min));
        }
        if (max != null) {
            filtros.add((root, query, cb) -> cb.lessThanOrEqualTo(root.<BigDecimal>get("valorUnitario"), max));
        }

        return produtoRepository.findAll(Specification.allOf(filtros));
    }
}
