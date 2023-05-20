package com.autoelite.AutoElite.publicaciones;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicacionesService {
    private final PublicacionesDAO publicacionesDAO;

    public PublicacionesService(PublicacionesDAO publicacionesDAO) {
        this.publicacionesDAO = publicacionesDAO;
    }

    public List<Publicaciones> getAllPublicaciones() {
        return publicacionesDAO.findAll();
    }

    public void deletePublicaciones(String id) {
        publicacionesDAO.deleteById(id);
    }


    public Publicaciones getPublicacionesById(String id) {
        return publicacionesDAO.findById(id).orElseThrow(()->new RuntimeException("Publicacion no encontrada con id: "+id));
    }

    public void addPublicaciones(Publicaciones publicaciones){
        publicacionesDAO.save(publicaciones);
    }
    public void updatePublicaciones(String id, Publicaciones publicaciones) {
        Optional<Publicaciones> existingPublicaciones = publicacionesDAO.findById(id);
        if (existingPublicaciones.isPresent()){
            if (publicaciones.getCiudad() != null && !publicaciones.getCiudad().isEmpty()){
                existingPublicaciones.get().setCiudad(publicaciones.getCiudad());
            }
            if (publicaciones.getFechaPublicacion() != null && !publicaciones.getFechaPublicacion().isEmpty()){
                existingPublicaciones.get().setFechaPublicacion(publicaciones.getFechaPublicacion());
            }
        }else{
            throw new RuntimeException("Publicacion no encontrada: "+id);
        }
    }

}
