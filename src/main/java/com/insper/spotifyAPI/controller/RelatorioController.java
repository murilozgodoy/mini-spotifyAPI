package com.insper.spotifyAPI.controller;

import com.insper.spotifyAPI.dto.TopMusicaDTO;
import com.insper.spotifyAPI.service.MusicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RelatorioController {

    @Autowired
    private MusicaService musicaService;

    @GetMapping("/relatorios/top-musicas")
    public List<TopMusicaDTO> topMusicasMaisReproduzidas() {
        return musicaService.topMusicasMaisReproduzidas();
    }
}