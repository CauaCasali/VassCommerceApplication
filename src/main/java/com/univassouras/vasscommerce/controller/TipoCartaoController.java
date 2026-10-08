package com.univassouras.vasscommerce.controller;

import com.univassouras.vasscommerce.entity.TipoCartao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tipo-cartao")
public class TipoCartaoController {

    @GetMapping
    public TipoCartao[] listar() {
        return TipoCartao.values();
    }
}
