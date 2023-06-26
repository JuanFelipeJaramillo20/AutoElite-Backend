package com.autoelite.AutoElite.reportes;

import com.autoelite.AutoElite.Publicacion.Publicacion;
import com.autoelite.AutoElite.Publicacion.PublicacionDAO;
import com.autoelite.AutoElite.Usuarios.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteService {

    private final ReporteDAO reporteDAO;
    private final PublicacionDAO publicacionDAO;
    @PersistenceContext
    private EntityManager entityManager;

    public ReporteService(ReporteDAO reporteDAO, PublicacionDAO publicacionDAO) {
        this.reporteDAO = reporteDAO;
        this.publicacionDAO = publicacionDAO;
    }


    public List<Reporte> getAllReportes() {
        List<Reporte> reportes = reporteDAO.findAll();
        if (reportes.isEmpty()) {
            throw new NullPointerException();
        }else {
            return reporteDAO.findAll();
        }
    }

    public void deleteReportes(String id){
        reporteDAO.deleteById(id);
    }

    public boolean existsReporte(String id) {
        return reporteDAO.existsById(id);
    }

    public Reporte getReporteById(String id) {
        return reporteDAO.findById(id).orElseThrow(() -> new NullPointerException());
    }

    public List<Reporte> getReporteByIdPublicacion(String idPublicacion) {
        Optional<Publicacion> publicacion = publicacionDAO.findById(idPublicacion);

        if (!publicacion.isPresent()) {
            throw new NullPointerException();
        }
        String query = "SELECT r FROM Reporte r WHERE r.reportePublicacion.id = :idPublicacion";
        TypedQuery<Reporte> typedQuery = entityManager.createQuery(query, Reporte.class);
        typedQuery.setParameter("idPublicacion", idPublicacion);
        return typedQuery.getResultList();
    }

    public void addReporte(ReporteRequest request){
        Publicacion publicacion = publicacionDAO.findById(request.getPublicacionId()).orElseThrow(()->new RuntimeException("Publicacion no encontrada"));
        Reporte reporte = new Reporte();
        reporte.setComentarios(request.getComentarios());
        reporte.setReportePublicacion(publicacion);

        reporteDAO.save(reporte);
    }
}
