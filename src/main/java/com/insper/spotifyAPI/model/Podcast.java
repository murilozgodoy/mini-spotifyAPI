package com.insper.spotifyAPI.model;

import jakarta.persistence.*;

@Entity
@Table(name = "podcasts")
public class Podcast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Integer numeroEpisodios;

    @Column(nullable = false)
    private Boolean ativo;

    public Podcast() {
    }

    public Podcast(Long id, String titulo, String descricao, Integer numeroEpisodios, Boolean ativo) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.numeroEpisodios = numeroEpisodios;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getNumeroEpisodios() {
        return numeroEpisodios;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setNumeroEpisodios(Integer numeroEpisodios) {
        this.numeroEpisodios = numeroEpisodios;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}