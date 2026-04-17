package com.insper.spotifyAPI.service;

import com.insper.spotifyAPI.model.Musica;
import com.insper.spotifyAPI.model.Playlist;
import com.insper.spotifyAPI.model.Usuario;
import com.insper.spotifyAPI.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final UsuarioService usuarioService;
    private final MusicaService musicaService;

    public PlaylistService(
            PlaylistRepository playlistRepository,
            UsuarioService usuarioService,
            MusicaService musicaService
    ) {
        this.playlistRepository = playlistRepository;
        this.usuarioService = usuarioService;
        this.musicaService = musicaService;
    }

    public Playlist criarPlaylist(Playlist playlist) {
        if (playlist == null
                || playlist.getNome() == null || playlist.getNome().isBlank()
                || playlist.getPublica() == null
                || playlist.getUsuario() == null || playlist.getUsuario().getId() == null) {
            return null;
        }

        Usuario usuario = usuarioService.buscarPorId(playlist.getUsuario().getId());

        if (usuario == null || Boolean.FALSE.equals(usuario.getAtivo())) {
            return null;
        }

        if (playlistRepository.existsByNomeIgnoreCase(playlist.getNome())) {
            return null;
        }

        playlist.setId(null);
        playlist.setDataCriacao(LocalDateTime.now());
        playlist.setUsuario(usuario);
        playlist.setAtivo(true);

        if (playlist.getMusicas() == null) {
            playlist.setMusicas(new ArrayList<>());
        }

        return playlistRepository.save(playlist);
    }

    public List<Playlist> listarPlaylists() {
        return playlistRepository.findByAtivoTrue();
    }

    public Playlist buscarPorId(Long id) {
        return playlistRepository.findById(id)
                .filter(playlist -> Boolean.TRUE.equals(playlist.getAtivo()))
                .orElse(null);
    }

    public Playlist atualizarPlaylist(Long id, Playlist novaPlaylist) {
        Playlist existente = playlistRepository.findById(id).orElse(null);

        if (existente == null || Boolean.FALSE.equals(existente.getAtivo())) {
            return null;
        }

        if (novaPlaylist == null
                || novaPlaylist.getNome() == null || novaPlaylist.getNome().isBlank()
                || novaPlaylist.getPublica() == null) {
            return null;
        }

        if (!existente.getNome().equalsIgnoreCase(novaPlaylist.getNome())
                && playlistRepository.existsByNomeIgnoreCase(novaPlaylist.getNome())) {
            return null;
        }

        existente.setNome(novaPlaylist.getNome());
        existente.setPublica(novaPlaylist.getPublica());

        return playlistRepository.save(existente);
    }

    public Playlist deletarPlaylist(Long id) {
        Playlist playlist = playlistRepository.findById(id).orElse(null);

        if (playlist == null || Boolean.FALSE.equals(playlist.getAtivo())) {
            return null;
        }

        playlist.setAtivo(false);
        return playlistRepository.save(playlist);
    }

    public Playlist adicionarMusicaNaPlaylist(Long playlistId, Long musicaId, Long usuarioId) {
        Playlist playlist = buscarPorId(playlistId);

        if (playlist == null || Boolean.FALSE.equals(playlist.getAtivo())) {
            return null;
        }

        if (playlist.getUsuario() == null || !playlist.getUsuario().getId().equals(usuarioId)) {
            return null;
        }

        Musica musica = musicaService.buscarPorId(musicaId);

        if (musica == null || Boolean.FALSE.equals(musica.getAtivo())) {
            return null;
        }

        if (playlist.getMusicas() == null) {
            playlist.setMusicas(new ArrayList<>());
        }

        for (Musica musicaDaPlaylist : playlist.getMusicas()) {
            if (musicaDaPlaylist.getId().equals(musicaId)) {
                return null;
            }
        }

        playlist.getMusicas().add(musica);
        return playlistRepository.save(playlist);
    }
}