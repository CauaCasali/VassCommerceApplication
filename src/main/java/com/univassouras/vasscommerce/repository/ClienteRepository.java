package com.univassouras.vasscommerce.repository;

import com.univassouras.vasscommerce.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
