# Spring Auth JWT

Sistema de autenticação e autorização utilizando Spring Boot Security 6 com JWT (JSON Web Tokens) e criptografia de senhas com BCrypt.

## 📋 Descrição

Este projeto implementa um sistema completo de autenticação baseado em JWT, utilizando Spring Security e OAuth2 Resource Server. As senhas são criptografadas usando BCrypt antes de serem armazenadas no banco de dados, garantindo segurança adicional.

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.2.3**
- **Spring Security 6** - Autenticação e autorização
- **Spring Data JPA** - Persistência de dados
- **MySQL** - Banco de dados
- **JWT (JSON Web Tokens)** - Tokens de autenticação
- **BCrypt** - Criptografia de senhas
- **Lombok** - Redução de boilerplate
- **Maven** - Gerenciamento de dependências

## 📁 Estrutura do Projeto

```
├── src/
│   └── main/
│       ├── java/
│       │   └── org/
│       │       └── example/
│       │           └── springauthjwt/
│       │               ├── SpringAuthJwtApplication.java    # Classe principal
│       │               │
│       │               ├── controller/                      # Controladores REST
│       │               │   ├── dto/                         # Data Transfer Objects
│       │               │   │   ├── CreateUserDto.java      # DTO para criação de usuário
│       │               │   │   ├── LoginRequest.java      # DTO para requisição de login
│       │               │   │   └── LoginResponse.java      # DTO para resposta de login
│       │               │   ├── TokenController.java        # Endpoint de autenticação/login
│       │               │   └── UserControler.java          # Endpoint de criação de usuário
│       │               │
│       │               ├── entities/                        # Entidades JPA
│       │               │   └── User.java                   # Entidade Usuário
│       │               │
│       │               ├── repository/                      # Repositórios JPA
│       │               │   └── UserRepository.java         # Repositório de usuários
│       │               │
│       │               └── security/                        # Configurações de segurança
│       │                   └── SecurityConfig.java         # Configuração do Spring Security
│       │
│       └── resources/
│           ├── application.properties                       # Configurações da aplicação
│           ├── app.key                                     # Chave privada RSA para JWT
│           └── app.pub                                     # Chave pública RSA para JWT
│
├── pom.xml                                                  # Dependências Maven
├── mvnw                                                     # Maven Wrapper (Unix)
└── mvnw.cmd                                                 # Maven Wrapper (Windows)
```

## ⚙️ Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- **Java 21** ou superior
- **Maven 3.6+** (ou use o Maven Wrapper incluído)
- **MySQL 8.0+** instalado e rodando

## 🔧 Configuração

### 1. Banco de Dados MySQL

Crie um banco de dados MySQL:

```sql
CREATE DATABASE springjwt;
```

### 2. Configuração da Aplicação

Edite o arquivo `src/main/resources/application.properties` e ajuste as configurações conforme necessário:

```properties
# Configurações do banco de dados
spring.datasource.url=jdbc:mysql://localhost:3306/springjwt
spring.datasource.username=root
spring.datasource.password=1234

# Configurações JWT (chaves RSA)
jwt.public.key=classpath:app.pub
jwt.private.key=classpath:app.key
```

**⚠️ Importante:**

- Altere `spring.datasource.username` e `spring.datasource.password` para suas credenciais do MySQL
- As chaves RSA (`app.key` e `app.pub`) devem estar presentes na pasta `src/main/resources/`

### 3. Chaves RSA para JWT

O projeto utiliza chaves RSA para assinar e validar tokens JWT. Gere suas chaves no site (RSA Key Generator)[https://cryptotools.net/rsagen] e altere o valor dos arquivos `app.key` (chave privada) e `app.pub` (chave pública) que estão presentes em `src/main/resources/`.

## 🚀 Como Executar

### Opção 1: Usando Maven instalado

```bash
mvn spring-boot:run
```

### Opção 2: Executando o JAR

Primeiro, compile o projeto:

```bash
mvn clean package
```

Depois, execute o JAR gerado:

```bash
java -jar target/Spring-Auth-JWT-0.0.1-SNAPSHOT.jar
```

### Opção 3: Executando pela IDE

1. Abra o projeto na sua IDE
2. Localize a classe `SpringAuthJwtApplication.java`
3. Execute como aplicação Java

A aplicação estará disponível em: **http://localhost:8080**

## 📡 Endpoints da API

### 1. Criar Usuário

**POST** `/users`

**Request Body:**

```json
{
  "name": "Herberth",
  "email": "heb@gmail.com",
  "password": "senha123"
}
```

**Response:** `200 OK` (sem corpo)

**Erro:** `422 Unprocessable Entity` - Se o email já existir

---

### 2. Login (Autenticação)

**POST** `/login`

**Request Body:**

```json
{
  "email": "heb@gmail.com",
  "password": "senha123"
}
```

**Response:** `200 OK`

```json
{
  "token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9...",
  "expiresIn": 300
}
```

**Erro:** `401 Unauthorized` - Se as credenciais estiverem incorretas

---

## 🔐 Segurança

- **Criptografia de Senhas:** Todas as senhas são criptografadas usando BCrypt antes de serem armazenadas
- **JWT:** Tokens JWT assinados com RSA para autenticação stateless
- **Spring Security:** Configuração de segurança com OAuth2 Resource Server
- **Sessões Stateless:** A aplicação não mantém sessões no servidor
- **Validade do Token:** Tokens expiram em 300 segundos (5 minutos)

## 📝 Usuário Padrão

Ao iniciar a aplicação, um usuário padrão é criado automaticamente (se não existir):

- **Nome:** heb
- **Email:** heb@gmail.com
- **Senha:** 1234

⚠️ **Atenção:** Este é apenas para fins de teste. Em produção, remova ou modifique este comportamento.

## 🧪 Testando a API

### Exemplo com cURL

**1. Criar um novo usuário:**

```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Maria Santos",
    "email": "maria@example.com",
    "password": "senha456"
  }'
```

**2. Fazer login:**

```bash
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "maria@example.com",
    "password": "senha456"
  }'
```

**3. Acessar endpoint protegido (usando o token retornado):**

```bash
curl -X GET http://localhost:8080/seu-endpoint-protegido \
  -H "Authorization: Bearer SEU_TOKEN_AQUI"
```
