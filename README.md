# Sistema de Gestão de Ativos de TI

API REST para gerenciamento de hardware, periféricos e licenças de software, vinculando ativos a funcionários.
## API em produção

A API está disponível publicamente em:

- **Swagger UI:** https://gestao-ati-production.up.railway.app/swagger-ui/index.html
- **Base URL:** https://gestao-ati-production.up.railway.app
## Tecnologias

- Java 21
- Spring Boot 4.0.4
- Spring Security + JWT
- Spring Data JPA
- PostgreSQL
- HATEOAS
- Swagger/OpenAPI 3
- Lombok

## Funcionalidades

- Cadastro e gerenciamento de **Funcionários**
- Cadastro e gerenciamento de **Ativos** (hardware/periféricos)
- Cadastro e gerenciamento de **Licenças** de software
- **Autenticação JWT** com níveis de acesso (Admin/Usuário)
- **Logs de Auditoria** — registra quem alterou o quê e quando
- **HATEOAS** — links de navegação nas respostas da API
- **Documentação interativa** via Swagger UI

## Pré-requisitos

- Java 21+
- PostgreSQL 16+
- Maven

## Como rodar localmente

1. Clone o repositório:
```bash
git clone https://github.com/RafaelGomes-dev/gestao-ati.git
```

2. Configure o banco no `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gestao_ati
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

3. Rode o projeto:
```bash
./mvnw spring-boot:run
```

## Documentação

Com o projeto rodando, acesse a documentação interativa:
```
http://localhost:8080/swagger-ui/index.html
```

## Autenticação

1. Registre um usuário:
```
POST /api/auth/registrar
```
```json
{
    "email": "admin@empresa.com",
    "senha": "admin123",
    "role": "ADMIN"
}
```

2. Faça login e copie o token:
```
POST /api/auth/login
```

3. Use o token no header de todas as requisições:
```
Authorization: Bearer {seu_token}
```

## Endpoints principais

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | /api/auth/registrar | Registrar usuário |
| POST | /api/auth/login | Login e geração de token |
| GET | /api/funcionarios | Listar funcionários |
| POST | /api/funcionarios | Cadastrar funcionário |
| GET | /api/ativos | Listar ativos |
| POST | /api/ativos | Cadastrar ativo |
| GET | /api/licencas | Listar licenças |
| POST | /api/licencas | Cadastrar licença |
| GET | /api/auditoria | Ver logs de auditoria (Admin) |

## Autor

Rafael Gomes — [GitHub](https://github.com/RafaelGomes-dev)
