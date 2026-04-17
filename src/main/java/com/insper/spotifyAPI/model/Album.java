package com.insper.spotifyAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "albuns")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String titulo;

    private LocalDate dataLancamento;

    @ManyToOne(optional = false)
    @JoinColumn(name = "artista_id", nullable = false)
    @JsonIgnoreProperties({"albuns", "musicas"})
    private Artista artista;

    @Column(nullable = false)
    private Boolean ativo;

    @OneToMany(mappedBy = "album")
    @JsonIgnoreProperties({"album", "artista"})
    private List<Musica> musicas;

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

    public void setId(Long id) { this.id = id; }

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

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }
}