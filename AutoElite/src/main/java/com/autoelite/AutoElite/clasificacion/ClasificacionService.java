package com.autoelite.AutoElite.clasificacion;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClasificacionService {
    private final ClasificacionDAO clasificacionDAO;

    public ClasificacionService(ClasificacionDAO clasificacionDAO) {
        this.clasificacionDAO = clasificacionDAO;
    }

    public List<Clasificacion> getAllClasificacion() {
        return clasificacionDAO.findAll();
    }

    public Clasificacion getClasificacionById(String id) {
        return clasificacionDAO.findById(id).orElseThrow(() -> new RuntimeException("clasificacion not found with id: " + id));
    }

    public void addClasificacion(Clasificacion clasificacion) {
        clasificacionDAO.save(clasificacion);
    }

    public void deleteClasificacion(String id) {
        clasificacionDAO.deleteById(id);
    }

    public void updateClasificacion(String id, Clasificacion clasificacion) {
        Optional<Clasificacion> existingClasificacion = clasificacionDAO.findById(id);
        if (existingClasificacion.isPresent()) {
            if (clasificacion.getFecha() != null && !clasificacion.getFecha().isEmpty()) {
                existingClasificacion.get().setFecha(clasificacion.getFecha());
            }
            if (clasificacion.getComentarios() != null && !clasificacion.getComentarios().isEmpty()) {
                existingClasificacion.get().setComentarios(clasificacion.getComentarios());
            }
            if (clasificacion.getNumEstrellas() != 0) {
                existingClasificacion.get().setNumEstrellas(clasificacion.getNumEstrellas());
            }

            clasificacionDAO.save(existingClasificacion.get());
        } else {
            throw new RuntimeException("clasificacion no encontrado: " + id);
        }
    }
}
