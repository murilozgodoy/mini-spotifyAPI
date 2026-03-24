package com.insper.spotifyAPI.controller;

import com.insper.spotifyAPI.model.Album;
import com.insper.spotifyAPI.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albuns")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping
    public Album criarAlbum(@RequestBody Album album) {
        return albumService.criarAlbum(album);
    }

    @GetMapping
    public List<Album> listarAlbuns() {
        return albumService.listarAlbuns();
    }

    @GetMapping("/{id}")
    public Album buscarPorId(@PathVariable Long id) {
        return albumService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Album atualizarAlbum(@PathVariable Long id, @RequestBody Album album) {
        return albumService.atualizarAlbum(id, album);
    }

    @DeleteMapping("/{id}")
    public Album deletarAlbum(@PathVariable Long id) {
        return albumService.deletarAlbum(id);
    }
}