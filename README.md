# 🎧 Mini Spotify API

## 📌 Descrição

API REST desenvolvida com Spring Boot que simula funcionalidades básicas de uma plataforma de streaming de música, inspirada no Spotify.

A aplicação permite o gerenciamento de usuários, artistas, álbuns, músicas e playlists, além de implementar regras de negócio como reprodução de músicas, gerenciamento de playlists e geração de relatórios.

🚀 Nesta versão, a aplicação foi evoluída para utilizar persistência em banco de dados PostgreSQL com Spring Data JPA, substituindo o armazenamento em memória (HashMap).

---

## 🚀 Tecnologias Utilizadas

* Java 25
* Spring Boot
* Spring Data JPA
* PostgreSQL (Aiven)
* Maven
* Postman (para testes)

---

## 📁 Estrutura do Projeto


src/main/java/com/insper/spotifyAPI
│
├── controller/
├── service/
├── model/
├── repository/
├── dto/
├── enums/


---

## ▶️ Como rodar a aplicação

### 1. Clonar o repositório


git clone <url-do-repositorio>


### 2. Entrar na pasta do projeto


cd spotifyAPI


### 3. Configurar o banco de dados

No arquivo `application.properties`, configure:


spring.datasource.url=jdbc:postgresql://<host>:<porta>/defaultdb?sslmode=require
spring.datasource.username=<usuario>
spring.datasource.password=<senha>


💡 Recomendado utilizar variável de ambiente:


spring.datasource.password=${DB_PASSWORD}


---

### 4. Rodar a aplicação

Via IDE (recomendado):

* Executar a classe `SpotifyApiApplication`

Ou via terminal:


mvn spring-boot:run


---

## 🌐 URL Base


http://localhost:8080


---

## 🧱 Entidades

### Usuário

* id
* nome
* email
* tipoPlano (FREE, PREMIUM)
* ativo
* dataCriacao

### Artista

* id
* nome
* generoMusical
* paisOrigem
* ativo

### Álbum

* id
* titulo
* dataLancamento
* artista
* ativo

### Música

* id
* titulo
* duracaoSegundos
* numeroFaixa
* album
* artista
* totalReproducoes
* ativo

### Playlist

* id
* nome
* publica
* dataCriacao
* usuario
* musicas
* ativo

### Podcast (Entidade Extra)

* id
* titulo
* descricao
* numeroEpisodios
* ativo

---

## 🔗 Relacionamentos

* Um artista possui vários álbuns
* Um álbum possui várias músicas
* Um usuário possui várias playlists
* Uma playlist possui várias músicas (N:N)

---

## 🌐 Endpoints

### 👤 Usuários

* POST `/usuarios`
* GET `/usuarios`
* GET `/usuarios/{id}`
* PUT `/usuarios/{id}`
* DELETE `/usuarios/{id}`

---

### 🎤 Artistas

* POST `/artistas`
* GET `/artistas`
* GET `/artistas/{id}`
* PUT `/artistas/{id}`
* DELETE `/artistas/{id}`

---

### 💿 Álbuns

* POST `/albuns`
* GET `/albuns`
* GET `/albuns/{id}`
* PUT `/albuns/{id}`
* DELETE `/albuns/{id}`

---

### 🎵 Músicas

* POST `/musicas`
* GET `/musicas`
* GET `/musicas/{id}`
* PUT `/musicas/{id}`
* DELETE `/musicas/{id}`

---

### 📀 Playlists

* POST `/playlists`
* GET `/playlists`
* GET `/playlists/{id}`
* PUT `/playlists/{id}`
* DELETE `/playlists/{id}`

---

### 🎙️ Podcasts

* POST `/podcasts`
* GET `/podcasts`
* GET `/podcasts/{id}`
* PUT `/podcasts/{id}`
* DELETE `/podcasts/{id}`

---

## 🚀 Regras de Negócio

### ▶️ Reproduzir Música


POST /musicas/{id}/reproduzir


Header obrigatório:


X-USER-ID: {idUsuario}


Regras:

* Incrementa o número de reproduções
* Usuário deve estar ativo

---

### ➕ Adicionar Música à Playlist


POST /playlists/{playlistId}/musicas/{musicaId}


Header obrigatório:


X-USER-ID: {idUsuario}


Regras:

* Apenas o dono da playlist pode adicionar
* Não permite músicas duplicadas
* Playlist deve existir

---

### 📊 Top 10 Músicas Mais Reproduzidas


GET /relatorios/top-musicas


Retorna:

* título da música
* nome do artista
* total de reproduções

---

## ⚠️ Regras Gerais

* Exclusão lógica (uso de campo `ativo`)
* Entidades inativas não podem ser utilizadas
* Validações realizadas no Service
* Persistência realizada via PostgreSQL + JPA

---

## 📬 Testes

Todos os endpoints foram testados utilizando o Postman.

Foram validados:

* criação de entidades
* relacionamentos
* regras de negócio
* persistência no banco de dados

---

## 👨‍💻 Autor

Murilo Godoy 🚀
