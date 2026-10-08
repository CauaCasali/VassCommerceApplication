package com.univassouras.vasscommerce.mapper;

import com.univassouras.vasscommerce.dto.ProdutoCreateRequest;
import com.univassouras.vasscommerce.dto.ProdutoResponse;
import com.univassouras.vasscommerce.entity.Categoria;
import com.univassouras.vasscommerce.entity.Produto;

import java.time.LocalDateTime;

public final class ProdutoMapper {

    private ProdutoMapper() {
    }

    /** Request DTO -> Entidade. Em implementacoes que geram o id sozinhas (banco), passe id = null. */
    public static Produto toEntity(ProdutoCreateRequest req, Long id) {
        LocalDateTime agora = LocalDateTime.now();

        Produto p = new Produto();
        p.setId(id);
        p.setNome(req.nome().trim());
        p.setDescricao(req.descricao());
        p.setFotoUrl(req.fotoUrl());
        p.setValorUnitario(req.valorUnitario());
        p.setDataCadastro(agora);
        p.setDataUltimaAtualizacao(agora);

        if (req.categoriaId() != null) {
            // referencia apenas pelo id; implementacoes com banco substituem pela entidade real
            Categoria categoria = new Categoria();
            categoria.setId(req.categoriaId());
            p.setCategoria(categoria);
        }
        return p;
    }

    /** Entidade -> Response DTO. */
    public static ProdutoResponse toResponse(Produto p) {
        return new ProdutoResponse(
                p.getId(),
                p.getNome(),
                p.getDescricao(),
                p.getFotoUrl(),
                p.getValorUnitario(),
                p.getCategoria() != null ? p.getCategoria().getId() : null,
                p.getDataCadastro(),
                p.getDataUltimaAtualizacao()
        );
    }
}
