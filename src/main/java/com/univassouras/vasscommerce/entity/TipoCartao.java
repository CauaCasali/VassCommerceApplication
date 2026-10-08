package com.univassouras.vasscommerce.entity;

public enum TipoCartao {
    DEBITO(1),
    CREDITO(2);

    private final int codigo;

    TipoCartao(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
}
