package com.insper.spotifyAPI.model;

public class Musica {

    private Long id;
    private String titulo;
    private Integer duracaoSegundos;
    private Integer numeroFaixa;
    private Album album;
    private Artista artista;
    private Long totalReproducoes;
    private Boolean ativo;

    public Musica() {
    }

    public Musica(Long id, String titulo, Integer duracaoSegundos, Integer numeroFaixa,
                  Album album, Artista artista, Long totalReproducoes, Boolean ativo) {
        this.id = id;
        this.titulo = titulo;
        this.duracaoSegundos = duracaoSegundos;
        this.numeroFaixa = numeroFaixa;
        this.album = album;
        this.artista = artista;
        this.totalReproducoes = totalReproducoes;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public void setDuracaoSegundos(Integer duracaoSegundos) {
        this.duracaoSegundos = duracaoSegundos;
    }

    public Integer getNumeroFaixa() {
        return numeroFaixa;
    }

    public void setNumeroFaixa(Integer numeroFaixa) {
        this.numeroFaixa = numeroFaixa;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Long getTotalReproducoes() {
        return totalReproducoes;
    }

    public void setTotalReproducoes(Long totalReproducoes) {
        this.totalReproducoes = totalReproducoes;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}