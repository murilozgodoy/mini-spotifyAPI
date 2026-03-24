package com.insper.spotifyAPI.service;

import com.insper.spotifyAPI.model.Musica;
import com.insper.spotifyAPI.model.Playlist;
import com.insper.spotifyAPI.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlaylistService {

    private final Map<Long, Playlist> playlists = new HashMap<>();
    private Long proximoId = 1L;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MusicaService musicaService;

    public Playlist criarPlaylist(Playlist playlist) {
        if (playlist.getUsuario() == null || playlist.getUsuario().getId() == null) {
            return null;
        }

        Usuario usuario = usuarioService.buscarPorId(playlist.getUsuario().getId());

        if (usuario == null || Boolean.FALSE.equals(usuario.getAtivo())) {
            return null;
        }

        playlist.setId(proximoId++);
        playlist.setDataCriacao(LocalDateTime.now());
        playlist.setUsuario(usuario);
        playlist.setMusicas(new ArrayList<>());
        playlist.setAtivo(true);

        playlists.put(playlist.getId(), playlist);
        return playlist;
    }

    public List<Playlist> listarPlaylists() {
        return new ArrayList<>(playlists.values());
    }

    public Playlist buscarPorId(Long id) {
        return playlists.get(id);
    }

    public Playlist atualizarPlaylist(Long id, Playlist novaPlaylist) {
        Playlist existente = playlists.get(id);

        if (existente == null) {
            return null;
        }

        existente.setNome(novaPlaylist.getNome());
        existente.setPublica(novaPlaylist.getPublica());

        return existente;
    }

    public Playlist deletarPlaylist(Long id) {
        Playlist playlist = playlists.get(id);

        if (playlist == null) {
            return null;
        }

        playlist.setAtivo(false);
        return playlist;
    }

    public Playlist adicionarMusicaNaPlaylist(Long playlistId, Long musicaId, Long usuarioId) {
        Playlist playlist = playlists.get(playlistId);

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

        for (Musica musicaDaPlaylist : playlist.getMusicas()) {
            if (musicaDaPlaylist.getId().equals(musicaId)) {
                return null;
            }
        }

        playlist.getMusicas().add(musica);
        return playlist;
    }
}