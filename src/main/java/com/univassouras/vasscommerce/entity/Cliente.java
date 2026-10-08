package com.univassouras.vasscommerce.entity;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Cliente extends Usuario {

    private String cpf;
    private LocalDate dataNascimento;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
