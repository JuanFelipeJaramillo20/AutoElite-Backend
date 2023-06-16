package com.autoelite.AutoElite.registro;

import com.autoelite.AutoElite.Usuarios.Usuario;
import com.autoelite.AutoElite.Usuarios.UsuarioDAO;
import com.autoelite.AutoElite.Usuarios.UsuarioService;
import com.autoelite.AutoElite.security.SecurityConfigurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/registro")
public class RegistrationController {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SecurityConfigurations securityConfigurations;


    @PostMapping
    public void register(@RequestBody RegistrationRequest request) {
        Optional<Usuario> userByEmail = usuarioDAO.findByEmail(request.getEmail());
        if (userByEmail.isPresent()){
            throw new IllegalStateException("Email ya registrado");
        }
        String hashPass = securityConfigurations.passwordEncoder().encode(request.getContrasena());
        var usuario = new Usuario();
        usuario.setContrasena(hashPass);
        usuario.setNombres(request.getNombres());
        usuario.setEmail(request.getEmail());
        usuario.setTelefono(request.getTelefono());
        usuario.setRolUsuario(request.getRolUsuario());
        usuarioService.addUsuario(usuario);
    }

}
