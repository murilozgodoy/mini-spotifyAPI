package com.insper.spotifyAPI.service;

import com.insper.spotifyAPI.model.Album;
import com.insper.spotifyAPI.model.Artista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlbumService {

    private final Map<Long, Album> albuns = new HashMap<>();
    private Long proximoId = 1L;

    @Autowired
    private ArtistaService artistaService;

    public Album criarAlbum(Album album) {
        if (album.getArtista() == null || album.getArtista().getId() == null) {
            return null;
        }

        Artista artista = artistaService.buscarPorId(album.getArtista().getId());

        if (artista == null || Boolean.FALSE.equals(artista.getAtivo())) {
            return null;
        }

        album.setId(proximoId++);
        album.setArtista(artista);
        album.setAtivo(true);

        albuns.put(album.getId(), album);
        return album;
    }

    public List<Album> listarAlbuns() {
        return new ArrayList<>(albuns.values());
    }

    public Album buscarPorId(Long id) {
        return albuns.get(id);
    }

    public Album atualizarAlbum(Long id, Album albumAtualizado) {
        Album albumExistente = albuns.get(id);

        if (albumExistente == null) {
            return null;
        }

        if (albumAtualizado.getArtista() == null || albumAtualizado.getArtista().getId() == null) {
            return null;
        }

        Artista artista = artistaService.buscarPorId(albumAtualizado.getArtista().getId());

        if (artista == null || Boolean.FALSE.equals(artista.getAtivo())) {
            return null;
        }

        albumExistente.setTitulo(albumAtualizado.getTitulo());
        albumExistente.setDataLancamento(albumAtualizado.getDataLancamento());
        albumExistente.setArtista(artista);

        return albumExistente;
    }

    public Album deletarAlbum(Long id) {
        Album album = albuns.get(id);

        if (album == null) {
            return null;
        }

        album.setAtivo(false);
        return album;
    }
}