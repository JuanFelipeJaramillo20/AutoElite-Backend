package com.autoelite.AutoElite.registro;

import com.autoelite.AutoElite.Usuarios.Usuario;
import com.autoelite.AutoElite.Usuarios.UsuarioDAO;
import com.autoelite.AutoElite.Usuarios.UsuarioService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UsuarioService usuarioService;

    private final UsuarioDAO usuarioDAO;

    public void addRegister(Usuario usuario){

        usuarioDAO.save(usuario);
    }
}
