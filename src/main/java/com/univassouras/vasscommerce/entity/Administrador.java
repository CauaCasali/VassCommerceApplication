package com.univassouras.vasscommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Administrador extends Usuario {

    @OneToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoriaResponsavel;

    public Categoria getCategoriaResponsavel() {
        return categoriaResponsavel;
    }

    public void setCategoriaResponsavel(Categoria categoriaResponsavel) {
        this.categoriaResponsavel = categoriaResponsavel;
    }
}
