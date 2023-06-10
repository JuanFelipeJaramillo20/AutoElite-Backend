package com.autoelite.AutoElite.Usuarios;

import com.autoelite.AutoElite.registro.RegistrationService;
import com.autoelite.AutoElite.registro.token.ConfirmationToken;
import com.autoelite.AutoElite.registro.token.ConfirmationTokenService;
import com.autoelite.AutoElite.security.SecurityConfigurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private RegistrationService registrationServices;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private SecurityConfigurations securityConfigurations;

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

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
            if (usuario.getRolUsuario() != null) {
                existingUsuario.get().setRolUsuario(usuario.getRolUsuario());
            }
            usuarioDAO.save(existingUsuario.get());
        } else {
            throw new RuntimeException("Usuario no encontrado: " + id);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioDAO.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(Usuario usuario){
        Optional<Usuario> userByEmail = usuarioDAO.findByEmail(usuario.getEmail());
        if (userByEmail.isPresent()){
            throw new IllegalStateException("Email ya registrado");
        }
        String hashPass = securityConfigurations.passwordEncoder().encode(usuario.getPassword());
        usuario.setContrasena(hashPass);
        usuarioDAO.save(usuario);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                usuario
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

//        TODO: SEND EMAIL

        return token;
    }

    public int isEnableUsuario(String email) {
        return usuarioDAO.isEnableUsuario(email);
    }

}
