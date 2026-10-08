package com.univassouras.vasscommerce.model;

import com.univassouras.vasscommerce.dto.ProdutoCreateRequest;
import com.univassouras.vasscommerce.entity.Produto;
import com.univassouras.vasscommerce.mapper.ProdutoMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Implementacao em memoria. Ativa com spring.profiles.active=mem.
 * Os dados somem quando a aplicacao reinicia.
 */
@Service("produtoModelMemoria")
@Profile("mem")
public class ProdutoModelMemoria implements ProdutoModel {

    // CopyOnWriteArrayList: o bean e singleton e atende varias requisicoes ao mesmo tempo
    private final List<Produto> banco = new CopyOnWriteArrayList<>();
    private final AtomicLong seq = new AtomicLong(0);

    @Override
    public Produto criar(ProdutoCreateRequest req) {
        Long novoId = seq.incrementAndGet();
        Produto p = ProdutoMapper.toEntity(req, novoId);
        banco.add(p);
        return p;
    }

    @Override
    public Optional<Produto> obterPorId(Long id) {
        return banco.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst();
    }

    @Override
    public List<Produto> buscar(String nome, BigDecimal min, BigDecimal max) {
        return banco.stream()
                .filter(p -> nome == null || nome.isBlank()
                        || p.getNome().toLowerCase().contains(nome.trim().toLowerCase()))
                .filter(p -> min == null || p.getValorUnitario().compareTo(min) >= 0)
                .filter(p -> max == null || p.getValorUnitario().compareTo(max) <= 0)
                .toList();
    }
}
