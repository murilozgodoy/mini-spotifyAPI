package com.insper.spotifyAPI.service;

import com.insper.spotifyAPI.model.Podcast;
import com.insper.spotifyAPI.repository.PodcastRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PodcastService {

    private final PodcastRepository podcastRepository;

    public PodcastService(PodcastRepository podcastRepository) {
        this.podcastRepository = podcastRepository;
    }

    public Podcast criarPodcast(Podcast podcast) {
        if (podcast == null
                || podcast.getTitulo() == null || podcast.getTitulo().isBlank()
                || podcast.getDescricao() == null || podcast.getDescricao().isBlank()
                || podcast.getNumeroEpisodios() == null || podcast.getNumeroEpisodios() < 0) {
            return null;
        }

        if (podcastRepository.existsByTituloIgnoreCase(podcast.getTitulo())) {
            return null;
        }

        podcast.setId(null);
        podcast.setAtivo(true);

        return podcastRepository.save(podcast);
    }

    public List<Podcast> listarPodcasts() {
        return podcastRepository.findByAtivoTrue();
    }

    public Podcast buscarPorId(Long id) {
        return podcastRepository.findById(id)
                .filter(podcast -> Boolean.TRUE.equals(podcast.getAtivo()))
                .orElse(null);
    }

    public Podcast atualizarPodcast(Long id, Podcast podcastAtualizado) {
        Podcast podcastExistente = podcastRepository.findById(id).orElse(null);

        if (podcastExistente == null || Boolean.FALSE.equals(podcastExistente.getAtivo())) {
            return null;
        }

        if (podcastAtualizado == null
                || podcastAtualizado.getTitulo() == null || podcastAtualizado.getTitulo().isBlank()
                || podcastAtualizado.getDescricao() == null || podcastAtualizado.getDescricao().isBlank()
                || podcastAtualizado.getNumeroEpisodios() == null || podcastAtualizado.getNumeroEpisodios() < 0) {
            return null;
        }

        if (!podcastExistente.getTitulo().equalsIgnoreCase(podcastAtualizado.getTitulo())
                && podcastRepository.existsByTituloIgnoreCase(podcastAtualizado.getTitulo())) {
            return null;
        }

        podcastExistente.setTitulo(podcastAtualizado.getTitulo());
        podcastExistente.setDescricao(podcastAtualizado.getDescricao());
        podcastExistente.setNumeroEpisodios(podcastAtualizado.getNumeroEpisodios());

        return podcastRepository.save(podcastExistente);
    }

    public Podcast deletarPodcast(Long id) {
        Podcast podcast = podcastRepository.findById(id).orElse(null);

        if (podcast == null || Boolean.FALSE.equals(podcast.getAtivo())) {
            return null;
        }

        podcast.setAtivo(false);
        return podcastRepository.save(podcast);
    }
}