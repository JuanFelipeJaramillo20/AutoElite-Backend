package com.autoelite.AutoElite.Calificacion;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalificacionService {
    private final CalificacionDAO calificacionDAO;

    public CalificacionService(CalificacionDAO calificacionDAO) {
        this.calificacionDAO = calificacionDAO;
    }

    public List<Calificacion> getAllClasificacion() {
        return calificacionDAO.findAll();
    }

    public Calificacion getClasificacionById(String id) {
        return calificacionDAO.findById(id).orElseThrow(() -> new RuntimeException("clasificacion not found with id: " + id));
    }

    public void addClasificacion(Calificacion clasificacion) {
        calificacionDAO.save(clasificacion);
    }

    public void deleteClasificacion(String id) {
        calificacionDAO.deleteById(id);
    }

    public void updateClasificacion(String id, Calificacion clasificacion) {
        Optional<Calificacion> existingClasificacion = calificacionDAO.findById(id);
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

            calificacionDAO.save(existingClasificacion.get());
        } else {
            throw new RuntimeException("clasificacion no encontrado: " + id);
        }
    }
}
