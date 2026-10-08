package com.univassouras.vasscommerce.controller;

import com.univassouras.vasscommerce.entity.Categoria;
import com.univassouras.vasscommerce.entity.Produto;
import com.univassouras.vasscommerce.service.CategoriaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<Categoria> listar(@RequestParam(required = false) String nome) {
        return categoriaService.listar(nome);
    }

    @GetMapping("/{idcategoria}/produto")
    public List<Produto> listarProdutos(@PathVariable Long idcategoria) {
        return categoriaService.listarProdutos(idcategoria);
    }
}
