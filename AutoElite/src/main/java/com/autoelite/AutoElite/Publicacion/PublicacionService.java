package com.autoelite.AutoElite.Publicacion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicacionService {
    private final PublicacionDAO publicacionDAO;
    @PersistenceContext
    private EntityManager entityManager;

    public PublicacionService(PublicacionDAO publicacionDAO) {
        this.publicacionDAO = publicacionDAO;
    }

    public List<Publicacion> getAllPublicaciones() {
        return publicacionDAO.findAll();
    }

    public void deletePublicaciones(String id) {
        publicacionDAO.deleteById(id);
    }


    public Publicacion getPublicacionesById(String id) {
        return publicacionDAO.findById(id).orElseThrow(() -> new RuntimeException("Publicacion no encontrada con id: " + id));
    }

    public List<Publicacion> getPublicacionesByEmail(String email) {
        String queryString = "SELECT p FROM Publicacion p JOIN p.usuarioPublicacion u WHERE u.email = :userEmail";
        return entityManager.createQuery(queryString, Publicacion.class)
                .setParameter("userEmail", email)
                .getResultList();
    }

    public void addPublicaciones(Publicacion publicacion) {
        publicacionDAO.save(publicacion);
    }

    public void updatePublicaciones(String id, Publicacion publicacion) {
        Optional<Publicacion> existingPublicaciones = publicacionDAO.findById(id);
        if (existingPublicaciones.isPresent()) {
            if (publicacion.getCiudad() != null && !publicacion.getCiudad().isEmpty()) {
                existingPublicaciones.get().setCiudad(publicacion.getCiudad());
            }
            if (publicacion.getFechaPublicacion() != null && !publicacion.getFechaPublicacion().isEmpty()){
                existingPublicaciones.get().setFechaPublicacion(publicacion.getFechaPublicacion());
            }
        }else{
            throw new RuntimeException("Publicacion no encontrada: "+id);
        }
    }

}
