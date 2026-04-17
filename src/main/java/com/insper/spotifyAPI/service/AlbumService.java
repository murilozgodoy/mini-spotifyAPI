package com.insper.spotifyAPI.service;

import com.insper.spotifyAPI.model.Album;
import com.insper.spotifyAPI.model.Artista;
import com.insper.spotifyAPI.repository.AlbumRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistaService artistaService;

    public AlbumService(AlbumRepository albumRepository, ArtistaService artistaService) {
        this.albumRepository = albumRepository;
        this.artistaService = artistaService;
    }

    public Album criarAlbum(Album album) {
        if (album == null || album.getTitulo() == null || album.getTitulo().isBlank()
                || album.getArtista() == null || album.getArtista().getId() == null) {
            return null;
        }

        Artista artista = artistaService.buscarPorId(album.getArtista().getId());

        if (artista == null || Boolean.FALSE.equals(artista.getAtivo())) {
            return null;
        }

        if (albumRepository.existsByTituloIgnoreCase(album.getTitulo())) {
            return null;
        }

        album.setId(null);
        album.setArtista(artista);
        album.setAtivo(true);

        if (album.getDataLancamento() == null) {
            album.setDataLancamento(LocalDate.now());
        }

        return albumRepository.save(album);
    }

    public List<Album> listarAlbuns() {
        return albumRepository.findByAtivoTrue();
    }

    public Album buscarPorId(Long id) {
        return albumRepository.findById(id)
                .filter(album -> Boolean.TRUE.equals(album.getAtivo()))
                .orElse(null);
    }

    public Album atualizarAlbum(Long id, Album albumAtualizado) {
        Album albumExistente = albumRepository.findById(id).orElse(null);

        if (albumExistente == null || Boolean.FALSE.equals(albumExistente.getAtivo())) {
            return null;
        }

        if (albumAtualizado == null || albumAtualizado.getTitulo() == null || albumAtualizado.getTitulo().isBlank()
                || albumAtualizado.getArtista() == null || albumAtualizado.getArtista().getId() == null) {
            return null;
        }

        Artista artista = artistaService.buscarPorId(albumAtualizado.getArtista().getId());

        if (artista == null || Boolean.FALSE.equals(artista.getAtivo())) {
            return null;
        }

        if (!albumExistente.getTitulo().equalsIgnoreCase(albumAtualizado.getTitulo())
                && albumRepository.existsByTituloIgnoreCase(albumAtualizado.getTitulo())) {
            return null;
        }

        albumExistente.setTitulo(albumAtualizado.getTitulo());
        albumExistente.setDataLancamento(albumAtualizado.getDataLancamento());
        albumExistente.setArtista(artista);

        return albumRepository.save(albumExistente);
    }

    public Album deletarAlbum(Long id) {
        Album album = albumRepository.findById(id).orElse(null);

        if (album == null || Boolean.FALSE.equals(album.getAtivo())) {
            return null;
        }

        album.setAtivo(false);
        return albumRepository.save(album);
    }
}