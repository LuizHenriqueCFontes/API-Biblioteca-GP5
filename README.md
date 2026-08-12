# 📚 Sistema de Gerenciamento de Biblioteca (Back-End)

## 📝 Descrição do Projeto
Este projeto consiste em uma API RESTful desenvolvida para o gerenciamento de uma biblioteca digital e física. O sistema conta com controle de acessos customizado, segurança avançada de endpoints, persistência relacional de dados e um módulo inteligente de enriquecimento de acervo através do consumo de dados de uma API externa de literatura.

> ⚠️ **Nota:** Este projeto está atualmente **em desenvolvimento**.

A aplicação foi desenhada seguindo as melhores práticas de desenvolvimento de software, com total separação de responsabilidades, tráfego de dados isolado via DTOs e resiliência por meio de tratamento global de exceções.

---

## 🚀 Tecnologias e Ferramentas

- **Linguagem:** Java 21
- **Framework Principal:** Spring Boot 3.x
- **Módulos do Spring:**
  - Spring Boot Starter Web (Construção da API REST)
  - Spring Security (Autenticação e Autorização)
  - Spring Data JPA (Camada de persistência de dados)
- **Banco de Dados:** MySQL
- **Segurança:** JSON Web Tokens (JWT) & Hash de senhas com BCrypt
- **Arquitetura de Comunicação:** RestClient (Spring Boot 3) para consumo de API de terceiros
- **Ferramentas:** Git, GitHub, Maven

---

## 🔒 Segurança & Controle de Acesso (RBAC)

O sistema adota o modelo **Role-Based Access Control (RBAC)** integrado ao ecossistema do **Spring Security** em uma arquitetura estritamente *Stateless*. Os tokens JWT realizam a identificação dos usuários e barram acessos não autorizados nas rotas da aplicação de forma centralizada.

### Perfis de Acesso Disponíveis:
- `ADMIN`: Controle total do acervo de livros e gerenciamento de permissões e listagem de usuários.
- `ALUNO` / `USER`: Perfis de clientes do sistema com permissão para gerenciar a própria conta.

### Matriz de Permissões das Rotas

| Contexto | Método | Endpoint | Permissão | Descrição |
| :--- | :--- | :--- | :--- | :--- |
| **Autenticação** | `POST` | `/api/auth/login` | Público | Autenticação do usuário e geração do token JWT. |
| | `POST` | `/api/auth/register` | Público | Cadastro de novos usuários no sistema. |
| | `POST` | `/api/auth/register/validate` | Público | Validação e confirmação do registro do usuário. |
| **Perfil** | `PATCH` | `/api/users/me` | `USER`, `ALUNO`, `ADMIN` | Atualização cadastral do próprio usuário logado. |
| | `PATCH` | `/api/users/me/password` | `USER`, `ALUNO`, `ADMIN` | Alteração de senha pessoal (criptografada em BCrypt). |
| **Usuários** | `GET` | `/api/users` | `ADMIN` | Listagem administrativa de todos os usuários cadastrados. |
| | `PATCH` | `/api/users/*/role` | `ADMIN` | Alteração do nível de permissão (Role) de um usuário. |
| **Catálogo** | `GET` | `/api/admin/books` | `ADMIN` | Listagem do acervo de livros. |
| | `POST` | `/api/admin/books/*` | `ADMIN` | Cadastro/Importação de novas obras. |
| | `PATCH` | `/api/admin/books/*` | `ADMIN` | Edição de informações de um livro específico. |
| | `DELETE` | `/api/admin/books/*` | `ADMIN` | Remoção de um livro do acervo. |

---

## 🌐 Integração com APIs Externas (Enriquecimento de Dados)

Para automatizar e enriquecer a base de dados do acervo de livros sem a necessidade de digitação manual exaustiva, o sistema consome dados da API pública **Gutendex** (um grande catálogo digital de obras do Projeto Gutenberg).

Para a comunicação HTTP externa, foi utilizado o moderno **`RestClient`** do Spring Boot 3.x, implementando uma arquitetura de cliente fluente (`GutendexClient`) encapsulada com tratamento dinâmico de parâmetros de consulta:
- **`searchBooks(String title)`**: Realiza pesquisas textuais assíncronas utilizando sanitização de parâmetros (`?search=`), mapeando o payload de resposta em coleções dinâmicas via `GutendexSearchResponseDTO`.
- **`searchBookById(Integer id)`**: Busca direta por identificador único para importação exata da obra literária.

---

## 🛡️ Resiliência & Tratamento Global de Erros

A API conta com uma camada interceptadora global de exceções gerenciada pela anotação `@RestControllerAdvice`. Essa arquitetura assegura que falhas em tempo de execução ou violações de regras de negócio nunca exponham o rastreamento interno do servidor (*stack trace*) ao cliente externo.

Sempre que uma exceção é disparada, ela é tratada centralizadamente na classe `GlobalException` e retorna um payload estruturado padrão (`ErrorResponse`) contendo o carimbo de data/hora (`timestamp`), o código de status HTTP correspondente e mensagens claras sobre a ocorrência.

### Exceções customizadas tratadas:
- **Gestão de Usuários e Autenticação:** `UserNotFoundException`, `UsernameNotFoundException`, `BadCredentialsException`, `EmailAlreadyExistsException`, `InvalidPasswordException`, `PasswordMatches`, `InvalidRoleException`.
- **Criptografia e Sessão:** `TokenCreationException`, `TokenValidationException`.
- **Gestão do Acervo:** `BookNotFoundException`.

---

## 🗂️ Arquitetura de Dados & Validações (Camada DTO)

Para isolar as entidades do banco de dados das requisições externas e garantir a segurança do tráfego, a aplicação utiliza intensamente o padrão **DTO (Data Transfer Objects)** implementado através de **Java Records** (recurso moderno para imutabilidade de dados).

Toda a entrada de dados passa por uma camada rigorosa de validação com **Bean Validation (Jakarta Validation)**, impedindo que dados inconsistentes ou maliciosos cheguem aos Services.

### Principais Estruturas de Dados Controladas:

1. **Fluxo de Autenticação (`AuthResponseDTO` & `LoginDTO`)**
   - **Login Seguro:** O payload de entrada (`LoginDTO`) valida se o e-mail possui formato sintático válido (`@Email`) e se nenhum campo foi enviado em branco (`@NotBlank`).
   - **Padrão de Resposta:** O retorno (`AuthResponseDTO`) implementa sobrecarga de construtor para automatizar a atribuição do tipo de credencial no formato de mercado padrão `Bearer token`.

2. **Gerenciamento do Acervo (`EditBookRequestDTO`)**
   - Estrutura flexível contendo metadados completos para atualização parcial ou total das obras literárias (título, autores, fontes, status ativo/inativo).
   - Validação numérica rígida através de restrições `@PositiveOrZero` nas propriedades de `totalQuantity` e `availableQuantity`, blindando a regra de negócio do estoque físico contra valores negativos ou nulos.
  
---

## 🌍 Infraestrutura & Hospedagem

### Banco de Dados
O banco de dados **MySQL** está hospedado na **Aiven** (plataforma de dados na nuvem). A Aiven oferece alta disponibilidade, backups automáticos e gerenciamento total de infraestrutura, garantindo que os dados da biblioteca estejam seguros e acessíveis.

### Servidor da Aplicação
A aplicação está hospedada na plataforma **Render**, que fornece:
- Deploy contínuo direto do repositório GitHub
- Escalabilidade automática
- SSL/HTTPS incluído
- Ambiente gerenciado sem preocupação com infraestrutura

### Armazenamento de Arquivos
Para **imagens de capas** e **arquivos EPUB**, o sistema utiliza armazenamento local no próprio disco do servidor Render. Esta abordagem foi adotada como solução temporária devido às limitações de custos para contratar um serviço de object storage externo (como AWS S3 ou Azure Blob Storage). 

> **📌 Nota Futura:** A arquitetura foi projetada para permitir migração futura para um serviço de cloud storage profissional, mantendo compatibilidade com o código existente.

---

## 💻 Integração com Front-End

O front-end está disponível em: https://github.com/LuizHenriqueCFontes/biblioteca-gp5

### Instruções para Configurar o Front-End

Para consumir esta API a partir da aplicação cliente, siga os passos abaixo:

#### 1. **Variáveis de Ambiente**
Crie um arquivo `.env` na raiz do projeto front-end com as seguintes variáveis:

```bash
VITE_API_URL=https://seu-servidor-render.onrender.com/api
VITE_AUTH_ENDPOINTS=/auth
VITE_BOOKS_ENDPOINTS=/admin/books
VITE_USERS_ENDPOINTS=/users
```

#### 2. **Armazenamento Seguro do JWT**
- O token JWT retornado pela rota `/api/auth/login` deve ser armazenado no `localStorage` ou `sessionStorage` (com análise de risco de segurança)
- Adicionar o token em toda requisição através do header `Authorization: Bearer <token>`

#### 3. **Endpoints Principais para Consumo**

| Contexto | Método | Endpoint | Descrição |
| :--- | :--- | :--- | :--- |
| **Login** | `POST` | `/api/auth/login` | Autenticação e obtenção do token JWT |
| **Registro** | `POST` | `/api/auth/register` | Cadastro de novo usuário |
| **Catálogo** | `GET` | `/api/admin/books` | Listagem de livros disponíveis |
| **Detalhes** | `GET` | `/api/admin/books/{id}` | Informações específicas de um livro |
| **Perfil** | `GET` | `/api/users/me` | Dados do usuário autenticado |

#### 4. **Tratamento de Respostas**
A API retorna respostas estruturadas em JSON com a seguinte formato padrão:

**Sucesso (200-201):**
```json
{
  "id": 1,
  "title": "O Cortiço",
  "authors": "Aluísio Azevedo",
  "status": "ACTIVE",
  "createdAt": "2024-01-15T10:30:00Z"
}
```

**Erro (4xx-5xx):**
```json
{
  "timestamp": "2024-01-15T10:30:00Z",
  "status": 404,
  "message": "Livro não encontrado"
}
```

#### 5. **Configuração de CORS**
A API está configurada para aceitar requisições de origens específicas. Certifique-se de que a URL do front-end está cadastrada nas configurações CORS do back-end.
