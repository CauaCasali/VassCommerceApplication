package com.univassouras.vasscommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProdutoResponse(
        Long id,
        String nome,
        String descricao,
        String fotoUrl,
        BigDecimal valorUnitario,
        Long categoriaId,
        LocalDateTime dataCadastro,
        LocalDateTime dataUltimaAtualizacao
) {
}
