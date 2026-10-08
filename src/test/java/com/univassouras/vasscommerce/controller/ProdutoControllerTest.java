package com.univassouras.vasscommerce.controller;

import com.univassouras.vasscommerce.entity.Produto;
import com.univassouras.vasscommerce.model.ProdutoModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * O controller e testado contra um MOCK da interface ProdutoModel:
 * prova que ele nao conhece nenhuma implementacao concreta.
 */
@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {

    private final MockMvc mvc;

    @MockBean
    private ProdutoModel model;

    ProdutoControllerTest(@Autowired MockMvc mvc) {   // injecao por construtor tambem nos testes
        this.mvc = mvc;
    }

    @Test
    void postValidoRetorna201ComLocation() throws Exception {
        when(model.criar(any())).thenReturn(produto(7L, "Fone", "199.90"));

        mvc.perform(post("/produto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"nome":"Fone","descricao":"d","fotoUrl":"u","valorUnitario":199.90,"categoriaId":1}
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/produto/7"))
                .andExpect(jsonPath("$.id").value(7))
                .andExpect(jsonPath("$.nome").value("Fone"));
    }

    @Test
    void postInvalidoRetorna400PadronizadoENaoChamaOModel() throws Exception {
        mvc.perform(post("/produto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"nome":"","valorUnitario":-5}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Dados invalidos na requisicao"))
                .andExpect(jsonPath("$.path").value("/produto"))
                .andExpect(jsonPath("$.campos").isArray());

        verifyNoInteractions(model);
    }

    @Test
    void postComJsonMalformadoRetorna400() throws Exception {
        mvc.perform(post("/produto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{nome:"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    void getExistenteRetorna200() throws Exception {
        when(model.obterPorId(1L)).thenReturn(Optional.of(produto(1L, "Fone", "199.90")));

        mvc.perform(get("/produto/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.categoriaId").value(1));
    }

    @Test
    void getInexistenteRetorna404() throws Exception {
        when(model.obterPorId(99L)).thenReturn(Optional.empty());

        mvc.perform(get("/produto/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getIdNaoNumericoRetorna400() throws Exception {
        mvc.perform(get("/produto/abc"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    void listarRepassaFiltrosAoModel() throws Exception {
        when(model.buscar(eq("fone"), eq(new BigDecimal("10")), eq(new BigDecimal("500"))))
                .thenReturn(List.of(produto(1L, "Fone", "199.90")));

        mvc.perform(get("/produto")
                        .param("nome", "fone")
                        .param("valorMinimo", "10")
                        .param("valorMaximo", "500"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Fone"));
    }

    private static Produto produto(Long id, String nome, String valor) {
        com.univassouras.vasscommerce.entity.Categoria c = new com.univassouras.vasscommerce.entity.Categoria();
        c.setId(1L);

        Produto p = new Produto();
        p.setId(id);
        p.setNome(nome);
        p.setDescricao("d");
        p.setFotoUrl("u");
        p.setValorUnitario(new BigDecimal(valor));
        p.setDataCadastro(LocalDateTime.now());
        p.setDataUltimaAtualizacao(LocalDateTime.now());
        p.setCategoria(c);
        return p;
    }
}
