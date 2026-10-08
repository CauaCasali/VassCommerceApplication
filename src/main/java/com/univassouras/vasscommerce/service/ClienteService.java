package com.univassouras.vasscommerce.service;

import com.univassouras.vasscommerce.entity.Cartao;
import com.univassouras.vasscommerce.entity.Cliente;
import com.univassouras.vasscommerce.entity.Endereco;
import com.univassouras.vasscommerce.entity.Pedido;
import com.univassouras.vasscommerce.exception.ResourceNotFoundException;
import com.univassouras.vasscommerce.repository.CartaoRepository;
import com.univassouras.vasscommerce.repository.ClienteRepository;
import com.univassouras.vasscommerce.repository.EnderecoRepository;
import com.univassouras.vasscommerce.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final CartaoRepository cartaoRepository;
    private final EnderecoRepository enderecoRepository;
    private final PedidoRepository pedidoRepository;

    public ClienteService(ClienteRepository clienteRepository,
                           CartaoRepository cartaoRepository,
                           EnderecoRepository enderecoRepository,
                           PedidoRepository pedidoRepository) {
        this.clienteRepository = clienteRepository;
        this.cartaoRepository = cartaoRepository;
        this.enderecoRepository = enderecoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente nao encontrado: " + id));
    }

    public List<Cartao> listarFormasDePagamento(Long clienteId) {
        buscarPorId(clienteId);
        return cartaoRepository.findByTitularId(clienteId);
    }

    public Endereco buscarEndereco(Long clienteId) {
        buscarPorId(clienteId);
        return enderecoRepository.findByClienteId(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Endereco nao encontrado para o cliente: " + clienteId));
    }

    public List<Pedido> listarPedidos(Long clienteId) {
        buscarPorId(clienteId);
        return pedidoRepository.findByClienteId(clienteId);
    }
}
