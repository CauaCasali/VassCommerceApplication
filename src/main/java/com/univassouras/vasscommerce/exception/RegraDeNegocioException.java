package com.univassouras.vasscommerce.exception;

/** Violacao de regra de negocio (ex.: referencia a categoria inexistente). Vira HTTP 400. */
public class RegraDeNegocioException extends RuntimeException {

    public RegraDeNegocioException(String message) {
        super(message);
    }
}
