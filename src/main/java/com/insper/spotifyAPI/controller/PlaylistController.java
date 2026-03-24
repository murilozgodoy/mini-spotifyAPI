package com.insper.spotifyAPI.controller;

import com.insper.spotifyAPI.model.Playlist;
import com.insper.spotifyAPI.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @PostMapping
    public Playlist criarPlaylist(@RequestBody Playlist playlist) {
        return playlistService.criarPlaylist(playlist);
    }

    @GetMapping
    public List<Playlist> listarPlaylists() {
        return playlistService.listarPlaylists();
    }

    @GetMapping("/{id}")
    public Playlist buscarPorId(@PathVariable Long id) {
        return playlistService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Playlist atualizarPlaylist(@PathVariable Long id, @RequestBody Playlist playlist) {
        return playlistService.atualizarPlaylist(id, playlist);
    }

    @DeleteMapping("/{id}")
    public Playlist deletarPlaylist(@PathVariable Long id) {
        return playlistService.deletarPlaylist(id);
    }

    @PostMapping("/{playlistId}/musicas/{musicaId}")
    public Playlist adicionarMusicaNaPlaylist(
            @PathVariable Long playlistId,
            @PathVariable Long musicaId,
            @RequestHeader("X-USER-ID") Long usuarioId) {
        return playlistService.adicionarMusicaNaPlaylist(playlistId, musicaId, usuarioId);
    }
}