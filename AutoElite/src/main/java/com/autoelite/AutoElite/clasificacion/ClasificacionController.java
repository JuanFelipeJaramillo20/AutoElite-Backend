package com.autoelite.AutoElite.clasificacion;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clasificacion")
public class ClasificacionController {
    private final ClasificacionService clasificacionService;

    public ClasificacionController(ClasificacionService clasificacionService) {
        this.clasificacionService = clasificacionService;
    }

    @GetMapping
    public List<Clasificacion> getClasificacions() {
        return clasificacionService.getAllClasificacion();
    }

    @GetMapping("{idClasificacion}")
    public Clasificacion getClasificacionById(@PathVariable("idClasificacion") String id) {
        return clasificacionService.getClasificacionById(id);
    }

    @PostMapping
    public void addClasificacion(@RequestBody Clasificacion clasificacion) {
        clasificacionService.addClasificacion(clasificacion);
    }

    @DeleteMapping("{idClasificacion}")
    public void deleteClasificacion(@PathVariable("idClasificacion") String id) {
        clasificacionService.deleteClasificacion(id);
    }

    @PutMapping("{idClasificacion}")
    public void updateClasificacion(@PathVariable("idClasificacion") String id, @RequestBody Clasificacion clasificacion) {
        clasificacionService.updateClasificacion(id, clasificacion);
    }
}
