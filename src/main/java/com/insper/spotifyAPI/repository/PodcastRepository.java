package com.insper.spotifyAPI.repository;

import com.insper.spotifyAPI.model.Podcast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PodcastRepository extends JpaRepository<Podcast, Long> {
    boolean existsByTituloIgnoreCase(String titulo);
    List<Podcast> findByAtivoTrue();
}