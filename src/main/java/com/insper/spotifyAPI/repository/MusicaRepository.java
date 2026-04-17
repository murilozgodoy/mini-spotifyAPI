package com.insper.spotifyAPI.repository;

import com.insper.spotifyAPI.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicaRepository extends JpaRepository<Musica, Long> {
    boolean existsByTituloIgnoreCase(String titulo);
    List<Musica> findByAtivoTrue();
}