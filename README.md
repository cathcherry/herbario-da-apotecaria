# Herbário da Apotecária

## Sobre o projeto

O **Herbário da Apotecária** é uma aplicação web para cadastrar e consultar plantas. A ideia surgiu do tema **Planta** proposto para o trabalho de integração e da atmosfera de *Diários de uma Apotecária*: um caderno de observações botânicas com plantas medicinais, aromáticas e tóxicas.

O site permite montar uma coleção própria. É possível cadastrar uma planta, pesquisar pelo nome comum ou científico, abrir o registro completo, editar as informações e excluir um registro. Cada item fica guardado no MySQL, então os dados continuam disponíveis depois que a aplicação é fechada e aberta novamente.

O projeto foi pensado para mostrar, em uma aplicação pequena, as quatro partes avaliadas no trabalho: banco de dados, conexão com o banco, interface e programação.

## O que a aplicação faz

Cada registro pode ter:

- nome comum;
- nome científico;
- categoria;
- nível de toxicidade;
- origem;
- habitat;
- parte utilizada;
- cultivo e cuidados;
- usos ou observações;
- descrição;
- curiosidades;
- imagem opcional da planta.

As funções principais são:

1. **Cadastrar:** o formulário recebe os dados e salva uma nova planta no banco.
2. **Listar:** a página inicial mostra todas as plantas cadastradas em cartões.
3. **Pesquisar:** a busca procura no nome comum e no nome científico, sem diferenciar letras maiúsculas e minúsculas.
4. **Ver detalhes:** cada cartão abre o registro completo da planta.
5. **Editar:** as informações podem ser corrigidas ou complementadas depois do cadastro.
6. **Excluir:** um registro pode ser removido com confirmação.
7. **Cadastrar imagem:** o formulário aceita PNG, JPG, WEBP ou GIF de até 4 MB. A imagem é transformada em texto Base64 e armazenada junto com o registro no MySQL.

Quando uma planta recebe toxicidade **Alta**, o site mostra o destaque **“Veneno absoluto!”**. Esse efeito é apenas uma escolha visual para a apresentação e depende do valor preenchido no cadastro.

## Tecnologias usadas

### Java e Spring Boot

O Java concentra as regras da aplicação. O Spring Boot inicia o servidor web, organiza os componentes e facilita a conexão entre as páginas, o código Java e o banco.

### Spring MVC

O padrão MVC separa as responsabilidades:

- **Model:** representa os dados de uma planta. É a classe `Planta.java`.
- **View:** são as páginas HTML com Thymeleaf.
- **Controller:** recebe as ações do navegador e decide o que deve acontecer. É a classe `PlantaController.java`.

### Thymeleaf

Thymeleaf permite misturar HTML com informações que vêm do Java. Por exemplo, a página inicial percorre a lista de plantas recebida pelo controlador e cria um cartão para cada registro.

### JPA e Hibernate

JPA é a forma usada pelo projeto para transformar objetos Java em registros de banco. O Hibernate, que é a implementação usada pelo Spring Boot, cria e atualiza a tabela conforme a entidade `Planta`.

Por isso, no controlador, uma operação como:

```java
repository.save(planta);
```

salva o objeto no MySQL sem precisar escrever manualmente um `INSERT` para cada cadastro.

### MySQL

O MySQL guarda os dados de forma permanente. O banco usado pelo projeto se chama `herbario_apotecaria` e a tabela principal se chama `plantas`.

### HTML e CSS

O HTML organiza o conteúdo das páginas. O CSS define a identidade visual do herbário: cores escuras de jardim, papel claro, tipografia editorial, ilustrações e adaptação para telas menores.

As imagens da Maomao usadas como pequenos detalhes foram incluídas como PNG com fundo transparente. O GIF do jardim aparece na abertura do catálogo.

## Como o sistema funciona

O fluxo de um cadastro é:

```text
Pessoa preenche o formulário
            ↓
Thymeleaf envia um POST para /plantas
            ↓
PlantaController recebe e valida os dados
            ↓
PlantaRepository salva o objeto
            ↓
Hibernate converte o objeto em SQL
            ↓
MySQL guarda o registro
            ↓
O navegador abre a página de detalhes
```

O fluxo da pesquisa é parecido. A pessoa digita um termo na página inicial, o controlador consulta o repositório pelo nome comum ou científico e devolve somente os registros correspondentes para `index.html`.

## Principais rotas

| Rota | Método | Função |
| --- | --- | --- |
| `/` | GET | Lista as plantas e realiza a pesquisa |
| `/plantas/nova` | GET | Abre o formulário de cadastro |
| `/plantas` | POST | Salva uma nova planta |
| `/plantas/{id}` | GET | Mostra os detalhes de uma planta |
| `/plantas/{id}/editar` | GET | Abre um registro para edição |
| `/plantas/{id}` | POST | Atualiza um registro existente |
| `/plantas/{id}/excluir` | POST | Exclui um registro |

## Estrutura do projeto

```text
herbario-da-apotecaria/
├── pom.xml
├── banco.sql
├── README.md
└── src/main/
    ├── java/br/com/herbario/
    │   ├── HerbarioApplication.java
    │   ├── controller/PlantaController.java
    │   ├── model/Planta.java
    │   └── repository/PlantaRepository.java
    └── resources/
        ├── application.properties
        ├── templates/
        │   ├── index.html
        │   ├── formulario.html
        │   └── detalhes.html
        └── static/
            ├── css/style.css
            └── media/
```

## Como executar

### Requisitos

- Java 17 ou mais recente;
- Maven 3.6.3 ou mais recente, ou uma IDE com suporte a Maven;
- MySQL Server em execução.

### Preparar o banco

1. Abra o MySQL Workbench.
2. Execute o arquivo `banco.sql`.

Esse arquivo cria o banco `herbario_apotecaria`. A tabela `plantas` é criada e atualizada pelo JPA quando a aplicação inicia.

### Configurar a conexão

Abra:

```text
src/main/resources/application.properties
```

A configuração padrão usa o usuário `root` e senha vazia. Se o seu MySQL tiver uma senha, altere:

```properties
spring.datasource.username=root
spring.datasource.password=SUA_SENHA
```

Também é possível configurar por variáveis de ambiente:

```text
DB_USER
DB_PASSWORD
DB_URL
```

### Iniciar pela IDE

1. Abra a pasta que contém `pom.xml`.
2. Espere a IDE baixar as dependências do Maven.
3. Abra `HerbarioApplication.java`.
4. Clique em **Run**.
5. Acesse `http://localhost:8080`.

### Iniciar pelo terminal

No terminal, dentro da pasta que contém o `pom.xml`, execute:

```bash
mvn spring-boot:run
```

Para apenas verificar se o projeto compila:

```bash
mvn clean package -DskipTests
```

Se a porta 8080 estiver ocupada, acrescente ao `application.properties`:

```properties
server.port=8081
```

Nesse caso, abra `http://localhost:8081`.

## Roteiro para testar e apresentar

Uma demonstração curta pode seguir esta ordem:

1. Cadastre **Camomila**, preenchendo os campos principais e uma imagem.
2. Mostre o registro completo e explique que os dados vieram do banco.
3. Volte para a coleção e pesquise `Camomila`.
4. Cadastre **Beladona** com toxicidade alta para mostrar o aviso “Veneno absoluto!”.
5. Edite um campo da Camomila e salve.
6. Feche e abra novamente a aplicação para mostrar que os dados continuam armazenados.
7. Exclua um registro e confirme que ele desaparece da coleção.

Para conferir diretamente no banco:

```sql
USE herbario_apotecaria;
SELECT * FROM plantas;
```

## Relação com a atividade

O requisito de **pesquisar e armazenar dados** aparece na pesquisa por nome e no cadastro persistido no MySQL. A conexão é demonstrada pelo Spring Boot consultando e gravando na tabela `plantas`. A interface é feita em HTML e CSS, com páginas separadas para coleção, cadastro e detalhes. A parte de codificação inclui as rotas, validação, CRUD, busca, upload de imagem e integração com o banco.

## Observação sobre o conteúdo

Este é um projeto escolar e as informações sobre usos e toxicidade servem apenas para preencher e demonstrar o catálogo. Elas não substituem orientação médica, farmacêutica ou botânica.

As ilustrações foram usadas como referências visuais para esta apresentação escolar. Caso o site seja publicado, é preciso verificar as autorizações de uso das imagens.

---

**Herbário da Apotecária** · © 2026 Catharine Toso Taques
