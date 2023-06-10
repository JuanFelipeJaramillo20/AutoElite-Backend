package com.autoelite.AutoElite.login;

import com.autoelite.AutoElite.Usuarios.Usuario;
import com.autoelite.AutoElite.Usuarios.UsuarioDAO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "usuario con email %s no encontrado";
    
    @Autowired
    private  UsuarioDAO usuarioDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioDAO.findByEmail(email)
                .orElseThrow(() ->
                    new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG, email)));
    }
}
