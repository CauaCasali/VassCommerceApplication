package com.univassouras.vasscommerce.controller;

import com.univassouras.vasscommerce.entity.Cidade;
import com.univassouras.vasscommerce.entity.Estado;
import com.univassouras.vasscommerce.service.EstadoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estado")
public class EstadoController {

    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping
    public List<Estado> listar() {
        return estadoService.listar();
    }

    @GetMapping("/{idestado}/cidade")
    public List<Cidade> listarCidades(@PathVariable Long idestado) {
        return estadoService.listarCidades(idestado);
    }
}
