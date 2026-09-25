# 🌿 Herbário da Apotecária

O **Herbário da Apotecária** é uma aplicação web para criar e organizar uma coleção digital de plantas.

A aplicação permite cadastrar espécies, armazenar informações botânicas, pesquisar plantas pelo nome, adicionar imagens e manter os registros salvos em um banco de dados MySQL.

O projeto nasceu como um trabalho acadêmico de desenvolvimento web com o tema **Plantas**. A identidade visual foi inspirada na atmosfera de *Diários de uma Apotecária*, especialmente na ideia de um caderno de observações sobre plantas, ervas e seus diferentes usos.

> **Esta é a versão 1.0 do projeto**, originalmente desenvolvida como trabalho acadêmico. O Herbário continua em desenvolvimento e está sendo expandido para funcionar como um caderno botânico digital mais completo.

---

## ✦ Funcionalidades

Atualmente, o Herbário permite:

* cadastrar plantas;
* visualizar todas as plantas da coleção;
* pesquisar pelo nome comum ou científico;
* consultar a ficha completa de uma planta;
* editar registros existentes;
* excluir plantas;
* adicionar uma imagem ao registro;
* armazenar permanentemente os dados no MySQL.

Cada planta pode conter:

* nome comum;
* nome científico;
* categoria;
* nível de toxicidade;
* origem;
* habitat;
* parte utilizada;
* cultivo e cuidados;
* usos ou observações;
* descrição;
* curiosidades;
* imagem.

Quando uma planta recebe o nível de toxicidade **Alta**, a interface também exibe o destaque **“Veneno absoluto!”**. Esse aviso faz parte da identidade visual da aplicação e é determinado pelo valor informado no cadastro.

---

## ✦ Como funciona

O Herbário é uma aplicação web desenvolvida em **Java com Spring Boot**.

O navegador exibe as páginas da aplicação, enquanto o Spring Boot processa as ações realizadas pelo usuário e se comunica com o banco MySQL.

De forma simplificada:

```text
Usuário
   ↓
Interface HTML + CSS
   ↓
Thymeleaf
   ↓
Spring Boot / Java
   ↓
JPA + Hibernate
   ↓
MySQL
```

Por exemplo, ao cadastrar uma planta:

```text
Preenchimento do formulário
          ↓
POST /plantas
          ↓
PlantaController
          ↓
PlantaRepository
          ↓
Hibernate / JPA
          ↓
MySQL
          ↓
Página da planta cadastrada
```

Isso significa que o projeto não é apenas um conjunto de páginas HTML: os registros são processados pelo backend e armazenados em um banco de dados.

---

# Tecnologias

## Java + Spring Boot

O **Java** contém a lógica principal da aplicação.

O **Spring Boot** é responsável por executar o servidor web e integrar os diferentes componentes do sistema.

---

## Spring MVC

A aplicação segue a estrutura MVC (*Model–View–Controller*), separando as responsabilidades do código:

**Model — `Planta.java`**
Representa uma planta e define quais informações podem ser armazenadas.

**View — templates HTML**
Representa as páginas que aparecem no navegador.

**Controller — `PlantaController.java`**
Recebe as requisições do navegador e determina o que a aplicação deve fazer.

---

## Thymeleaf

O **Thymeleaf** conecta as páginas HTML aos dados enviados pelo Java.

Na página inicial, por exemplo, ele percorre a lista de plantas recebida do backend e gera automaticamente um cartão para cada registro encontrado.

---

## JPA + Hibernate

O projeto utiliza **JPA** para relacionar os objetos Java aos registros do banco de dados.

O **Hibernate**, utilizado pelo Spring Boot como implementação da JPA, transforma operações realizadas no Java em comandos para o MySQL.

Por isso, uma operação como:

```java
repository.save(planta);
```

pode criar ou atualizar um registro no banco sem ser necessário escrever manualmente um comando `INSERT` ou `UPDATE`.

---

## MySQL

O **MySQL** é responsável pela persistência das informações.

O banco utilizado pelo projeto é:

```text
herbario_apotecaria
```

A tabela principal é:

```text
plantas
```

Como os registros ficam armazenados no banco, fechar a aplicação não apaga a coleção cadastrada.

---

## HTML + CSS

O **HTML** organiza o conteúdo das páginas e o **CSS** define a interface visual.

A identidade do Herbário utiliza elementos inspirados em cadernos botânicos e na estética de *Diários de uma Apotecária*, incluindo tons de jardim, papel claro, tipografia editorial e ilustrações decorativas.

A interface também possui adaptações para diferentes tamanhos de tela.

---

# Rotas principais

| Rota                    | Método | Função                                      |
| ----------------------- | ------ | ------------------------------------------- |
| `/`                     | `GET`  | Exibe a coleção e permite pesquisar plantas |
| `/plantas/nova`         | `GET`  | Abre o formulário de cadastro               |
| `/plantas`              | `POST` | Cadastra uma nova planta                    |
| `/plantas/{id}`         | `GET`  | Exibe a ficha completa de uma planta        |
| `/plantas/{id}/editar`  | `GET`  | Abre uma planta para edição                 |
| `/plantas/{id}`         | `POST` | Salva as alterações de uma planta           |
| `/plantas/{id}/excluir` | `POST` | Exclui uma planta                           |

---

# Estrutura do projeto

```text
herbario-da-apotecaria/
├── pom.xml
├── banco.sql
├── README.md
│
└── src/main/
    ├── java/br/com/herbario/
    │   ├── HerbarioApplication.java
    │   ├── controller/
    │   │   └── PlantaController.java
    │   ├── model/
    │   │   └── Planta.java
    │   └── repository/
    │       └── PlantaRepository.java
    │
    └── resources/
        ├── application.properties
        │
        ├── templates/
        │   ├── index.html
        │   ├── formulario.html
        │   └── detalhes.html
        │
        └── static/
            ├── css/
            │   └── style.css
            └── media/
```

### Arquivos principais

**`HerbarioApplication.java`**
Ponto de entrada da aplicação. É a classe que inicia o Spring Boot.

**`Planta.java`**
Define a estrutura de uma planta e seu relacionamento com a tabela do banco.

**`PlantaController.java`**
Controla cadastro, pesquisa, visualização, edição e exclusão.

**`PlantaRepository.java`**
Faz a comunicação entre a aplicação e os registros armazenados no MySQL.

**`application.properties`**
Contém as configurações da aplicação e da conexão com o banco.

**`templates/`**
Contém as páginas HTML processadas pelo Thymeleaf.

**`static/`**
Contém CSS, imagens e outros arquivos estáticos da interface.

---

# Executando localmente

## Requisitos

Para executar o projeto, é necessário ter:

* Java 17 ou superior;
* MySQL Server;
* Maven 3.6.3 ou superior, ou utilizar o Maven Wrapper incluído no projeto.

---

## 1. Preparar o banco

Com o MySQL em execução, execute:

```text
banco.sql
```

O script cria o banco:

```text
herbario_apotecaria
```

A estrutura da tabela `plantas` é gerenciada pelo Hibernate quando a aplicação é iniciada.

---

## 2. Configurar a conexão

A aplicação utiliza variáveis de ambiente para permitir que as credenciais do banco permaneçam fora do código.

As variáveis disponíveis são:

```text
DB_URL
DB_USER
DB_PASSWORD
```

A configuração padrão aponta para:

```text
jdbc:mysql://localhost:3306/herbario_apotecaria
```

e utiliza:

```text
DB_USER=root
```

Defina `DB_PASSWORD` de acordo com a senha configurada no seu MySQL.

> Nunca adicione senhas ou outras credenciais diretamente ao repositório.

---

## 3. Iniciar a aplicação

### Usando uma IDE

Abra a pasta que contém `pom.xml`, aguarde o carregamento das dependências e execute:

```text
HerbarioApplication.java
```

Depois acesse:

```text
http://localhost:8080
```

### Usando o Maven Wrapper

No Windows:

```bash
./mvnw.cmd spring-boot:run
```

No Linux ou macOS:

```bash
./mvnw spring-boot:run
```

Também é possível utilizar uma instalação local do Maven:

```bash
mvn spring-boot:run
```

---

# Imagens

O formulário permite adicionar imagens nos formatos:

* PNG;
* JPG/JPEG;
* WEBP;
* GIF.

O limite atual é de **4 MB por arquivo**.

Na versão 1.0, a imagem é convertida para Base64 e armazenada junto ao registro da planta no banco de dados.

---

# Origem do projeto

O Herbário da Apotecária foi criado inicialmente para um trabalho acadêmico que exigia o desenvolvimento de uma aplicação web capaz de **pesquisar e armazenar dados**, utilizando HTML e CSS e integração com banco de dados.

A primeira versão implementa esses requisitos por meio de:

* operações CRUD;
* pesquisa;
* persistência com MySQL;
* conexão com banco através de JPA/Hibernate;
* backend em Java;
* interface web com Thymeleaf;
* upload de imagens.

Depois da entrega acadêmica, o projeto passou a ser desenvolvido como um projeto pessoal, com a intenção de transformá-lo em um **caderno botânico digital mais completo**.

---

# Versões

### v1.0 — Versão acadêmica

Primeira versão funcional do Herbário.

**Stack:** Java · Spring Boot · Thymeleaf · JPA/Hibernate · MySQL · HTML · CSS

Esta versão contém o catálogo de plantas, pesquisa, CRUD e armazenamento de imagens.

### Próximas versões

O projeto está sendo expandido para explorar funcionalidades de um caderno botânico pessoal, como registros de observações, coleções, fotografias e organização mais avançada das plantas.

---

# Aviso sobre informações botânicas

O Herbário da Apotecária é um projeto de software.

Informações cadastradas sobre toxicidade, propriedades, cultivo ou usos de plantas são fornecidas pelos próprios registros adicionados à aplicação e **não devem ser interpretadas como orientação médica, farmacêutica ou botânica profissional**.

---

# Créditos e direitos de imagem

A versão acadêmica utiliza alguns elementos visuais inspirados em *Diários de uma Apotecária* (*The Apothecary Diaries / Kusuriya no Hitorigoto*).

Esses elementos pertencem aos seus respectivos detentores de direitos e foram utilizados apenas no contexto da apresentação acadêmica. Eles não são de autoria deste projeto.

Uma versão destinada à publicação oficial deverá utilizar recursos visuais próprios ou devidamente licenciados.

---

**Herbário da Apotecária**
© 2026 Catharine Toso Taques
