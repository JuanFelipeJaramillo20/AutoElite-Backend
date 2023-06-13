package com.autoelite.AutoElite.Usuarios;

import com.autoelite.AutoElite.security.SecurityConfigurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService{

    private final UsuarioDAO usuarioDAO;

    @Autowired
    private SecurityConfigurations securityConfigurations;

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
                String hashPass = securityConfigurations.passwordEncoder().encode(usuario.getContrasena());
                existingUsuario.get().setContrasena(hashPass);
            }
            if (usuario.getTelefono() != null && !usuario.getTelefono().isEmpty()) {
                existingUsuario.get().setTelefono(usuario.getTelefono());
            }
            if (usuario.getRolUsuario() != null) {
                existingUsuario.get().setRolUsuario(usuario.getRolUsuario());
            }

            usuarioDAO.save(existingUsuario.get());
        } else {
            throw new RuntimeException("Usuario no encontrado: " + id);
        }
    }

    public void updateToken(Usuario usuario, String token){
        Optional<Usuario> userByEmail = usuarioDAO.findByEmail(usuario.getEmail());
        if (userByEmail.isPresent()) {
            userByEmail.get().setToken(token);
        }
        usuarioDAO.save(userByEmail.get());
    }


}
