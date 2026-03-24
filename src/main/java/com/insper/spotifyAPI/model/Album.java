package com.insper.spotifyAPI.model;

import java.time.LocalDate;

public class Album {

    private Long id;
    private String titulo;
    private LocalDate dataLancamento;
    private Artista artista;
    private Boolean ativo;

    public Album() {
    }

    public Album(Long id, String titulo, LocalDate dataLancamento, Artista artista, Boolean ativo) {
        this.id = id;
        this.titulo = titulo;
        this.dataLancamento = dataLancamento;
        this.artista = artista;
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

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}