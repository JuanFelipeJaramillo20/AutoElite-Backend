package com.autoelite.AutoElite.Usuarios;

import com.autoelite.AutoElite.Publicacion.Publicacion;
import com.autoelite.AutoElite.Publicacion.PublicacionDAO;
import com.autoelite.AutoElite.security.SecurityConfigurations;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioDAO usuarioDAO;
    private final PublicacionDAO publicacionDAO;
    @Autowired
    private SecurityConfigurations securityConfigurations;
    @PersistenceContext
    private EntityManager entityManager;

    public UsuarioService(UsuarioDAO usuarioDAO, PublicacionDAO publicacionDAO) {
        this.usuarioDAO = usuarioDAO;
        this.publicacionDAO = publicacionDAO;
    }

    public List<Usuario> getAllUsuarios() {
        List<Usuario> usuarios = usuarioDAO.findAll();
        if (usuarios.isEmpty()) {
            throw new NullPointerException();
        } else {
            return usuarioDAO.findAll();
        }
    }

    public Usuario getUsuarioById(String id) {
        Usuario usuario = usuarioDAO.findById(id)
                .orElseThrow(() -> new NullPointerException());

        return usuario;
    }


    public void addUsuario(Usuario usuario) {
        usuarioDAO.save(usuario);
    }

    @Transactional
    public void deleteUsuario(String id) {
        try {
            String sql = "DELETE FROM calificacion WHERE sender = :id OR receiver = :id";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("id", Long.parseLong(id));
            query.executeUpdate();
            usuarioDAO.deleteById(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean existsUsuario(String id) {
        return usuarioDAO.existsById(id);
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
            if (usuario.getImagenPerfil() != null) {
                existingUsuario.get().setImagenPerfil(usuario.getImagenPerfil());
            }
            usuarioDAO.save(existingUsuario.get());

        } else {
            throw new NullPointerException();
        }
    }

    public void setImagenPerfilUsuario(String id, String imagen) {
        Optional<Usuario> user = usuarioDAO.findById(id);
        if (user.isPresent()) {
            user.get().setImagenPerfil(imagen);
            usuarioDAO.save(user.get());
        }
    }

    public void addToFavorites(String usuarioId, String publicacionId) {
        Optional<Usuario> optionalUsuario = usuarioDAO.findById(usuarioId);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            Optional<Publicacion> optionalPublicacion = publicacionDAO.findById(publicacionId);
            if (optionalPublicacion.isPresent()) {

                List<Publicacion> favorites = usuario.getPublicacionesFavoritas();
                if (favorites == null) {
                    favorites = new ArrayList<>();
                }

                Publicacion publicacion = optionalPublicacion.get();
                favorites.add(publicacion);

                usuario.setPublicacionesFavoritas(favorites);
                usuarioDAO.save(usuario);
            } else {
                throw new RuntimeException("Publicacion not found: " + publicacionId);
            }
        } else {
            throw new RuntimeException("Usuario not found: " + usuarioId);
        }
    }

    public Publicacion removeFromFavorites(String usuarioId, String publicacionId) {
        try {
            Usuario usuario = getUsuarioById(usuarioId);
            Publicacion publicacion = publicacionDAO.getById(publicacionId);
            if (usuario == null) {
                throw new NullPointerException("No se encuentra el usuario.");
            }
            if (publicacion == null) {
                throw new NullPointerException("No se encuentra la publicación.");
            }
            if (usuario.getPublicacionesFavoritas().contains(publicacion)) {
                usuario.getPublicacionesFavoritas().remove(publicacion);
                usuarioDAO.save(usuario);
            }
            return publicacion;
        } catch (NullPointerException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
