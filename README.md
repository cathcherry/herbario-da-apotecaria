<div align="center">

# 🌿 Herbário da Apotecária

### Um herbário digital para catalogar, pesquisar e organizar plantas.

![Java](https://img.shields.io/badge/Java-17%2B-orange?style=flat-square)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Backend-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-Database-4479A1?style=flat-square&logo=mysql&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-Templates-005F0F?style=flat-square&logo=thymeleaf&logoColor=white)
![Version](https://img.shields.io/badge/version-v1.0-6f8065?style=flat-square)

</div>

---

## Sobre o projeto

O **Herbário da Apotecária** é uma aplicação web para criar e organizar uma coleção digital de plantas.

A aplicação permite cadastrar espécies, pesquisar registros, consultar fichas completas, adicionar imagens e editar ou excluir informações. Os dados são armazenados em um banco de dados **MySQL**, permitindo que a coleção continue disponível mesmo depois que a aplicação é encerrada.

O projeto surgiu originalmente como um trabalho acadêmico de desenvolvimento web com o tema **Plantas**. Sua identidade visual foi inspirada na atmosfera de *Diários de uma Apotecária*, principalmente na ideia de um caderno de observações sobre plantas, ervas e botânica.

> [!NOTE]
> Esta é a **versão 1.0**, correspondente à versão acadêmica original do projeto.
>
> O Herbário continuará sendo desenvolvido como projeto pessoal, com a intenção de se tornar um caderno botânico digital mais completo e útil no dia a dia.

---

## Funcionalidades

A versão atual permite:

- cadastrar novas plantas;
- visualizar todas as plantas cadastradas;
- pesquisar pelo nome comum ou científico;
- abrir a ficha completa de uma planta;
- editar informações existentes;
- excluir registros;
- adicionar uma imagem à ficha;
- armazenar permanentemente os dados no MySQL;
- identificar plantas por categoria e nível de toxicidade;
- utilizar a aplicação em diferentes tamanhos de tela.

Cada planta pode armazenar:

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
- imagem.

Quando uma planta é cadastrada com toxicidade **Alta**, a interface exibe o destaque visual **“Veneno absoluto!”**.

Esse destaque faz parte da identidade visual da aplicação e é definido a partir do valor informado no cadastro.

---

## Tecnologias

| Tecnologia | Função |
| --- | --- |
| Java | Lógica principal da aplicação |
| Spring Boot | Backend e servidor web |
| Spring MVC | Organização da aplicação em Model, View e Controller |
| Thymeleaf | Integração entre Java e HTML |
| Spring Data JPA | Comunicação com o banco de dados |
| Hibernate | Mapeamento entre objetos Java e registros do banco |
| MySQL | Armazenamento persistente dos dados |
| HTML | Estrutura das páginas |
| CSS | Interface e identidade visual |
| Maven | Gerenciamento de dependências e build |

---

## Como a aplicação funciona

O Herbário utiliza uma arquitetura web baseada em Spring Boot.

De forma simplificada:

```text
Navegador
    │
    ▼
HTML + CSS
    │
    ▼
Thymeleaf
    │
    ▼
Spring MVC
    │
    ▼
Controller
    │
    ▼
Repository
    │
    ▼
JPA / Hibernate
    │
    ▼
MySQL
```

O navegador apresenta a interface para o usuário.

O **Thymeleaf** conecta as páginas HTML às informações enviadas pelo backend.

O **Spring MVC** recebe as requisições feitas pelo navegador e encaminha cada ação para o código responsável.

O **JPA e o Hibernate** fazem a comunicação entre os objetos Java e o banco de dados.

O **MySQL** armazena os registros permanentemente.

### Exemplo de cadastro

```text
Usuário preenche o formulário
            │
            ▼
       POST /plantas
            │
            ▼
   PlantaController
            │
            ▼
   PlantaRepository
            │
            ▼
     JPA / Hibernate
            │
            ▼
          MySQL
            │
            ▼
Página de detalhes da planta
```

---

## Estrutura do projeto

```text
herbario-da-apotecaria/
│
├── .mvn/
│
├── src/
│   └── main/
│       │
│       ├── java/br/com/herbario/
│       │   ├── HerbarioApplication.java
│       │   │
│       │   ├── controller/
│       │   │   └── PlantaController.java
│       │   │
│       │   ├── model/
│       │   │   └── Planta.java
│       │   │
│       │   └── repository/
│       │       └── PlantaRepository.java
│       │
│       └── resources/
│           ├── application.properties
│           │
│           ├── templates/
│           │   ├── index.html
│           │   ├── formulario.html
│           │   └── detalhes.html
│           │
│           └── static/
│               ├── css/
│               │   └── style.css
│               └── media/
│
├── .gitignore
├── banco.sql
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

### Principais arquivos

#### `HerbarioApplication.java`

Classe principal do projeto.

É responsável por iniciar a aplicação Spring Boot.

#### `Planta.java`

Representa uma planta dentro do sistema.

A classe define os campos que serão armazenados e mapeados para a tabela correspondente no banco de dados.

#### `PlantaController.java`

Recebe as requisições feitas pelo navegador e controla as principais operações da aplicação:

- cadastro;
- pesquisa;
- visualização;
- edição;
- exclusão.

#### `PlantaRepository.java`

Interface responsável pela comunicação entre a aplicação e os registros armazenados no MySQL.

#### `application.properties`

Contém as configurações da aplicação e da conexão com o banco de dados.

#### `templates/`

Contém as páginas HTML processadas pelo Thymeleaf.

#### `static/`

Contém os arquivos estáticos utilizados pela interface, como CSS e imagens.

---

## Rotas

| Método | Rota | Função |
| --- | --- | --- |
| `GET` | `/` | Exibe a coleção e realiza pesquisas |
| `GET` | `/plantas/nova` | Abre o formulário de cadastro |
| `POST` | `/plantas` | Cadastra uma nova planta |
| `GET` | `/plantas/{id}` | Exibe a ficha completa |
| `GET` | `/plantas/{id}/editar` | Abre o formulário de edição |
| `POST` | `/plantas/{id}` | Atualiza um registro |
| `POST` | `/plantas/{id}/excluir` | Exclui uma planta |

---

## Banco de dados

O projeto utiliza **MySQL**.

O banco usado pela aplicação é:

```text
herbario_apotecaria
```

A tabela principal é:

```text
plantas
```

A estrutura da tabela é vinculada à entidade `Planta.java`.

A configuração atual utiliza:

```properties
spring.jpa.hibernate.ddl-auto=update
```

Com isso, o Hibernate pode atualizar a estrutura do banco de acordo com alterações realizadas na entidade.

---

## Imagens

O formulário permite adicionar imagens nos formatos:

- PNG;
- JPG ou JPEG;
- WEBP;
- GIF.

O tamanho máximo configurado atualmente é de **4 MB**.

Na versão 1.0, a imagem é convertida para **Base64** e armazenada junto ao registro da planta no banco de dados.

---

## Executando o projeto

### Requisitos

Para executar a aplicação localmente, é necessário ter:

- Java 17 ou superior;
- MySQL Server;
- Git, caso o projeto seja clonado pelo GitHub.

O projeto inclui o **Maven Wrapper**, portanto não é necessário instalar o Maven separadamente.

---

### 1. Clonar o repositório

```bash
git clone https://github.com/SEU-USUARIO/herbario-da-apotecaria.git
```

Entre na pasta:

```bash
cd herbario-da-apotecaria
```

---

### 2. Preparar o banco de dados

Com o MySQL Server em execução, execute o arquivo:

```text
banco.sql
```

O script cria o banco:

```text
herbario_apotecaria
```

A tabela utilizada pela aplicação é gerenciada pelo Hibernate quando o projeto é iniciado.

---

### 3. Configurar a conexão

A configuração de banco está localizada em:

```text
src/main/resources/application.properties
```

O projeto utiliza as seguintes variáveis de ambiente:

```text
DB_URL
DB_USER
DB_PASSWORD
```

A URL padrão é:

```text
jdbc:mysql://localhost:3306/herbario_apotecaria
```

O usuário padrão é:

```text
root
```

A senha deve ser definida através da variável:

```text
DB_PASSWORD
```

A configuração utilizada pela aplicação segue este formato:

```properties
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/herbario_apotecaria?useUnicode=true&characterEncoding=UTF-8&serverTimezone=America/Sao_Paulo}
spring.datasource.username=${DB_USER:root}
spring.datasource.password=${DB_PASSWORD:}
```

> [!IMPORTANT]
> Senhas, tokens e outras credenciais não devem ser adicionados diretamente ao repositório.

---

### 4. Iniciar a aplicação

#### Windows

```powershell
.\mvnw.cmd spring-boot:run
```

#### Linux ou macOS

```bash
./mvnw spring-boot:run
```

Também é possível executar a classe:

```text
HerbarioApplication.java
```

diretamente por uma IDE compatível com Java e Maven.

Depois que a aplicação iniciar, acesse:

```text
http://localhost:8080
```

---

## Origem acadêmica

A primeira versão do Herbário da Apotecária foi criada para um trabalho de integração que propunha o desenvolvimento de uma aplicação web capaz de **pesquisar e armazenar dados**.

Os principais critérios da atividade envolviam:

- banco de dados;
- conexão com o banco;
- interface gráfica;
- programação.

A versão 1.0 atende a esses requisitos através de uma aplicação com:

```text
Interface web
      +
CRUD
      +
Pesquisa
      +
Backend em Java
      +
Persistência em MySQL
```

Depois da conclusão da atividade acadêmica, o projeto passou a ser mantido como um projeto pessoal.

---

## Versões

### v1.0.0 — Versão acadêmica

Primeira versão funcional do Herbário da Apotecária.

**Stack:**

`Java` · `Spring Boot` · `Thymeleaf` · `JPA/Hibernate` · `MySQL` · `HTML` · `CSS`

Principais recursos:

- cadastro de plantas;
- pesquisa;
- visualização individual;
- edição;
- exclusão;
- armazenamento persistente;
- upload de imagens.

---

## Próximos passos

O objetivo das próximas versões é transformar o Herbário em um **caderno botânico digital de uso pessoal**, indo além de um simples catálogo.

Algumas funcionalidades planejadas ou consideradas para o futuro incluem:

- contas de usuário;
- herbários pessoais;
- coleções;
- favoritos;
- tags;
- filtros avançados;
- múltiplas fotografias por planta;
- diário de observações;
- histórico de registros;
- acompanhamento de plantas cultivadas;
- fichas botânicas para impressão;
- organização de observações por data;
- armazenamento de informações sobre exemplares individuais.

A arquitetura e as tecnologias utilizadas nas próximas versões também poderão evoluir conforme o projeto crescer.

---

## Aviso sobre informações botânicas

O **Herbário da Apotecária é um projeto de software**.

As informações sobre toxicidade, cultivo, propriedades ou usos das plantas são inseridas nos próprios registros da aplicação.

O conteúdo não deve ser utilizado como substituto para orientação médica, farmacêutica, agronômica ou botânica profissional.

---

## Créditos visuais

A versão acadêmica utiliza alguns elementos visuais inspirados em *Diários de uma Apotecária* (*Kusuriya no Hitorigoto / The Apothecary Diaries*).

Esses elementos pertencem aos seus respectivos detentores de direitos e não fazem parte dos recursos visuais originais do projeto.

Uma versão destinada à publicação pública deverá utilizar imagens e elementos gráficos próprios ou devidamente licenciados.

---

<div align="center">

### 🌿 Herbário da Apotecária

Projeto iniciado em setembro de 2026.

**Catharine Toso Taques**

</div>
