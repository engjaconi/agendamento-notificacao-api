
# Agendador de Notificações

Esse projeto tem como escopo o cadastro de notificações para posterior envio. Projeto de referência: https://github.com/angelicaweiler/agendamento_notificacao_api

## Rodando localmente

### Requisitos
- JDK Java 17 ou superior.
- Apache Maven
- Docker Desktop

### Comandos e instruções

Clone o projeto:

```bash
  git clone https://github.com/engjaconi/agendamento-notificacao-api.git
```

Entre no diretório do projeto e execute:

```bash
  mvn clean package
```
```bash
  docker-compose up --build
```
Acesse o Postman e importe a coleção da api presente na pasta **../src/main/resources/collection**.

## Documentação da API

#### Cadastra notificações pendentes

```http
  POST /agendamento
```

| Body   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `emailDestinatario` | `string` | **Obrigatório**. Email do destinatário da notificação |
| `telefoneDestinatario` | `string` | **Obrigatório**. Telefone do destinatário da notificação |
| `mensagem` | `string` | **Obrigatório**. Mensagem da notificação |
| `dataHoraEvento` | `LocalDateTime` | **Obrigatório**. Data hora do evento no formado dd-MM-yyyy HH:mm:ss |

#### Retorna uma notificação por id

```http
  GET /agendamento/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do item que você quer buscar |

#### Cancela uma notificação por id

```http
  DELETE /agendamento/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do item que você quer cancelar |


## Rodando os testes

Para rodar os testes, rode o seguinte comando:

```bash
  mvn test
```