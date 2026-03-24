package com.insper.spotifyAPI.service;

import com.insper.spotifyAPI.model.Podcast;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PodcastService {

    private final Map<Long, Podcast> podcasts = new HashMap<>();
    private Long proximoId = 1L;

    public Podcast criarPodcast(Podcast podcast) {
        podcast.setId(proximoId++);
        podcast.setAtivo(true);

        podcasts.put(podcast.getId(), podcast);
        return podcast;
    }

    public List<Podcast> listarPodcasts() {
        return new ArrayList<>(podcasts.values());
    }

    public Podcast buscarPorId(Long id) {
        return podcasts.get(id);
    }

    public Podcast atualizarPodcast(Long id, Podcast podcastAtualizado) {
        Podcast podcastExistente = podcasts.get(id);

        if (podcastExistente == null) {
            return null;
        }

        podcastExistente.setTitulo(podcastAtualizado.getTitulo());
        podcastExistente.setDescricao(podcastAtualizado.getDescricao());
        podcastExistente.setNumeroEpisodios(podcastAtualizado.getNumeroEpisodios());

        return podcastExistente;
    }

    public Podcast deletarPodcast(Long id) {
        Podcast podcast = podcasts.get(id);

        if (podcast == null) {
            return null;
        }

        podcast.setAtivo(false);
        return podcast;
    }
}