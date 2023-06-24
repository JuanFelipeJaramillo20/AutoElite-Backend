package com.autoelite.AutoElite.Publicacion;

import com.autoelite.AutoElite.Carro.Carro;
import com.autoelite.AutoElite.errores.ErrorMessage;
import com.autoelite.AutoElite.security.ConfirmationMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/publicaciones")
public class PublicacionController {
    private final PublicacionService publicacionService;

    public PublicacionController(PublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }

    @GetMapping
    public ResponseEntity<?> getPublicaciones() {
        try {
            return ResponseEntity.ok(publicacionService.getAllPublicaciones());
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("Publicaciones no encontradas"));
        }
    }

    @GetMapping("{idPublicaciones}")
    public ResponseEntity<?> getPublicacionesById(@PathVariable("idPublicaciones") String id) {
        try {
            return ResponseEntity.ok(publicacionService.getPublicacionesById(id));
        }catch (NullPointerException e){
            String errorMessage = "Publicación no encontrada con id: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(errorMessage));
        }
    }

    @PostMapping
    public ResponseEntity<Void> addPublicacion(@RequestBody PublicacionRequest publicacionRequest) {
        publicacionService.addPublicacion(publicacionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @DeleteMapping("{idPublicaciones}")
    public ResponseEntity<?> deletePublicaciones(@PathVariable("idPublicaciones") String id) {
        if (publicacionService.existsPublicacion(id)) {
            publicacionService.deletePublicaciones(id);
            return ResponseEntity.ok(new ConfirmationMessage("Publicación con ID " + id + " ha sido eliminada exitosamente"));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("La publicación con el ID " + id + " no existe"));
        }
    }

    @PutMapping("{idPublicaciones}")
    public ResponseEntity<?> updatePublicaciones(@PathVariable("idPublicaciones") String id, @RequestBody Publicacion publicacion) {
        try {
            publicacionService.updatePublicaciones(id, publicacion);
            return ResponseEntity.ok(new ConfirmationMessage("Publicación actualizada exitosamente"));
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("La publicación con el ID " + id + " no existe"));
        }
    }

    @GetMapping("/byuseremail/{userEmail}")
    public ResponseEntity<?> getPublicacionesByUserEmail(@PathVariable("userEmail") String email) {
        try {
            return ResponseEntity.ok(publicacionService.getPublicacionesByEmail(email));
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("No se encontraron publicaciones para el email: " + email));
        }
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<?> getPublicacionesByUserId(@PathVariable("userId") String userId) {
        try {
            return ResponseEntity.ok(publicacionService.getPublicacionesByUserId(userId));
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("No se encontraron publicaciones para el ID de usuario: " + userId));
        }
    }

}
