package com.autoelite.AutoElite.login;

import com.autoelite.AutoElite.Usuarios.Usuario;
import com.autoelite.AutoElite.Usuarios.UsuarioService;
import com.autoelite.AutoElite.security.DatosJWTToken;
import com.autoelite.AutoElite.security.TokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/userlogin")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenServices tokenServices;


    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody Usuario usuario){
        Authentication authToken = new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getContrasena());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenServices.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }

}
