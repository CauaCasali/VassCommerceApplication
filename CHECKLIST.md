# CHECKLIST.md — Separação Controller x Model (IoC/DI)

**Estratégia de seleção usada:** `@Profile` (`mem` / `sql`), definida em `application.properties`.

- [x] Controladores **não** fazem `new` de implementações; dependem de **interfaces**.
  `ProdutoController` recebe `ProdutoModel` no construtor; nenhuma classe concreta é importada.
- [x] Há ao menos **duas** implementações `@Service` de `ProdutoModel`:
  `ProdutoModelMemoria` (`@Service("produtoModelMemoria")`) e `ProdutoModelSql` (`@Service("produtoModelSql")`).
- [x] A seleção é feita por `@Primary` **ou** `@Qualifier` **ou** `@Profile`.
  Usei `@Profile("mem")` e `@Profile("sql")`, com `spring.profiles.active=mem` em `application.properties`.
  Prova automatizada: `SelecaoDeImplementacaoTest`.
- [x] Injeção é **por construtor**; nenhum `@Autowired` em campo.
  (`grep -rn "@Autowired" src` não retorna nada.)
- [x] `ProdutoController` continua retornando **201/200/404** corretos
  (`POST` → 201 + `Location: /produto/{id}`; `GET /{id}` → 200 ou 404; `GET` lista → 200).
- [x] Validação em `ProdutoCreateRequest` continua ativa (`@Valid` no `@RequestBody`;
  `@NotBlank`, `@NotNull`, `@DecimalMin`, `@Digits`, `@Size`, `@Positive` no record).
- [x] JSON de erro 400 continua **padronizado** via `@RestControllerAdvice`
  (`GlobalExceptionHandler` → `ErroResponse`: `timestamp`, `status`, `error`, `message`, `path`, `campos`).

## Onde está cada coisa

| Item | Arquivo |
|---|---|
| Contrato | `model/ProdutoModel.java` |
| Implementação em memória | `model/ProdutoModelMemoria.java` (`@Profile("mem")`) |
| Implementação relacional (JPA/H2) | `model/ProdutoModelSql.java` (`@Profile("sql")`) |
| Controller desacoplado | `controller/ProdutoController.java` |
| DTOs | `dto/ProdutoCreateRequest.java`, `dto/ProdutoResponse.java`, `dto/ErroResponse.java` |
| Mapper | `mapper/ProdutoMapper.java` |
| Entidade | `entity/Produto.java` |
| Erros padronizados | `exception/GlobalExceptionHandler.java` |
| Testes | `src/test/java/...` (unitário, controller com mock da interface, seleção por profile) |
| Postman | `postman/VassCommerce-IoC-DI.postman_collection.json` |

## Como trocar a implementação

```
mvn spring-boot:run                                          # profile mem (padrão do properties)
mvn spring-boot:run -Dspring-boot.run.profiles=sql           # profile sql
```
ou edite `spring.profiles.active=` em `src/main/resources/application.properties`.
