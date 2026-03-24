package com.insper.spotifyAPI.dto;

public class TopMusicaDTO {

    private String titulo;
    private String nomeArtista;
    private Long totalReproducoes;

    public TopMusicaDTO() {
    }

    public TopMusicaDTO(String titulo, String nomeArtista, Long totalReproducoes) {
        this.titulo = titulo;
        this.nomeArtista = nomeArtista;
        this.totalReproducoes = totalReproducoes;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getNomeArtista() {
        return nomeArtista;
    }

    public Long getTotalReproducoes() {
        return totalReproducoes;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setNomeArtista(String nomeArtista) {
        this.nomeArtista = nomeArtista;
    }

    public void setTotalReproducoes(Long totalReproducoes) {
        this.totalReproducoes = totalReproducoes;
    }
}