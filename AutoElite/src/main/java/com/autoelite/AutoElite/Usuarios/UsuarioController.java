package com.autoelite.AutoElite.Usuarios;

//import com.autoelite.AutoElite.security.TokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    //@Autowired
    private AuthenticationManager authenticationManager;

   // @Autowired
    //private TokenServices tokenServices;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("{idUsuario}")
    public Usuario getUsuarioById(@PathVariable("idUsuario") String id) {
        return usuarioService.getUsuarioById(id);
    }

    @PostMapping
    /*public void addUsuario(@RequestBody Usuario usuario) {
        usuarioService.addUsuario(usuario);
    }*/
    public ResponseEntity autenticarUsuario(@RequestBody Usuario usuario){
        Authentication authToken = new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getContrasena());
        authenticationManager.authenticate(authToken);
        //var JWTtoken = tokenServices.generarToken();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{idUsuario}")
    public void deleteUsuario(@PathVariable("idUsuario") String id) {
        usuarioService.deleteUsuario(id);
    }

    @PutMapping("{idUsuario}")
    public void updateUsuario(@PathVariable("idUsuario") String id, @RequestBody Usuario usuario) {
        usuarioService.updateUsuario(id, usuario);
    }

}

