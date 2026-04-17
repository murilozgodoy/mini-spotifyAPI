package com.insper.spotifyAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "musicas")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String titulo;

    @Column(nullable = false)
    private Integer duracaoSegundos;

    @Column(nullable = false)
    private Integer numeroFaixa;

    @ManyToOne(optional = false)
    @JoinColumn(name = "album_id", nullable = false)
    @JsonIgnoreProperties({"musicas", "artista"})
    private Album album;

    @ManyToOne(optional = false)
    @JoinColumn(name = "artista_id", nullable = false)
    @JsonIgnoreProperties({"albuns", "musicas"})
    private Artista artista;

    @Column(nullable = false)
    private Long totalReproducoes;

    @Column(nullable = false)
    private Boolean ativo;

    @ManyToMany(mappedBy = "musicas")
    @JsonIgnore
    private List<Playlist> playlists;

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

    public void setId(Long id) { this.id = id; }

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

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }
}