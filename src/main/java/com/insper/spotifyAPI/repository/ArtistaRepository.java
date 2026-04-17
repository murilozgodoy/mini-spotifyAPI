package com.insper.spotifyAPI.repository;

import com.insper.spotifyAPI.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    boolean existsByNomeIgnoreCase(String nome);
    List<Artista> findByAtivoTrue();
}