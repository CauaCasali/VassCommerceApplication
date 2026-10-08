package com.univassouras.vasscommerce.seed;

import com.univassouras.vasscommerce.entity.Administrador;
import com.univassouras.vasscommerce.entity.Cartao;
import com.univassouras.vasscommerce.entity.Categoria;
import com.univassouras.vasscommerce.entity.Cidade;
import com.univassouras.vasscommerce.entity.Cliente;
import com.univassouras.vasscommerce.entity.Endereco;
import com.univassouras.vasscommerce.entity.Estado;
import com.univassouras.vasscommerce.entity.ItemPedido;
import com.univassouras.vasscommerce.entity.Pagamento;
import com.univassouras.vasscommerce.entity.Pedido;
import com.univassouras.vasscommerce.entity.PedidoStatus;
import com.univassouras.vasscommerce.entity.PromocaoItem;
import com.univassouras.vasscommerce.entity.Produto;
import com.univassouras.vasscommerce.entity.TabelaPromocao;
import com.univassouras.vasscommerce.entity.TipoCartao;
import com.univassouras.vasscommerce.repository.AdministradorRepository;
import com.univassouras.vasscommerce.repository.CartaoRepository;
import com.univassouras.vasscommerce.repository.CategoriaRepository;
import com.univassouras.vasscommerce.repository.CidadeRepository;
import com.univassouras.vasscommerce.repository.ClienteRepository;
import com.univassouras.vasscommerce.repository.EnderecoRepository;
import com.univassouras.vasscommerce.repository.EstadoRepository;
import com.univassouras.vasscommerce.repository.ItemPedidoRepository;
import com.univassouras.vasscommerce.repository.PagamentoRepository;
import com.univassouras.vasscommerce.repository.PedidoRepository;
import com.univassouras.vasscommerce.repository.PromocaoItemRepository;
import com.univassouras.vasscommerce.repository.ProdutoRepository;
import com.univassouras.vasscommerce.repository.TabelaPromocaoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataSeeder implements CommandLineRunner {

    private final EstadoRepository estadoRepository;
    private final CidadeRepository cidadeRepository;
    private final CategoriaRepository categoriaRepository;
    private final AdministradorRepository administradorRepository;
    private final ProdutoRepository produtoRepository;
    private final TabelaPromocaoRepository tabelaPromocaoRepository;
    private final PromocaoItemRepository promocaoItemRepository;
    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final CartaoRepository cartaoRepository;
    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final PagamentoRepository pagamentoRepository;

    public DataSeeder(EstadoRepository estadoRepository,
                       CidadeRepository cidadeRepository,
                       CategoriaRepository categoriaRepository,
                       AdministradorRepository administradorRepository,
                       ProdutoRepository produtoRepository,
                       TabelaPromocaoRepository tabelaPromocaoRepository,
                       PromocaoItemRepository promocaoItemRepository,
                       ClienteRepository clienteRepository,
                       EnderecoRepository enderecoRepository,
                       CartaoRepository cartaoRepository,
                       PedidoRepository pedidoRepository,
                       ItemPedidoRepository itemPedidoRepository,
                       PagamentoRepository pagamentoRepository) {
        this.estadoRepository = estadoRepository;
        this.cidadeRepository = cidadeRepository;
        this.categoriaRepository = categoriaRepository;
        this.administradorRepository = administradorRepository;
        this.produtoRepository = produtoRepository;
        this.tabelaPromocaoRepository = tabelaPromocaoRepository;
        this.promocaoItemRepository = promocaoItemRepository;
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.cartaoRepository = cartaoRepository;
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.pagamentoRepository = pagamentoRepository;
    }

    @Override
    public void run(String... args) {
        LocalDateTime agora = LocalDateTime.now();

        Estado sp = new Estado();
        sp.setNome("Sao Paulo");
        sp.setSigla("SP");
        estadoRepository.save(sp);

        Estado rj = new Estado();
        rj.setNome("Rio de Janeiro");
        rj.setSigla("RJ");
        estadoRepository.save(rj);

        Cidade vassouras = new Cidade();
        vassouras.setNome("Vassouras");
        vassouras.setEstado(rj);
        cidadeRepository.save(vassouras);

        Cidade saoPauloCidade = new Cidade();
        saoPauloCidade.setNome("Sao Paulo");
        saoPauloCidade.setEstado(sp);
        cidadeRepository.save(saoPauloCidade);

        Categoria eletronicos = new Categoria();
        eletronicos.setNome("Eletronicos");
        eletronicos.setDescricao("Produtos eletronicos em geral");
        eletronicos.setImagemSimboloUrl("https://exemplo.com/icones/eletronicos.png");
        categoriaRepository.save(eletronicos);

        Categoria livros = new Categoria();
        livros.setNome("Livros");
        livros.setDescricao("Livros fisicos e digitais");
        livros.setImagemSimboloUrl("https://exemplo.com/icones/livros.png");
        categoriaRepository.save(livros);

        Administrador adminEletronicos = new Administrador();
        adminEletronicos.setNomeCompleto("Ana Souza");
        adminEletronicos.setEmail("ana.souza@vasscommerce.com");
        adminEletronicos.setSenha("senha123");
        adminEletronicos.setDataCadastro(agora);
        adminEletronicos.setDataUltimaAtualizacao(agora);
        adminEletronicos.setCategoriaResponsavel(eletronicos);
        administradorRepository.save(adminEletronicos);

        Administrador adminLivros = new Administrador();
        adminLivros.setNomeCompleto("Bruno Lima");
        adminLivros.setEmail("bruno.lima@vasscommerce.com");
        adminLivros.setSenha("senha123");
        adminLivros.setDataCadastro(agora);
        adminLivros.setDataUltimaAtualizacao(agora);
        adminLivros.setCategoriaResponsavel(livros);
        administradorRepository.save(adminLivros);

        Produto fone = new Produto();
        fone.setNome("Fone de Ouvido Bluetooth");
        fone.setDescricao("Fone sem fio com cancelamento de ruido");
        fone.setFotoUrl("https://exemplo.com/produtos/fone.png");
        fone.setValorUnitario(new BigDecimal("199.90"));
        fone.setDataCadastro(agora);
        fone.setDataUltimaAtualizacao(agora);
        fone.setCategoria(eletronicos);
        produtoRepository.save(fone);

        Produto notebook = new Produto();
        notebook.setNome("Notebook 15 polegadas");
        notebook.setDescricao("Notebook para uso profissional");
        notebook.setFotoUrl("https://exemplo.com/produtos/notebook.png");
        notebook.setValorUnitario(new BigDecimal("3499.00"));
        notebook.setDataCadastro(agora);
        notebook.setDataUltimaAtualizacao(agora);
        notebook.setCategoria(eletronicos);
        produtoRepository.save(notebook);

        Produto livroJava = new Produto();
        livroJava.setNome("Java para Iniciantes");
        livroJava.setDescricao("Livro introdutorio de programacao Java");
        livroJava.setFotoUrl("https://exemplo.com/produtos/livro-java.png");
        livroJava.setValorUnitario(new BigDecimal("89.90"));
        livroJava.setDataCadastro(agora);
        livroJava.setDataUltimaAtualizacao(agora);
        livroJava.setCategoria(livros);
        produtoRepository.save(livroJava);

        TabelaPromocao blackFriday = new TabelaPromocao();
        blackFriday.setNome("Black Friday");
        blackFriday.setDescricao("Promocao de fim de ano");
        blackFriday.setDataInicio(LocalDate.now().minusDays(5));
        blackFriday.setDataFim(LocalDate.now().plusDays(5));
        blackFriday.setDataCadastro(agora);
        blackFriday.setDataUltimaAtualizacao(agora);
        blackFriday.setCadastradoPor(adminEletronicos);
        tabelaPromocaoRepository.save(blackFriday);

        PromocaoItem descontoFone = new PromocaoItem();
        descontoFone.setValorPromocao(new BigDecimal("149.90"));
        descontoFone.setDataCadastro(agora);
        descontoFone.setDataUltimaAtualizacao(agora);
        descontoFone.setProduto(fone);
        descontoFone.setTabelaPromocao(blackFriday);
        promocaoItemRepository.save(descontoFone);

        Cliente cliente = new Cliente();
        cliente.setNomeCompleto("Carlos Pereira");
        cliente.setEmail("carlos.pereira@email.com");
        cliente.setSenha("senha123");
        cliente.setFotoUrl("https://exemplo.com/clientes/carlos.png");
        cliente.setCpf("12345678900");
        cliente.setDataNascimento(LocalDate.of(1995, 4, 12));
        cliente.setDataCadastro(agora);
        cliente.setDataUltimaAtualizacao(agora);
        clienteRepository.save(cliente);

        Cliente clienteDois = new Cliente();
        clienteDois.setNomeCompleto("Fernanda Alves");
        clienteDois.setEmail("fernanda.alves@email.com");
        clienteDois.setSenha("senha123");
        clienteDois.setFotoUrl("https://exemplo.com/clientes/fernanda.png");
        clienteDois.setCpf("98765432100");
        clienteDois.setDataNascimento(LocalDate.of(1990, 8, 23));
        clienteDois.setDataCadastro(agora);
        clienteDois.setDataUltimaAtualizacao(agora);
        clienteRepository.save(clienteDois);

        Endereco enderecoCarlos = new Endereco();
        enderecoCarlos.setRua("Rua das Flores");
        enderecoCarlos.setNumero("123");
        enderecoCarlos.setCep("27700-000");
        enderecoCarlos.setComplemento("Apto 101");
        enderecoCarlos.setTelefone("(24) 99999-0000");
        enderecoCarlos.setBairro("Centro");
        enderecoCarlos.setDataCadastro(agora);
        enderecoCarlos.setDataUltimaAtualizacao(agora);
        enderecoCarlos.setCidade(vassouras);
        enderecoCarlos.setCliente(cliente);
        enderecoRepository.save(enderecoCarlos);

        Cartao cartaoCredito = new Cartao();
        cartaoCredito.setDataCriacao(agora);
        cartaoCredito.setExcluido(false);
        cartaoCredito.setTipo(TipoCartao.CREDITO);
        cartaoCredito.setTitular(cliente);
        cartaoRepository.save(cartaoCredito);

        Cartao cartaoDebito = new Cartao();
        cartaoDebito.setDataCriacao(agora);
        cartaoDebito.setExcluido(false);
        cartaoDebito.setTipo(TipoCartao.DEBITO);
        cartaoDebito.setTitular(cliente);
        cartaoRepository.save(cartaoDebito);

        Pedido pedido = new Pedido();
        pedido.setDataCadastro(agora);
        pedido.setValorTotal(new BigDecimal("349.80"));
        pedido.setStatus(PedidoStatus.AGUARDANDO_PAGAMENTO);
        pedido.setCliente(cliente);
        pedidoRepository.save(pedido);

        ItemPedido itemFone = new ItemPedido();
        itemFone.setQuantidade(1);
        itemFone.setValorUnitario(new BigDecimal("149.90"));
        itemFone.setPedido(pedido);
        itemFone.setProduto(fone);
        itemPedidoRepository.save(itemFone);

        ItemPedido itemLivro = new ItemPedido();
        itemLivro.setQuantidade(1);
        itemLivro.setValorUnitario(new BigDecimal("89.90"));
        itemLivro.setPedido(pedido);
        itemLivro.setProduto(livroJava);
        itemPedidoRepository.save(itemLivro);

        Pagamento pagamento = new Pagamento();
        pagamento.setValorPago(new BigDecimal("349.80"));
        pagamento.setPedido(pedido);
        pagamento.setCartao(cartaoCredito);
        pagamento.setCliente(cliente);
        pagamentoRepository.save(pagamento);
    }
}
