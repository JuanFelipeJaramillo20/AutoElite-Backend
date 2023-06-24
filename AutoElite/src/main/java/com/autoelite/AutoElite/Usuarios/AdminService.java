package com.autoelite.AutoElite.Usuarios;

import com.autoelite.AutoElite.security.SecurityConfigurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UsuarioDAO usuarioRepository;
    @Autowired
    private SecurityConfigurations securityConfigurations;

    public AdminService(UsuarioDAO usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll();
    }

    public Usuario getUserById(String userId) {
        return usuarioRepository.findById(userId).orElse(null);
    }

    public Usuario updateUserAsAdmin(String userId, Usuario updatedUser) {
        Usuario existingUser = usuarioRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            if (updatedUser.getNombres() != null && !updatedUser.getNombres().isEmpty()) {
                existingUser.setNombres(updatedUser.getNombres());
            }
            if (updatedUser.getContrasena() != null && !updatedUser.getContrasena().isEmpty()) {
                String hashPass = securityConfigurations.passwordEncoder().encode(updatedUser.getContrasena());
                existingUser.setContrasena(hashPass);
            }
            if (updatedUser.getTelefono() != null && !updatedUser.getTelefono().isEmpty()) {
                existingUser.setTelefono(updatedUser.getTelefono());
            }
            if (updatedUser.getRolUsuario() != null) {
                existingUser.setRolUsuario(updatedUser.getRolUsuario());
            }
            if (updatedUser.getImagenPerfil() != null) {
                existingUser.setImagenPerfil(updatedUser.getImagenPerfil());
            }
            return usuarioRepository.save(existingUser);
        }
        return null;
    }

    public boolean deleteUserAsAdmin(String userId) {
        Usuario existingUser = usuarioRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            usuarioRepository.delete(existingUser);
            return true;
        }
        return false;
    }

    public boolean blockUserAsAdmin(String userId) {
        Usuario existingUser = usuarioRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser.setBloqueado(true);
            usuarioRepository.save(existingUser);
            return true;
        }
        return false;
    }
}

