package com.insper.spotifyAPI.repository;

import com.insper.spotifyAPI.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    boolean existsByTituloIgnoreCase(String titulo);
    List<Album> findByAtivoTrue();
}