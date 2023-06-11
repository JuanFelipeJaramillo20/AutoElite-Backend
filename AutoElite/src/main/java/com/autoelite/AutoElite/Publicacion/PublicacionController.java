package com.autoelite.AutoElite.Publicacion;

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
    public List<Publicacion> getPublicaciones(){
        return publicacionService.getAllPublicaciones();
    }

    @GetMapping("{idPublicaciones}")
    public Publicacion getPublicacionesById(@PathVariable("idPublicaciones") String id){
        return publicacionService.getPublicacionesById(id);
    }

    @PostMapping
    public void addPublicaciones(@RequestBody Publicacion publicacion){
        publicacionService.addPublicaciones(publicacion);
    }

    @DeleteMapping("{idPublicaciones}")
    public void deletePublicaciones(@PathVariable("idPublicaciones") String id) {
        publicacionService.deletePublicaciones(id);
    }

    @PutMapping("{idPublicaciones}")
    public void updatePublicaciones(@PathVariable("idPublicaciones") String id, @RequestBody Publicacion publicacion) {
        publicacionService.updatePublicaciones(id, publicacion);
    }

    @GetMapping("/byuser/{userEmail}")
    public void getPublicacionesByUserEmail(@PathVariable("userEmail") String email) {
        publicacionService.getPublicacionesByEmail(email);
    }
}
