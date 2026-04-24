# Pedidos Checkpoint 2 API

API REST desenvolvida com Spring Boot para gerenciamento de pedidos. O projeto permite criar, listar, buscar, atualizar e excluir pedidos, utilizando banco de dados H2 em memoria.

## Integrantes

| Nome | RM |
| --- | --- |
| Abner | 558468 |
| Heloisa | 554535 |
| Fernando | 555201 |
| Thomas | 554812 |
| Eduardo | 556803 |


## Tecnologias

- Java 21
- Spring Boot 4.0.5
- Spring Web MVC
- Spring Data JPA
- Bean Validation
- H2 Database
- Lombok
- Maven

## Requisitos

- JDK 21 ou superior
- Maven ou Maven Wrapper incluso no projeto

## Como executar

Clone o projeto e acesse a pasta:

```bash
git clone <url-do-repositorio>
cd checkpoint2
```

Execute a aplicacao:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

A API ficara disponivel em:

```text
http://localhost:8085
```

## Banco de dados

A aplicacao usa H2 em memoria. Os dados sao perdidos ao reiniciar a aplicacao.

Console H2:

```text
http://localhost:8085/h2-console
```

Configuracoes de acesso:

| Campo | Valor |
| --- | --- |
| JDBC URL | `jdbc:h2:mem:checkpoint2db` |
| User Name | `sa` |
| Password | deixe em branco |

## Modelo de Pedido

| Campo | Tipo | Obrigatorio | Descricao |
| --- | --- | --- | --- |
| `id` | `Long` | Nao | Identificador gerado automaticamente |
| `clienteNome` | `String` | Sim | Nome do cliente |
| `dataPedido` | `LocalDate` | Nao | Data do pedido. Se nao enviada, e preenchida automaticamente com a data atual |
| `valorTotal` | `double` | Sim | Valor total do pedido. Nao pode ser negativo |

Exemplo de JSON:

```json
{
  "clienteNome": "Maria Silva",
  "valorTotal": 149.9
}
```

## Endpoints

Base URL:

```text
http://localhost:8085
```

### Criar pedido

```http
POST /pedidos
```

Corpo da requisicao:

```json
{
  "clienteNome": "Maria Silva",
  "valorTotal": 149.9
}
```

Resposta `201 Created`:

```json
{
  "id": 1,
  "clienteNome": "Maria Silva",
  "dataPedido": "2026-04-24",
  "valorTotal": 149.9
}
```

### Listar pedidos

```http
GET /pedidos
```

Resposta `200 OK`:

```json
[
  {
    "id": 1,
    "clienteNome": "Maria Silva",
    "dataPedido": "2026-04-24",
    "valorTotal": 149.9
  }
]
```

### Buscar pedido por ID

```http
GET /pedidos/{id}
```

Exemplo:

```http
GET /pedidos/1
```

Resposta `200 OK`:

```json
{
  "id": 1,
  "clienteNome": "Maria Silva",
  "dataPedido": "2026-04-24",
  "valorTotal": 149.9
}
```

Caso o pedido nao exista, a API retorna `404 Not Found` com a mensagem:

```text
Pedido não encontrado.
```

### Atualizar pedido

```http
PUT /pedidos/{id}
```

Exemplo:

```http
PUT /pedidos/1
```

Corpo da requisicao:

```json
{
  "clienteNome": "Maria Oliveira",
  "valorTotal": 199.9
}
```

Resposta `200 OK`:

```json
{
  "id": 1,
  "clienteNome": "Maria Oliveira",
  "dataPedido": "2026-04-24",
  "valorTotal": 199.9
}
```

Observacao: a atualizacao altera `clienteNome` e `valorTotal`. A `dataPedido` permanece a mesma do cadastro original.

### Excluir pedido

```http
DELETE /pedidos/{id}
```

Exemplo:

```http
DELETE /pedidos/1
```

Resposta:

```http
204 No Content
```

Caso o pedido nao exista, a API retorna `404 Not Found` com a mensagem:

```text
Pedido não encontrado.
```

## Validacoes

A API aplica as seguintes validacoes:

| Campo | Regra | Mensagem |
| --- | --- | --- |
| `clienteNome` | Nao pode estar vazio | `O nome do cliente é obrigatório.` |
| `valorTotal` | Deve ser maior ou igual a zero | `O valor total não pode ser negativo.` |

## Exemplos com cURL

Criar pedido:

```bash
curl -X POST http://localhost:8085/pedidos \
  -H "Content-Type: application/json" \
  -d '{"clienteNome":"Maria Silva","valorTotal":149.9}'
```

Listar pedidos:

```bash
curl http://localhost:8085/pedidos
```

Buscar pedido:

```bash
curl http://localhost:8085/pedidos/1
```

Atualizar pedido:

```bash
curl -X PUT http://localhost:8085/pedidos/1 \
  -H "Content-Type: application/json" \
  -d '{"clienteNome":"Maria Oliveira","valorTotal":199.9}'
```

Excluir pedido:

```bash
curl -X DELETE http://localhost:8085/pedidos/1
```

## Executar testes

```bash
./mvnw test
```

No Windows:

```bash
mvnw.cmd test
```

## Estrutura do projeto

```text
src/main/java/br/com/fiap3esr/checkpoint2
|-- Checkpoint2Application.java
|-- Controller
|   `-- PedidoController.java
|-- model
|   `-- Pedido.java
|-- repository
|   `-- PedidoRepository.java
`-- service
    `-- PedidoService.java
```
