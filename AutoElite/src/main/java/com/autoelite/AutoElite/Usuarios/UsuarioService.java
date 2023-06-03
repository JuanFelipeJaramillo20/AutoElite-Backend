package com.autoelite.AutoElite.Usuarios;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioDAO usuarioDAO;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioDAO.findAll();
    }

    public Usuario getUsuarioById(String id) {
        return usuarioDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario not found with id: " + id));
    }

    public void addUsuario(Usuario usuario) {
        usuarioDAO.save(usuario);
    }

    public void deleteUsuario(String id) {
        usuarioDAO.deleteById(id);
    }

    public void updateUsuario(String id, Usuario usuario) {
        Optional<Usuario> existingUsuario = usuarioDAO.findById(id);
        if (existingUsuario.isPresent()) {
            if (usuario.getNombres() != null && !usuario.getNombres().isEmpty()) {
                existingUsuario.get().setNombres(usuario.getNombres());
            }
            if (usuario.getContrasena() != null && !usuario.getContrasena().isEmpty()) {
                existingUsuario.get().setContrasena(usuario.getContrasena());
            }
            if (usuario.getTelefono() != null && !usuario.getTelefono().isEmpty()) {
                existingUsuario.get().setTelefono(usuario.getTelefono());
            }
            if (usuario.getRol() != null) {
                existingUsuario.get().setRol(usuario.getRol());
            }
            usuarioDAO.save(existingUsuario.get());
        } else {
            throw new RuntimeException("Usuario no encontrado: " + id);
        }
    }


}
