package com.autoelite.AutoElite.login;

import com.autoelite.AutoElite.Usuarios.Usuario;
import com.autoelite.AutoElite.Usuarios.UsuarioDAO;
import com.autoelite.AutoElite.Usuarios.UsuarioService;
import com.autoelite.AutoElite.errores.ErrorMessage;
import com.autoelite.AutoElite.errores.ForbiddenException;
import com.autoelite.AutoElite.security.ConfirmationMessage;
import com.autoelite.AutoElite.security.DatosJWTToken;
import com.autoelite.AutoElite.security.TokenServices;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/userlogin")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenServices tokenServices;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody LoginRequest loginRequest){
        try {
            Authentication authToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getContrasena());
            var usuarioAutenticado = authenticationManager.authenticate(authToken);
            var JWTtoken = tokenServices.generarToken((Usuario) usuarioAutenticado.getPrincipal());
            return ResponseEntity.ok(new DatosJWTToken(JWTtoken, ((Usuario) usuarioAutenticado.getPrincipal()).getId()));
        }catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( new ErrorMessage("error al iniciar sesión, email o contraseña incorrecta"));
        }
    }

}
