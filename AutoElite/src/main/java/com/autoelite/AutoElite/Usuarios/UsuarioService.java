package com.autoelite.AutoElite.Usuarios;

import com.autoelite.AutoElite.Publicacion.Publicacion;
import com.autoelite.AutoElite.Publicacion.PublicacionDAO;
import com.autoelite.AutoElite.security.SecurityConfigurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService{

    private final UsuarioDAO usuarioDAO;
    private final PublicacionDAO publicacionDAO;

    @Autowired
    private SecurityConfigurations securityConfigurations;

    public UsuarioService(UsuarioDAO usuarioDAO, PublicacionDAO publicacionDAO) {
        this.usuarioDAO = usuarioDAO;
        this.publicacionDAO = publicacionDAO;
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
            if (usuario.getImagenPerfil() != null) {
                existingUsuario.get().setImagenPerfil(usuario.getImagenPerfil());
            }
            usuarioDAO.save(existingUsuario.get());
        } else {
            throw new RuntimeException("Usuario no encontrado: " + id);
        }
    }

    public void setImagenPerfilUsuario(String id, MultipartFile imagen) {
        if (!imagen.isEmpty()) {
            try {
                Byte[] byteArrayWrapper = new Byte[imagen.getBytes().length];
                for (int i = 0; i < imagen.getBytes().length; i++) {
                    byteArrayWrapper[i] = Byte.valueOf(imagen.getBytes()[i]);
                }
                Optional<Usuario> user = usuarioDAO.findById(id);
                if (user.isPresent()) {
                    user.get().setImagenPerfil(byteArrayWrapper);
                    usuarioDAO.save(user.get());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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

    public void updateToken(Usuario usuario, String token) {
        Optional<Usuario> userByEmail = usuarioDAO.findByEmail(usuario.getEmail());
        if (userByEmail.isPresent()) {
            userByEmail.get().setToken(token);
        }
        usuarioDAO.save(userByEmail.get());
    }


}
