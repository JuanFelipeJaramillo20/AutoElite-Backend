package com.autoelite.AutoElite.Calificacion;

import com.autoelite.AutoElite.Usuarios.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/calificacion")
public class CalificacionController {
    private final CalificacionService calificacionService;

    public CalificacionController(CalificacionService calificacionService) {
        this.calificacionService = calificacionService;
    }

    @GetMapping("{idReceiver}")
    public ResponseEntity<?> getAllCalificacion(@PathVariable("idReceiver") Integer id) {
        Usuario receiver = new Usuario();
        receiver.setId(id);
        return calificacionService.getAllCalificacion(receiver);
    }

    @PostMapping
    public void addCalificacion(@RequestBody Calificacion calificacion) {
        calificacionService.addCalificacion(calificacion);
    }

    @DeleteMapping("{idClasificacion}")
    public void deleteClasificacion(@PathVariable("idClasificacion") String id) {
        calificacionService.deleteClasificacion(id);
    }

}
