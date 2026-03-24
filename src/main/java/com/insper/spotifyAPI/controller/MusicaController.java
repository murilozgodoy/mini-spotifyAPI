package com.insper.spotifyAPI.controller;

import com.insper.spotifyAPI.model.Musica;
import com.insper.spotifyAPI.service.MusicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    @Autowired
    private MusicaService musicaService;

    @PostMapping
    public Musica criarMusica(@RequestBody Musica musica) {
        return musicaService.criarMusica(musica);
    }

    @GetMapping
    public List<Musica> listarMusicas() {
        return musicaService.listarMusicas();
    }

    @GetMapping("/{id}")
    public Musica buscarPorId(@PathVariable Long id) {
        return musicaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Musica atualizarMusica(@PathVariable Long id, @RequestBody Musica musica) {
        return musicaService.atualizarMusica(id, musica);
    }

    @DeleteMapping("/{id}")
    public Musica deletarMusica(@PathVariable Long id) {
        return musicaService.deletarMusica(id);
    }

    @PostMapping("/{id}/reproduzir")
    public Musica reproduzirMusica(@PathVariable Long id, @RequestHeader("X-USER-ID") Long usuarioId) {
        return musicaService.reproduzirMusica(id, usuarioId);
    }
}