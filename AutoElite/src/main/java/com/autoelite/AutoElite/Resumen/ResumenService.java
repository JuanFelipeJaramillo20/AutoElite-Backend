package com.autoelite.AutoElite.Resumen;

import com.autoelite.AutoElite.Publicacion.Publicacion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResumenService {

    @PersistenceContext
    private EntityManager entityManager;
    public List<Resumen> getAllSummaries() {
        TypedQuery<Publicacion> query = entityManager.createQuery(
                "SELECT p FROM Publicacion p", Publicacion.class);

        List<Publicacion> publications = query.getResultList();
        List<Resumen> summaries = new ArrayList<>();

        for (Publicacion publication : publications) {
            Resumen summary = new Resumen();
            summary.setIdPublicacion(publication.getId());
            summary.setCarroPublicacion(publication.getCarroPublicacion());
            summaries.add(summary);
        }

        return summaries;
    }
}
