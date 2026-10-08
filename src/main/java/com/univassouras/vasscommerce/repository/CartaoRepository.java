package com.univassouras.vasscommerce.repository;

import com.univassouras.vasscommerce.entity.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    List<Cartao> findByTitularId(Long clienteId);
}
