package com.autoelite.AutoElite.Usuarios;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

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
    public void addUsuario(@RequestBody Usuario usuario) {
        usuarioService.addUsuario(usuario);
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

