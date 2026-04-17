package com.insper.spotifyAPI.service;

import com.insper.spotifyAPI.model.Usuario;
import com.insper.spotifyAPI.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario criarUsuario(Usuario usuario) {
        if (usuario == null || usuario.getNome() == null || usuario.getNome().isBlank()
                || usuario.getEmail() == null || usuario.getEmail().isBlank()
                || usuario.getTipoPlano() == null) {
            return null;
        }

        if (usuarioRepository.existsByEmailIgnoreCase(usuario.getEmail())) {
            return null;
        }

        usuario.setId(null);
        usuario.setAtivo(true);
        usuario.setDataCriacao(LocalDateTime.now());

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findByAtivoTrue();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .filter(usuario -> Boolean.TRUE.equals(usuario.getAtivo()))
                .orElse(null);
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);

        if (usuarioExistente == null || Boolean.FALSE.equals(usuarioExistente.getAtivo())) {
            return null;
        }

        if (usuarioAtualizado == null || usuarioAtualizado.getNome() == null || usuarioAtualizado.getNome().isBlank()
                || usuarioAtualizado.getEmail() == null || usuarioAtualizado.getEmail().isBlank()
                || usuarioAtualizado.getTipoPlano() == null) {
            return null;
        }

        if (!usuarioExistente.getEmail().equalsIgnoreCase(usuarioAtualizado.getEmail())
                && usuarioRepository.existsByEmailIgnoreCase(usuarioAtualizado.getEmail())) {
            return null;
        }

        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setTipoPlano(usuarioAtualizado.getTipoPlano());

        return usuarioRepository.save(usuarioExistente);
    }

    public Usuario deletarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if (usuario == null || Boolean.FALSE.equals(usuario.getAtivo())) {
            return null;
        }

        usuario.setAtivo(false);
        return usuarioRepository.save(usuario);
    }
}