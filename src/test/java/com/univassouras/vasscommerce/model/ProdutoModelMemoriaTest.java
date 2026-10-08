package com.univassouras.vasscommerce.model;

import com.univassouras.vasscommerce.dto.ProdutoCreateRequest;
import com.univassouras.vasscommerce.entity.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Teste unitario puro (sem Spring): a implementacao e testada pelo contrato da interface. */
class ProdutoModelMemoriaTest {

    private ProdutoModel model;

    @BeforeEach
    void setUp() {
        model = new ProdutoModelMemoria();
        model.criar(req("Fone Bluetooth", "199.90"));
        model.criar(req("Notebook 15", "3499.00"));
        model.criar(req("Livro de Java", "89.90"));
    }

    @Test
    void criarGeraIdsSequenciais() {
        Produto novo = model.criar(req("Mouse", "50.00"));
        assertEquals(4L, novo.getId());
    }

    @Test
    void obterPorIdEncontraEDevolveVazioQuandoNaoExiste() {
        assertEquals("Notebook 15", model.obterPorId(2L).orElseThrow().getNome());
        assertTrue(model.obterPorId(99L).isEmpty());
    }

    @Test
    void buscarSemFiltrosDevolveTodos() {
        assertEquals(3, model.buscar(null, null, null).size());
    }

    @Test
    void buscarPorNomeIgnoraMaiusculas() {
        List<Produto> r = model.buscar("fone", null, null);
        assertEquals(1, r.size());
        assertEquals("Fone Bluetooth", r.get(0).getNome());
    }

    @Test
    void buscarPorFaixaDeValor() {
        List<Produto> r = model.buscar(null, new BigDecimal("100"), new BigDecimal("500"));
        assertEquals(1, r.size());
        assertEquals("Fone Bluetooth", r.get(0).getNome());
    }

    private static ProdutoCreateRequest req(String nome, String valor) {
        return new ProdutoCreateRequest(nome, "descricao", "http://x/y.png", new BigDecimal(valor), 1L);
    }
}
