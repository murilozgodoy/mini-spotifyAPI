package com.insper.spotifyAPI.service;

import com.insper.spotifyAPI.model.Artista;
import com.insper.spotifyAPI.repository.ArtistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistaService {

    private final ArtistaRepository artistaRepository;

    public ArtistaService(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public Artista criarArtista(Artista artista) {
        if (artista == null || artista.getNome() == null || artista.getNome().isBlank()
                || artista.getGeneroMusical() == null || artista.getGeneroMusical().isBlank()
                || artista.getPaisOrigem() == null || artista.getPaisOrigem().isBlank()) {
            return null;
        }

        if (artistaRepository.existsByNomeIgnoreCase(artista.getNome())) {
            return null;
        }

        artista.setId(null);
        artista.setAtivo(true);
        return artistaRepository.save(artista);
    }

    public List<Artista> listarArtistas() {
        return artistaRepository.findByAtivoTrue();
    }

    public Artista buscarPorId(Long id) {
        return artistaRepository.findById(id)
                .filter(artista -> Boolean.TRUE.equals(artista.getAtivo()))
                .orElse(null);
    }

    public Artista atualizarArtista(Long id, Artista artistaAtualizado) {
        Artista artistaExistente = artistaRepository.findById(id).orElse(null);

        if (artistaExistente == null || Boolean.FALSE.equals(artistaExistente.getAtivo())) {
            return null;
        }

        if (artistaAtualizado == null || artistaAtualizado.getNome() == null || artistaAtualizado.getNome().isBlank()
                || artistaAtualizado.getGeneroMusical() == null || artistaAtualizado.getGeneroMusical().isBlank()
                || artistaAtualizado.getPaisOrigem() == null || artistaAtualizado.getPaisOrigem().isBlank()) {
            return null;
        }

        if (!artistaExistente.getNome().equalsIgnoreCase(artistaAtualizado.getNome())
                && artistaRepository.existsByNomeIgnoreCase(artistaAtualizado.getNome())) {
            return null;
        }

        artistaExistente.setNome(artistaAtualizado.getNome());
        artistaExistente.setGeneroMusical(artistaAtualizado.getGeneroMusical());
        artistaExistente.setPaisOrigem(artistaAtualizado.getPaisOrigem());

        return artistaRepository.save(artistaExistente);
    }

    public Artista deletarArtista(Long id) {
        Artista artista = artistaRepository.findById(id).orElse(null);

        if (artista == null || Boolean.FALSE.equals(artista.getAtivo())) {
            return null;
        }

        artista.setAtivo(false);
        return artistaRepository.save(artista);
    }
}