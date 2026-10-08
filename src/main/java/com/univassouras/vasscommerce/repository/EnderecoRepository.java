package com.univassouras.vasscommerce.repository;

import com.univassouras.vasscommerce.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Optional<Endereco> findByClienteId(Long clienteId);
}
