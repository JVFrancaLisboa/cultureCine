# CultureCine 🎥  

**CultureCine** é uma aplicação para gerenciamento de filmes e análises. A aplicação permite realizar operações de CRUD (Criar, Ler, Atualizar e Deletar) tanto para filmes quanto para análises relacionadas a eles.  

## Funcionalidades 🚀  

### Filmes  
- **Adicionar Filme**: Cadastro de novos filmes com informações como título, sinopse, gênero e ano.  
- **Listar Filmes**: Exibição de todos os filmes cadastrados.  
- **Atualizar Filme**: Edição das informações de um filme já cadastrado.  
- **Deletar Filme**: Remoção de um filme específico.  

### Análises  
- **Adicionar Análise**: Cadastro de análises e avaliações relacionadas a um filme.  
- **Listar Análises**: Exibição das análises vinculadas a um filme no formato JSON.  

Exemplo de estrutura de um filme com análises:  
```json
{
    "id": 1,
    "titulo": "O Poderoso Chefão",
    "sinopse": "A história de uma poderosa família mafiosa liderada por Don Vito Corleone.",
    "genero": "Drama/Crime",
    "ano": "1972",
    "analises": [
        {
            "id": 2,
            "analise": "Excelente filme, envolvente do começo ao fim!",
            "nota": "5"
        }
    ]
}
```
## Tecnologias Utilizadas 🛠️
### Front-end
- **Bootstrap**: Para estilização e layout responsivo.
- **jQuery**: Para manipulação do DOM e chamadas AJAX.
- **AJAX**: Para comunicação assíncrona com a API.
### Back-end 
- **Spring Boot**: Para construção da API RESTful.
- **MySQL**: Banco de dados relacional para armazenar as informações.

## Configuração e Execução 🖥️

### Pré-requisitos
Certifique-se de ter instalado:
- **JDK 21** ou superior  
- **Maven**  
- **MySQL**  
- Um servidor local para frontend (por exemplo, extensão **Live Server** do VSCode).

---

### Passos para rodar o backend:

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/JVFrancaLisboa/cultureCine.git
   cd culturecine

### Configure o banco de dados no arquivo application.properties:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/culturacine_db
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```
### Passos para rodar o frontend:
1. Abra a pasta frontend no seu editor.
2. Use uma extensão ou ferramenta como o Live Server para rodar o HTML localmente.
