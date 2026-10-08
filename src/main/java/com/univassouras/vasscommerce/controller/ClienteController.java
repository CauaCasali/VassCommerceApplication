package com.univassouras.vasscommerce.controller;

import com.univassouras.vasscommerce.entity.Cartao;
import com.univassouras.vasscommerce.entity.Cliente;
import com.univassouras.vasscommerce.entity.Endereco;
import com.univassouras.vasscommerce.entity.Pedido;
import com.univassouras.vasscommerce.service.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }

    @GetMapping("/{idcliente}/formas-de-pagamento")
    public List<Cartao> listarFormasDePagamento(@PathVariable Long idcliente) {
        return clienteService.listarFormasDePagamento(idcliente);
    }

    @GetMapping("/{idcliente}/endereco")
    public Endereco buscarEndereco(@PathVariable Long idcliente) {
        return clienteService.buscarEndereco(idcliente);
    }

    @GetMapping("/{idcliente}/pedido")
    public List<Pedido> listarPedidos(@PathVariable Long idcliente) {
        return clienteService.listarPedidos(idcliente);
    }
}
