package com.univassouras.vasscommerce.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * Prova da selecao por @Profile: o mesmo ProdutoModel injetado muda de classe
 * conforme o profile ativo. Cada contexto usa um H2 proprio para nao misturar dados.
 */
class SelecaoDeImplementacaoTest {

    @Nested
    @SpringBootTest(properties = "spring.datasource.url=jdbc:h2:mem:selecao_mem")
    @ActiveProfiles("mem")
    class ProfileMem {

        private final ProdutoModel model;
        private final ApplicationContext ctx;

        ProfileMem(@Autowired ProdutoModel model, @Autowired ApplicationContext ctx) {
            this.model = model;
            this.ctx = ctx;
        }

        @Test
        void injetaImplementacaoEmMemoria() {
            assertInstanceOf(ProdutoModelMemoria.class, model);
            assertEquals(1, ctx.getBeansOfType(ProdutoModel.class).size());
        }
    }

    @Nested
    @SpringBootTest(properties = "spring.datasource.url=jdbc:h2:mem:selecao_sql")
    @ActiveProfiles("sql")
    class ProfileSql {

        private final ProdutoModel model;
        private final ApplicationContext ctx;

        ProfileSql(@Autowired ProdutoModel model, @Autowired ApplicationContext ctx) {
            this.model = model;
            this.ctx = ctx;
        }

        @Test
        void injetaImplementacaoSql() {
            assertInstanceOf(ProdutoModelSql.class, model);
            assertEquals(1, ctx.getBeansOfType(ProdutoModel.class).size());
        }
    }
}
