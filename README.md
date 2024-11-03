# Challenge02 API 🏫

Bem-vindo ao projeto Challenge02! Este projeto fornece uma API RESTful para gerenciar dados de professores, estudantes, coordenadores e registros de cursos, com funcionalidades como autenticação, gerenciamento de disciplinas e validação de dados.

## Endpoints Principais 🌐

### Professores 📚

- **GET /api/teacher**: Lista todos os professores disponíveis.
- **GET /api/teacher/{id}**: Retorna um professor específico pelo ID.
- **POST /api/teacher**: Cria um novo professor.
- **DELETE /api/teacher/{id}**: Deleta um professor pelo ID.
- **POST /api/teacher/login**: Realiza login do professor e retorna um token JWT.

### Estudantes 🎓

- **POST /api/student**: Salva um novo estudante.
- **POST /api/student/two**: Método de teste para salvar estudantes.
- **GET /api/student/{id}**: Obtém dados de um estudante específico por ID.
- **GET /api/student**: Lista todos os estudantes disponíveis.
- **POST /api/student/login**: Realiza login do estudante e retorna um token JWT.

### Coordenadores 👨‍🏫

- **POST /api/coordinator**: Cria um novo coordenador, com tratamento para violação de unicidade de nome.
- **GET /api/coordinator**: Lista todos os coordenadores.
- **GET /api/coordinator/id/{id}**: Retorna um coordenador específico pelo ID, lançando exceção se não encontrado.
- **PUT /api/coordinator/{id}**: Atualiza um coordenador específico, garantindo integridade dos dados.
- **DELETE /api/coordinator/id/{id}**: Remove um coordenador específico.

### Registros de Cursos 📝

- **POST /api/registration**: Registra um estudante em um curso específico. (Exemplo fictício, ajuste conforme implementação)
- **GET /api/registration/student/{studentId}**: Lista todos os registros de cursos de um estudante.
- **DELETE /api/registration/{registrationId}**: Remove o registro de um estudante para um curso específico.

### Segurança 🔒

- **Filtros de JWT**: A API utiliza JWT para autenticação. Tokens são gerados e verificados utilizando a `JwtTokenService`.
- **Configuração de Segurança**: O controle de acesso é gerenciado pela `SecurityConfiguration`, definindo quais endpoints necessitam de autenticação e quais são públicos.

## Configuração do Banco de Dados 🐳

Este projeto utiliza um banco de dados executado em um contêiner Docker. A configuração básica do banco de dados pode ser encontrada e ajustada no arquivo `application.properties`. Para inicializar o banco de dados, certifique-se que o Docker esteja em execução na sua máquina e que suas credenciais e parâmetros de conexão no arquivo de configuração estejam corretos.

## Estrutura do Código 🗂️

### Controllers 📂

- **TeacherController**: Controla as operações relacionadas a professores.
- **StudentController**: Controla as operações relacionadas a estudantes.
- **CoordinatorController**: Gerencia as operações relacionadas aos coordenadores, incluindo criação, atualização, listagem e exclusão.
- **RegistrationServices**: Gerencia o registro de alunos em cursos, realizando operações de criação, listagem e remoção.

### Services ⚙️

- **TeacherService**: Contém a lógica de negócios associada a professores, incluindo criação, autenticação e manuseio de dados de disciplinas.
- **StudentService**: Contém a lógica de negócios associada aos estudantes, incluindo validações, autenticação e interações com serviços externos.
- **CoordinatorService**: Manipula a lógica de negócios para os coordenadores, incluídas as operações CRUD e tratamento de exceções.
- **RegistrationServices**: Provê funcionalidades para registrar estudantes em cursos e manipular esses registros.
- **JwtTokenService**: Serviço para geração e validação de tokens JWT para autenticação.
- **UserDetailsServiceImpl**: Implementa o carregamento de usuários para autenticação, procurando por professores, estudantes e coordenadores.

### Segurança 🔐

- **UserAuthenticationFilter**: Valida se as requisições possuem um token JWT válido para endpoints protegidos.
- **SecurityConfiguration**: Configura a segurança do Spring Security, permitindo ou negando acesso a diferentes endpoints.

## Recursos Internos

### DTOs 📄

- `LoginRequest`: Manipula dados de login para autenticação.
- `RecoveryJwtTokenDTO`: Estrutura de resposta para tokens JWT.
- `TeacherDTO`, `StudentDto`, `CoordinatorDTO`: Estruturas de dados para criar ou atualizar professores, estudantes, e coordenadores.
- `RegistrationDTO`: Estrutura de dados para manipulação de registros de curso.

### Exceções e Validações 🚨

- **EntityIdNotFoundException**: Lançada quando uma entidade não é encontrada com o ID fornecido.
- **NameUniqueViolationException**: Lançada ao tentar criar ou atualizar uma entidade com um nome que já existe.
- Implementa outras validações para campos obrigatórios e desajustes de dados.

## Tecnologias Utilizadas 💻

- **Spring Boot**: Framework principal para desenvolvimento da API.
- **Spring Security**: Segurança e autenticação na aplicação usando JWT.
- **JPA/Hibernate**: Persistência de dados.
- **BCryptPasswordEncoder**: Para codificação de senhas.
- **Lombok**: Simplifica o desenvolvimento de Java com anotações.
- **Docker**: Para gerenciamento do contêiner do banco de dados.


