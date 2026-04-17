package com.insper.spotifyAPI.service;

import com.insper.spotifyAPI.dto.TopMusicaDTO;
import com.insper.spotifyAPI.model.Album;
import com.insper.spotifyAPI.model.Artista;
import com.insper.spotifyAPI.model.Musica;
import com.insper.spotifyAPI.model.Usuario;
import com.insper.spotifyAPI.repository.MusicaRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicaService {

    private final MusicaRepository musicaRepository;
    private final AlbumService albumService;
    private final ArtistaService artistaService;
    private final UsuarioService usuarioService;

    public MusicaService(
            MusicaRepository musicaRepository,
            AlbumService albumService,
            ArtistaService artistaService,
            UsuarioService usuarioService
    ) {
        this.musicaRepository = musicaRepository;
        this.albumService = albumService;
        this.artistaService = artistaService;
        this.usuarioService = usuarioService;
    }

    public Musica criarMusica(Musica musica) {
        if (musica == null
                || musica.getTitulo() == null || musica.getTitulo().isBlank()
                || musica.getDuracaoSegundos() == null || musica.getDuracaoSegundos() < 0
                || musica.getNumeroFaixa() == null || musica.getNumeroFaixa() < 0
                || musica.getAlbum() == null || musica.getAlbum().getId() == null
                || musica.getArtista() == null || musica.getArtista().getId() == null) {
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

        if (musicaRepository.existsByTituloIgnoreCase(musica.getTitulo())) {
            return null;
        }

        musica.setId(null);
        musica.setAlbum(album);
        musica.setArtista(artista);
        musica.setTotalReproducoes(0L);
        musica.setAtivo(true);

        return musicaRepository.save(musica);
    }

    public List<Musica> listarMusicas() {
        return musicaRepository.findByAtivoTrue();
    }

    public Musica buscarPorId(Long id) {
        return musicaRepository.findById(id)
                .filter(musica -> Boolean.TRUE.equals(musica.getAtivo()))
                .orElse(null);
    }

    public Musica atualizarMusica(Long id, Musica musicaAtualizada) {
        Musica musicaExistente = musicaRepository.findById(id).orElse(null);

        if (musicaExistente == null || Boolean.FALSE.equals(musicaExistente.getAtivo())) {
            return null;
        }

        if (musicaAtualizada == null
                || musicaAtualizada.getTitulo() == null || musicaAtualizada.getTitulo().isBlank()
                || musicaAtualizada.getDuracaoSegundos() == null || musicaAtualizada.getDuracaoSegundos() < 0
                || musicaAtualizada.getNumeroFaixa() == null || musicaAtualizada.getNumeroFaixa() < 0) {
            return null;
        }

        if (!musicaExistente.getTitulo().equalsIgnoreCase(musicaAtualizada.getTitulo())
                && musicaRepository.existsByTituloIgnoreCase(musicaAtualizada.getTitulo())) {
            return null;
        }

        musicaExistente.setTitulo(musicaAtualizada.getTitulo());
        musicaExistente.setDuracaoSegundos(musicaAtualizada.getDuracaoSegundos());
        musicaExistente.setNumeroFaixa(musicaAtualizada.getNumeroFaixa());

        return musicaRepository.save(musicaExistente);
    }

    public Musica deletarMusica(Long id) {
        Musica musica = musicaRepository.findById(id).orElse(null);

        if (musica == null || Boolean.FALSE.equals(musica.getAtivo())) {
            return null;
        }

        musica.setAtivo(false);
        return musicaRepository.save(musica);
    }

    public Musica reproduzirMusica(Long musicaId, Long usuarioId) {
        Musica musica = buscarPorId(musicaId);

        if (musica == null || Boolean.FALSE.equals(musica.getAtivo())) {
            return null;
        }

        Usuario usuario = usuarioService.buscarPorId(usuarioId);

        if (usuario == null || Boolean.FALSE.equals(usuario.getAtivo())) {
            return null;
        }

        musica.setTotalReproducoes(musica.getTotalReproducoes() + 1);
        return musicaRepository.save(musica);
    }

    public List<TopMusicaDTO> topMusicasMaisReproduzidas() {
        return musicaRepository.findByAtivoTrue()
                .stream()
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