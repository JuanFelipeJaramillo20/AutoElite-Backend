package com.autoelite.AutoElite.Calificacion;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clasificacion")
public class CalificacionController {
    private final CalificacionService calificacionService;

    public CalificacionController(CalificacionService calificacionService) {
        this.calificacionService = calificacionService;
    }

    @GetMapping
    public List<Calificacion> getClasificacions() {
        return calificacionService.getAllClasificacion();
    }

    @GetMapping("{idClasificacion}")
    public Calificacion getClasificacionById(@PathVariable("idClasificacion") String id) {
        return calificacionService.getClasificacionById(id);
    }

    @PostMapping
    public void addClasificacion(@RequestBody Calificacion clasificacion) {
        calificacionService.addClasificacion(clasificacion);
    }

    @DeleteMapping("{idClasificacion}")
    public void deleteClasificacion(@PathVariable("idClasificacion") String id) {
        calificacionService.deleteClasificacion(id);
    }

    @PutMapping("{idClasificacion}")
    public void updateClasificacion(@PathVariable("idClasificacion") String id, @RequestBody Calificacion clasificacion) {
        calificacionService.updateClasificacion(id, clasificacion);
    }
}
