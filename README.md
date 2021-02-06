# 1. Carros API Kotlin

Aplicação desenvolvida para estudo da linguagem Kotlin integrada ao Spring Boot. Esse repositório é uma implementação
alternativa da
[API desenvolvida em Java ](https://github.com/DA0HN/carros-api).

Forks, Issues e PR's são bem-vindos :)
## 1.1 TODOs

- [ ] Adicionar endpoints para manutenção de Usuários
- [ ] Adicionar banco de dados H2
- [ ] Adicionar testes funcionais e de integração
- [ ] Adicionar perfis do spring
- [ ] Adicionar deploy no heroku
- [ ] Adicionar documentação com swagger
- [ ] Adicionar Github Actions

## 1.2 Endpoints

| URI                   | Método | Permissão | Detalhes                                       |
|-----------------------|--------|-----------|------------------------------------------------|
| /userInfo             | GET    | USER      | retorna os dados do usuário logado             |
| /api/v1/login         | GET    | PUBLIC    | realiza login e devolve informações do usuário |
| /api/v1/carros        | GET    | USER      | busca todos os carros                          |
| /api/v1/carros/{id}   | GET    | USER      | busca um carro pelo id                         |
| /api/v1/carros/{tipo} | GET    | USER      | busca um carro pelo tipo                       |
| /api/v1/carros        | POST   | ADMIN     | cria um carro                                  |
| /api/v1/carros/{id}   | PUT    | ADMIN     | atualiza um carro                              |
| /api/v1/carros/{id}   | DELETE | ADMIN     | apaga um carro                                 |

## 1.3 Retornos

####  1.3.1 Carro
```json
{
    "id": 2,
    "nome": "Chevrolet Corvette",
    "tipo": "classicos"
}
```

#### 1.3.2 Login
```json
{
    "login": "user",
    "nome": "User",
    "email": "user@gmail.com",
    "token": "..."
}
```

