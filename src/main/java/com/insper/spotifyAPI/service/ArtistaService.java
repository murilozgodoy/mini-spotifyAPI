package com.insper.spotifyAPI.service;

import com.insper.spotifyAPI.model.Artista;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArtistaService {

    private final Map<Long, Artista> artistas = new HashMap<>();
    private Long proximoId = 1L;

    public Artista criarArtista(Artista artista) {
        artista.setId(proximoId++);
        artista.setAtivo(true);

        artistas.put(artista.getId(), artista);
        return artista;
    }

    public List<Artista> listarArtistas() {
        return new ArrayList<>(artistas.values());
    }

    public Artista buscarPorId(Long id) {
        return artistas.get(id);
    }

    public Artista atualizarArtista(Long id, Artista artistaAtualizado) {
        Artista artistaExistente = artistas.get(id);

        if (artistaExistente == null) {
            return null;
        }

        artistaExistente.setNome(artistaAtualizado.getNome());
        artistaExistente.setGeneroMusical(artistaAtualizado.getGeneroMusical());
        artistaExistente.setPaisOrigem(artistaAtualizado.getPaisOrigem());

        return artistaExistente;
    }

    public Artista deletarArtista(Long id) {
        Artista artista = artistas.get(id);

        if (artista == null) {
            return null;
        }

        artista.setAtivo(false);
        return artista;
    }
}