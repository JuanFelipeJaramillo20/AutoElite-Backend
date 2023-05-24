package com.autoelite.AutoElite.publicaciones;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/publicaciones")
public class PublicacionesController {
    private final PublicacionesService publicacionesService;

    public PublicacionesController(PublicacionesService publicacionesService) {
        this.publicacionesService = publicacionesService;
    }

    @GetMapping
    public List<Publicaciones> getPublicaciones(){
        return publicacionesService.getAllPublicaciones();
    }

    @GetMapping("{idPublicaciones}")
    public Publicaciones getPublicacionesById(@PathVariable("idPublicaciones") String id){
        return publicacionesService.getPublicacionesById(id);
    }

    @PostMapping
    public void addPublicaciones(@RequestBody Publicaciones publicaciones){
        publicacionesService.addPublicaciones(publicaciones);
    }

    @DeleteMapping("{idPublicaciones}")
    public void deletePublicaciones(@PathVariable("idPublicaciones") String id){
        publicacionesService.deletePublicaciones(id);
    }

    @PutMapping("{idPublicaciones}")
    public void updatePublicaciones(@PathVariable("idPublicaciones") String id, @RequestBody Publicaciones publicaciones){
        publicacionesService.updatePublicaciones(id, publicaciones);
    }

}
