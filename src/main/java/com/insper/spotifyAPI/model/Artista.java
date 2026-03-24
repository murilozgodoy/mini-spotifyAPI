package com.insper.spotifyAPI.model;

public class Artista {

    private Long id;
    private String nome;
    private String generoMusical;
    private String paisOrigem;
    private Boolean ativo;

    public Artista() {
    }

    public Artista(Long id, String nome, String generoMusical, String paisOrigem, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.generoMusical = generoMusical;
        this.paisOrigem = paisOrigem;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}