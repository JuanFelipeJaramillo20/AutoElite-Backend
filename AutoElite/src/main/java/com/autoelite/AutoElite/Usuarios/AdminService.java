package com.autoelite.AutoElite.Usuarios;

import com.autoelite.AutoElite.security.SecurityConfigurations;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {

    private final UsuarioDAO usuarioRepository;
    @Autowired
    private SecurityConfigurations securityConfigurations;
    @PersistenceContext
    private EntityManager entityManager;

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

    @Transactional
    public boolean deleteUserAsAdmin(String id) {
        try {
            String sql = "DELETE FROM calificacion WHERE sender = :id OR receiver = :id";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("id", Long.parseLong(id));
            query.executeUpdate();
            Usuario existingUser = usuarioRepository.findById(id).orElse(null);
            if (existingUser != null) {
                usuarioRepository.delete(existingUser);
                return true;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
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

