package com.insper.spotifyAPI.controller;

import com.insper.spotifyAPI.model.Artista;
import com.insper.spotifyAPI.service.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artistas")
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    @PostMapping
    public Artista criarArtista(@RequestBody Artista artista) {
        return artistaService.criarArtista(artista);
    }

    @GetMapping
    public List<Artista> listarArtistas() {
        return artistaService.listarArtistas();
    }

    @GetMapping("/{id}")
    public Artista buscarPorId(@PathVariable Long id) {
        return artistaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Artista atualizarArtista(@PathVariable Long id, @RequestBody Artista artista) {
        return artistaService.atualizarArtista(id, artista);
    }

    @DeleteMapping("/{id}")
    public Artista deletarArtista(@PathVariable Long id) {
        return artistaService.deletarArtista(id);
    }
}