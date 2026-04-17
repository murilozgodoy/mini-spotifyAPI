package com.insper.spotifyAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String generoMusical;

    @Column(nullable = false)
    private String paisOrigem;

    @Column(nullable = false)
    private Boolean ativo;

    @OneToMany(mappedBy = "artista")
    @JsonIgnoreProperties({"artista"})
    private List<Album> albuns;

    @OneToMany(mappedBy = "artista")
    @JsonIgnoreProperties({"artista", "album"})
    private List<Musica> musicas;

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

    public List<Album> getAlbuns() {
        return albuns;
    }

    public void setAlbuns(List<Album> albuns) {
        this.albuns = albuns;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }
}