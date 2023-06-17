package com.autoelite.AutoElite.Usuarios;

import com.autoelite.AutoElite.errores.ErrorMessage;
import com.autoelite.AutoElite.security.ConfirmationMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<?> getUsuarios() {
        try {
            return ResponseEntity.ok(usuarioService.getAllUsuarios());
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("Usuarios no encontrados"));
        }
    }

    @GetMapping("{idUsuario}")
    public ResponseEntity<?> getUsuarioById(@PathVariable("idUsuario") String id) {
        try {
            return ResponseEntity.ok(usuarioService.getUsuarioById(id));
        } catch (NullPointerException e) {
            String errorMessage = "Usuario no encontrado con id: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(errorMessage));
        }
    }

    @PostMapping
    public void addUsuario(@RequestBody Usuario usuario) {
        usuarioService.addUsuario(usuario);
    }

    @DeleteMapping("{idUsuario}")
    public ResponseEntity<?> deleteUsuario(@PathVariable("idUsuario") String id) {
        if (usuarioService.existsUsuario(id)) {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.ok(new ConfirmationMessage("Usuario con ID " + id + " ha sido eliminado exitosamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("El usuario con el ID " + id + " no existe"));
        }
    }


    @PutMapping("{idUsuario}")
    public ResponseEntity<?> updateUsuario(@PathVariable("idUsuario") String id, @RequestBody Usuario usuario) {
        try {
            usuarioService.updateUsuario(id, usuario);
            return ResponseEntity.ok(new ConfirmationMessage("Usuario actualizado exitosamente"));
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("El usuario con el ID " + id + " no existe"));
        }
    }

    @PutMapping("/img/{idUsuario}")
    public void updateImgUsuario(@PathVariable("idUsuario") String id, @RequestBody MultipartFile img) {
        usuarioService.setImagenPerfilUsuario(id, img);
    }

    @PostMapping("/{usuarioId}/favoritos/{publicacionId}")
    public ResponseEntity<?> addToFavorites(
            @PathVariable("usuarioId") String usuarioId,
            @PathVariable("publicacionId") String publicacionId) {
        usuarioService.addToFavorites(usuarioId, publicacionId);
        return ResponseEntity.ok(new ConfirmationMessage("Publicacion added to favorites successfully"));
    }
}

