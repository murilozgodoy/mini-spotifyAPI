package com.insper.spotifyAPI.service;

import com.insper.spotifyAPI.model.Usuario;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UsuarioService {

    private final Map<Long, Usuario> usuarios = new HashMap<>();
    private Long proximoId = 1L;

    public Usuario criarUsuario(Usuario usuario) {
        usuario.setId(proximoId++);
        usuario.setAtivo(true);
        usuario.setDataCriacao(LocalDateTime.now());

        usuarios.put(usuario.getId(), usuario);
        return usuario;
    }

    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios.values());
    }

    public Usuario buscarPorId(Long id) {
        return usuarios.get(id);
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = usuarios.get(id);

        if (usuarioExistente == null) {
            return null;
        }

        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setTipoPlano(usuarioAtualizado.getTipoPlano());

        return usuarioExistente;
    }

    public Usuario deletarUsuario(Long id) {
        Usuario usuario = usuarios.get(id);

        if (usuario == null) {
            return null;
        }

        usuario.setAtivo(false);
        return usuario;
    }
}