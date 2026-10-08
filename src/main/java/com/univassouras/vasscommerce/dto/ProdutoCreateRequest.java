package com.univassouras.vasscommerce.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProdutoCreateRequest(

        @NotBlank(message = "o nome e obrigatorio")
        @Size(max = 120, message = "o nome deve ter no maximo 120 caracteres")
        String nome,

        @Size(max = 255, message = "a descricao deve ter no maximo 255 caracteres")
        String descricao,

        @Size(max = 255, message = "a fotoUrl deve ter no maximo 255 caracteres")
        String fotoUrl,

        @NotNull(message = "o valorUnitario e obrigatorio")
        @DecimalMin(value = "0.01", message = "o valorUnitario deve ser maior que zero")
        @Digits(integer = 10, fraction = 2, message = "o valorUnitario aceita ate 10 inteiros e 2 casas decimais")
        BigDecimal valorUnitario,

        @NotNull(message = "a categoriaId e obrigatoria")
        @Positive(message = "a categoriaId deve ser positiva")
        Long categoriaId
) {
}
