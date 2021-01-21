# Projeto de API REST  

## O projeto consiste em reservar ou cancelar passagens em um determinado onibus.

## Funcionalidades

- CRUD de cliente
- CRUD de marcas
- CRUD de patrimonio
- Login com CPF e Senha

## Tecnologias utilizadas e seu papel
● Java 11 - Linguagem utilizada, escolhi por já estar trabalhando com essa versão.
● Maven - É responsável por gerenciar dependências, controlar versão de artefatos, gerar
relatórios de produtividade, garantir execução de testes, manter nível de qualidade do código
dentre outras, escolhi o maven para gerenciar as dependências do projeto pela facilidade e
praticidade.
● Spring boot - O Spring Boot é uma ferramenta que visa facilitar o processo de configuração e
publicação de aplicações que utilizem o ecossistema Spring, escolhi o spring Boot pela
facilidade e produtividade que ele me fornece.
● Spring Boot Starter - E o coração de uma aplicação Spring. Sua função principal é combinar
as várias dependências de um projeto Spring Boot em uma única dependência, retirando-se a
necessidade de configuração de múltiplas dependências no Maven, escolhi para ter o projeto
rodando o mais rápido possível e sem complicação.
● Spring-data-JPA - Tem por objetivo facilitar nosso trabalho com persistência de dados de uma
forma geral, e com ele podemos usar hibernate, eclipse-link entre outros.
● Spring-devtools - A Spring Developer Tools usa cache para melhorar a performance.
● JWT - JWT (JSON Web Token) realiza autenticação entre duas partes por meio de um token
assinado que autêntica uma requisição web. Esse token é um código em Base64 que
armazena objetos JSON com os dados que permitem a autenticação da requisição o escolhi,
pois, queria utilizar a autenticação por token passando o token pelo header.
● Spring security - Spring Security tem o foco em tornar a parte de autenticação e autorização
uma coisa simples de fazer. Através das permissões que atribuímos aos usuários
autenticados, podemos proteger as requisições web, escolhi pela facilidade que me forneceu
em trabalhar com roles, podendo liberar endpoints específicos para determinados usuários.
● POSTGRESQL - Banco de dados utilizado na aplicação, escolhi pela escalabilidade e flexibilidade


## Executar a aplicação

Primeiro é necessário iniciar seu banco de dados POSTGRESQL. É necessário criar as tabelas do banco. A API faz isso para você se na primeira execução você utilizar a seguinte propriedade spring.jpa.hibernate.ddl-auto=update a base é denominada 'patrimonio' e o banco por padrão é criado desde que o POSTGRES tenha sido inicializado:
O Script do banco de dados encontrasse no resources
### application.properties

```json
spring.jpa.properties.jdbc.lob.non_contextual_creation=true
spring.datasource.url=jdbc:postgresql://localhost:5432/patrimonio
spring.datasource.username=postgres  <-- Usuario do banco de dados
spring.datasource.password=admin   <-- Senha do banco de dados
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=org.postgresql.Driver
```
## Funcionalidade do projeto
Para primeira utilização e necessario criar um cadastro de novo usuario, no endpoint http://localhost:8080/api/usuario/adicionar_cliente esse endpoint não tem autenticação.
Apos a criação do usuario e necessario fazer o login com email e senha para gerar o token que sera utilizado para autenticação, para isso utilize o endpoint http://localhost:8080/auth/login
## OBSERVAÇÂO: E necessario inserir o token ao header para as demais requisições.
Com o token adicionado ao header podemos utilizar o resto dos endpoints.

Pacotes do projeto
● Config (Onde estão as configurações globais do nosso projeto, no caso configurações de
segurança dos endpoints, cors e roles)
● Controller (Tem como objetivo direcionar o fluxo da aplicação mapeando e direcionado as
ações recebida pela camada da apresentação para os respectivos serviços da aplicação)
● Dto (As classes de transferência de objeto)
● Exception (Tratamento adequado das exceções)
● Model (As entidades)
● Repository (Usa a camada de persistência para gravar e recuperar os dados necessários para
persistir e recuperar os objetos no bando de dados)
● Security (As classes de segurança da aplicação)
● Service (Onde trabalhamos as regras de negócio, manipulando as informações vindas via
request e retornam a response para o controller devolver para a aplicação)

## Erros
400:  Erro generico para itens ja cadastrado
403: Acesso Negado
404: NotFound
422: Erro validação

## Endpoints

## Autorização

### Post
### http://localhost:8080/auth/login
### Gera o token para adicionar ao header
### Request
```
{
    "email": "felipe@hotmail.com",
    "senha": "123456"
}
```
### Response
```
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmZWxpcGVAaG90bWFpbC5jb20iLCJyb2xlcyI6WyJDTElFTlRFIl0sImlhdCI6MTYxMTE2MDIwNSwiZXhwIjoxNjExMTYzODA1fQ.hZC1T4Il0DMUotbQSUSaCNFWamYVaHBycy5tUuab-Uk"
}
```

## Usuario

### Get
### http://localhost:8080/api/usuario/id
### Busca um usuario por id
```
{
    "clienteId": 2,
    "nome": "felipe soares",
    "email": "felipe@hotmail.com",
    "perfies": [
        "CLIENTE"
    ]
}
```

### Get
### http://localhost:8080/api/usuario
### Busca todos os usuario
### Response
```
{
    "clienteId": 2,
    "nome": "felipe soares",
    "email": "felipe@hotmail.com",
    "perfies": [
        "CLIENTE"
    ]
}
```
### Get
### http://localhost:8080/api/usuario/page
### Busca todos os usuario
### Response
```
{
  "content": [
        {
            "clienteId": 2,
            "nome": "felipe soares",
            "email": "felipe@hotmail.com",
            "perfies": [
                "CLIENTE"
            ]
        }
    ],
    "pageable": {
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 24,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 2,
    "size": 24,
    "number": 0,
    "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
    },
    "first": true,
    "numberOfElements": 2,
    "empty": false
}
```

### Delete
### http://localhost:8080/api/usuario/id
### Deleta um usuario por id
### Response no content

### Post
### http://localhost:8080/api/usuario/adicionar_cliente
### Adiciona um usuario do tipo cliente
### Request
```
{
    "nome":"felipe soares",
    "senha":"171191",
    "email":"felispe@hotmail.com"
}
```
### Response
```
{
    "clienteId": 16,
    "nome": "felipe soares",
    "email": "felispe@hotmail.com",
    "perfies": [
        "CLIENTE"
    ]
}
```

### Put
### http://localhost:8080/api/usuario/id
### Atualiza um cliente por id
### Request
```
{
    "nome":"felipe soares",
    "senha":"171191",
    "email":"felispe@hotmail.com"
}
```
### Response
```
{
    "clienteId": 16,
    "nome": "felipe soares",
    "email": "felispe@hotmail.com",
    "perfies": [
        "CLIENTE"
    ]
}
```

## Marca

### Get
### http://localhost:8080/api/marca/{id}
### Busca uma marca por id
```
    {
        "marcaId": 1,
        "nome": "adidas"
    }
```

### Get
### http://localhost:8080/api/marca
### Busca todas as marcas
### Response
```
[
    {
        "marcaId": 1,
        "nome": "adidas"
    }
]
```
### Get
### http://localhost:8080/api/usuario/page
### Busca todas as marcas paginada
### Response
```
{
    "content": [
        {
            "marcaId": 1,
            "nome": "adidas"
        }
    ],
    "pageable": {
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 24,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 24,
    "number": 0,
    "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
    },
    "first": true,
    "numberOfElements": 1,
    "empty": false
}
```

### Delete
### http://localhost:8080/api/marca/id
### Deleta uma marca por id
### Response no content

### Post
### http://localhost:8080/api/marca
### Adiciona uma marca
### Request
```
{
    "nome":"adidas"
}
```
### Response
```
{
    "marcaId": "1",
    "nome": "adidas"
}
```

### Put
### http://localhost:8080/api/marca/id
### Atualiza uma marca por id
### Request
```
{
    "nome":"nike"
}
```
### Response
```
{
    "marcaId":1,
    "nome": "nike"
}
```

## Patrimonio

### Get
### http://localhost:8080/api/patrimonio/17
### Busca um patrimonio por id
```
      {
            "numeroTombo": 17,
            "nome": "novo ",
            "descricao": "descricao",
            "marcaId": 5
        }
```

### Get
### http://localhost:8080/api/patrimonio
### Busca todos os patrimonios
### Response
```
{
         {
            "numeroTombo": 17,
            "nome": "novo ",
            "descricao": "descricao",
            "marcaId": 5
        }
}
```
### Get
### http://localhost:8080/api/patrimonio/page
### Busca todos os patrimonios
### Response
```
{
    "content": [
        {
            "numeroTombo": 17,
            "nome": "novo ",
            "descricao": "descricao",
            "marcaId": 5
        }
    ],
    "pageable": {
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 24,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 24,
    "number": 0,
    "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
    },
    "first": true,
    "numberOfElements": 1,
    "empty": false
}
```

### Delete
### http://localhost:8080/api/patrimonio/id
### Deleta um patrimonio por id
### Response no content

### Post
### http://localhost:8080/api/patrimonio
### Adiciona um patrimonio
### Request
```
{
    "nome":"novo ",
    "descricao": "descricao",
    "marcaId": "5"
}
```
### Response
```
{
    "numeroTombo": 17,
    "nome": "novo ",
    "descricao": "descricao",
    "marcaId": 5
}
```

### Put
### http://localhost:8080/api/patrimonio/id
### Atualiza um patrimonio por id
### Request
```
{
    "nome":"teste ",
    "descricao": "testando",
    "marcaId": "6"
}
```
### Response
```
{
    "numeroTombo": 12,
    "nome": "teste ",
    "descricao": "testando",
    "marcaId": 6
}
```




