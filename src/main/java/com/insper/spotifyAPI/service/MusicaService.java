package com.insper.spotifyAPI.service;

import com.insper.spotifyAPI.dto.TopMusicaDTO;
import com.insper.spotifyAPI.model.Album;
import com.insper.spotifyAPI.model.Artista;
import com.insper.spotifyAPI.model.Musica;
import com.insper.spotifyAPI.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MusicaService {

    private final Map<Long, Musica> musicas = new HashMap<>();
    private Long proximoId = 1L;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private ArtistaService artistaService;

    @Autowired
    private UsuarioService usuarioService;

    public Musica criarMusica(Musica musica) {
        if (musica.getAlbum() == null || musica.getAlbum().getId() == null) {
            return null;
        }

        if (musica.getArtista() == null || musica.getArtista().getId() == null) {
            return null;
        }

        Album album = albumService.buscarPorId(musica.getAlbum().getId());
        Artista artista = artistaService.buscarPorId(musica.getArtista().getId());

        if (album == null || Boolean.FALSE.equals(album.getAtivo())) {
            return null;
        }

        if (artista == null || Boolean.FALSE.equals(artista.getAtivo())) {
            return null;
        }

        musica.setId(proximoId++);
        musica.setAlbum(album);
        musica.setArtista(artista);
        musica.setTotalReproducoes(0L);
        musica.setAtivo(true);

        musicas.put(musica.getId(), musica);
        return musica;
    }

    public List<Musica> listarMusicas() {
        return new ArrayList<>(musicas.values());
    }

    public Musica buscarPorId(Long id) {
        return musicas.get(id);
    }

    public Musica atualizarMusica(Long id, Musica musicaAtualizada) {
        Musica musicaExistente = musicas.get(id);

        if (musicaExistente == null) {
            return null;
        }

        musicaExistente.setTitulo(musicaAtualizada.getTitulo());
        musicaExistente.setDuracaoSegundos(musicaAtualizada.getDuracaoSegundos());
        musicaExistente.setNumeroFaixa(musicaAtualizada.getNumeroFaixa());

        return musicaExistente;
    }

    public Musica deletarMusica(Long id) {
        Musica musica = musicas.get(id);

        if (musica == null) {
            return null;
        }

        musica.setAtivo(false);
        return musica;
    }

    public Musica reproduzirMusica(Long musicaId, Long usuarioId) {
        Musica musica = musicas.get(musicaId);

        if (musica == null || Boolean.FALSE.equals(musica.getAtivo())) {
            return null;
        }

        Usuario usuario = usuarioService.buscarPorId(usuarioId);

        if (usuario == null || Boolean.FALSE.equals(usuario.getAtivo())) {
            return null;
        }

        musica.setTotalReproducoes(musica.getTotalReproducoes() + 1);
        return musica;
    }

    public List<TopMusicaDTO> topMusicasMaisReproduzidas() {
        return musicas.values()
                .stream()
                .filter(musica -> !Boolean.FALSE.equals(musica.getAtivo()))
                .sorted(Comparator.comparing(Musica::getTotalReproducoes).reversed())
                .limit(10)
                .map(musica -> new TopMusicaDTO(
                        musica.getTitulo(),
                        musica.getArtista().getNome(),
                        musica.getTotalReproducoes()
                ))
                .collect(Collectors.toList());
    }
}