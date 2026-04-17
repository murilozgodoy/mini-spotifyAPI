package com.insper.spotifyAPI.repository;

import com.insper.spotifyAPI.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    boolean existsByNomeIgnoreCase(String nome);
    List<Playlist> findByAtivoTrue();
}