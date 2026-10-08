# VassCommerce API — IoC/DI (Controllers x Models)

Continuação do exercício anterior (API somente-leitura). Esta etapa adiciona `POST /produto`, `GET /produto`
(com filtros) e separa a camada web da camada de modelo via interface + injeção por construtor.

## Executar
Requisitos: Java 17+ e Maven.

```
mvn spring-boot:run                                   # ProdutoModelMemoria  (profile mem)
mvn spring-boot:run -Dspring-boot.run.profiles=sql    # ProdutoModelSql      (profile sql)
mvn test
```

## Como perceber a troca de implementação
| Passo | profile `mem` | profile `sql` |
|---|---|---|
| `GET /produto/1` (antes de criar algo) | **404** (lista vazia) | **200** (dados do `DataSeeder`) |
| `POST /produto` | 201, `Location: /produto/1` | 201, `Location: /produto/4` (já existem 3) |
| `POST` com `categoriaId` inexistente | 201 (memória não conhece categorias) | **400** (`Categoria nao encontrada`) |
| Reiniciar a aplicação | dados perdidos | H2 em memória também recomeça (use outro datasource para persistir) |

## Endpoints
| Método | URL | Respostas |
|---|---|---|
| POST | `/produto` | 201 + `Location`, 400 (validação / JSON inválido) |
| GET | `/produto/{id}` | 200, 404, 400 (id não numérico) |
| GET | `/produto?nome=&valorMinimo=&valorMaximo=` | 200 |

Exemplo de corpo do POST:
```json
{ "nome": "Mouse Gamer", "descricao": "Mouse 12000 dpi", "fotoUrl": "https://exemplo.com/mouse.png",
  "valorUnitario": 129.90, "categoriaId": 1 }
```

Exemplo de erro 400:
```json
{ "timestamp": "...", "status": 400, "error": "Bad Request", "message": "Dados invalidos na requisicao",
  "path": "/produto", "campos": [ { "campo": "nome", "mensagem": "o nome e obrigatorio" } ] }
```

Coleção Postman: `postman/VassCommerce-IoC-DI.postman_collection.json` (já tem testes de status em cada request).
