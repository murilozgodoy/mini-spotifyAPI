package com.insper.spotifyAPI.model;

import java.time.LocalDateTime;
import java.util.List;

public class Playlist {

    private Long id;
    private String nome;
    private Boolean publica;
    private LocalDateTime dataCriacao;
    private Usuario usuario;
    private List<Musica> musicas;
    private Boolean ativo;

    public Playlist() {
    }

    public Playlist(Long id, String nome, Boolean publica, LocalDateTime dataCriacao, Usuario usuario, List<Musica> musicas, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.publica = publica;
        this.dataCriacao = dataCriacao;
        this.usuario = usuario;
        this.musicas = musicas;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Boolean getPublica() {
        return publica;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPublica(Boolean publica) {
        this.publica = publica;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}