package com.insper.spotifyAPI.controller;

import com.insper.spotifyAPI.model.Podcast;
import com.insper.spotifyAPI.service.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/podcasts")
public class PodcastController {

    @Autowired
    private PodcastService podcastService;

    @PostMapping
    public Podcast criarPodcast(@RequestBody Podcast podcast) {
        return podcastService.criarPodcast(podcast);
    }

    @GetMapping
    public List<Podcast> listarPodcasts() {
        return podcastService.listarPodcasts();
    }

    @GetMapping("/{id}")
    public Podcast buscarPorId(@PathVariable Long id) {
        return podcastService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Podcast atualizarPodcast(@PathVariable Long id, @RequestBody Podcast podcast) {
        return podcastService.atualizarPodcast(id, podcast);
    }

    @DeleteMapping("/{id}")
    public Podcast deletarPodcast(@PathVariable Long id) {
        return podcastService.deletarPodcast(id);
    }
}