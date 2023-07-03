package com.autoelite.AutoElite.Publicacion;

import com.autoelite.AutoElite.Carro.Carro;
import com.autoelite.AutoElite.Carro.CarroDAO;
import com.autoelite.AutoElite.Usuarios.Usuario;
import com.autoelite.AutoElite.Usuarios.UsuarioDAO;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PublicacionService {
    private final PublicacionDAO publicacionDAO;
    private final CarroDAO carroDAO;
    private final UsuarioDAO usuarioDAO;
    @PersistenceContext
    private EntityManager entityManager;

    public PublicacionService(PublicacionDAO publicacionDAO, CarroDAO carroDAO, UsuarioDAO usuarioDAO) {
        this.publicacionDAO = publicacionDAO;
        this.carroDAO = carroDAO;
        this.usuarioDAO = usuarioDAO;
    }

    public List<Publicacion> getAllPublicaciones() {
        List<Publicacion> publicaciones = publicacionDAO.findAll();
        if (publicaciones.isEmpty()) {
            throw new NullPointerException();
        } else {
            return publicacionDAO.findAll();
        }
    }

    @Transactional
    public void deletePublicaciones(String id) {
        Publicacion publicacion = getPublicacionesById(id);
        if (publicacion != null) {
            Carro carro = publicacion.getCarroPublicacion();
            if (carro != null) {
                publicacion.setCarroPublicacion(null);
                publicacionDAO.save(publicacion);
                carroDAO.deleteById(carro.getPlaca());
            }
        }
        String sql = "DELETE FROM usuario_publicacion WHERE publicacion_id = :id";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("id", id);
        query.executeUpdate();
        String sql1 = "DELETE FROM reporte WHERE publicacion_id = :id";
        Query query1 = entityManager.createNativeQuery(sql1);
        query1.setParameter("id", id);
        query1.executeUpdate();
        publicacionDAO.deleteById(id);
    }


    public boolean existsPublicacion(String id) {
        return publicacionDAO.existsById(id);
    }

    public Publicacion getPublicacionesById(String id) {
        return publicacionDAO.findById(id).orElseThrow(() -> new NullPointerException());
    }

    public List<Publicacion> getPublicacionesByEmail(String email) {
        String jpql = "SELECT p FROM Publicacion p JOIN p.usuarioPublicacion u WHERE u.email = '" + email + "'";
        TypedQuery<Publicacion> query = entityManager.createQuery(jpql, Publicacion.class);
        List<Publicacion> publicaciones = query.getResultList();
        if (publicaciones.isEmpty()) {
            throw new NullPointerException();
        }
        return publicaciones;
    }

    public List<Publicacion> getPublicacionesByUserId(String userId) {
        Optional<Usuario> usuario = usuarioDAO.findById(userId);

        if (!usuario.isPresent()) {
            throw new NullPointerException("Usuario not found with ID: " + userId);
        }
        String query = "SELECT p FROM Publicacion p WHERE p.usuarioPublicacion.id = :userId";
        TypedQuery<Publicacion> typedQuery = entityManager.createQuery(query, Publicacion.class);
        typedQuery.setParameter("userId", userId);
        return typedQuery.getResultList();
    }

    public void addPublicacion(PublicacionRequest publicacionRequest) {
        Usuario usuario = usuarioDAO.findById(publicacionRequest.getUsuarioId() + "")
                .orElseThrow(() -> new RuntimeException("Usuario not found"));

        Carro carro = new Carro();
        carro.setPuertas(publicacionRequest.getCarro().getPuertas());
        carro.setMotor(publicacionRequest.getCarro().getMotor());
        carro.setCiudad(publicacionRequest.getCarro().getCiudad());
        carro.setMarca(publicacionRequest.getCarro().getMarca());
        carro.setPlaca(publicacionRequest.getCarro().getPlaca());
        carro.setColor(publicacionRequest.getCarro().getColor());
        carro.setTipo(publicacionRequest.getCarro().getTipo());
        carro.setCombustible(publicacionRequest.getCarro().getCombustible());
        carro.setYear(publicacionRequest.getCarro().getYear());
        carro.setEstado(publicacionRequest.getCarro().getEstado());
        carro.setTransmision(publicacionRequest.getCarro().getTransmision());
        carro.setPrecio(publicacionRequest.getCarro().getPrecio());
        carro.setKilometraje(publicacionRequest.getCarro().getKilometraje());
        carro.setPrecioEsNegociable(publicacionRequest.getCarro().getPrecioEsNegociable());
        carro.setImagenes(publicacionRequest.getCarro().getImagenes());
        carroDAO.save(carro);

        Publicacion publicacion = new Publicacion();
        publicacion.setId(publicacionRequest.getId());
        publicacion.setFechaPublicacion(publicacionRequest.getFechaPublicacion());
        publicacion.setCiudad(publicacionRequest.getCiudad());
        publicacion.setUsuarioPublicacion(usuario);
        publicacion.setCarroPublicacion(carro);
        publicacion.setDescripcion(publicacionRequest.getDescripcion());

        publicacionDAO.save(publicacion);
    }

    public void updatePublicaciones(String id, Publicacion publicacion) {
        Optional<Publicacion> existingPublicaciones = publicacionDAO.findById(id);
        if (existingPublicaciones.isPresent()) {
            if (publicacion.getCiudad() != null && !publicacion.getCiudad().isEmpty()) {
                existingPublicaciones.get().setCiudad(publicacion.getCiudad());
            }
            if (publicacion.getFechaPublicacion() != null && !publicacion.getFechaPublicacion().isEmpty()) {
                existingPublicaciones.get().setFechaPublicacion(publicacion.getFechaPublicacion());
            }
        } else {
            throw new NullPointerException();
        }
    }

    public List<Publicacion> getPublicacionesByIds(List<String> ids) {
        return publicacionDAO.findAllById(ids);
    }

    public List<Publicacion> getLastThree() {
        List<Publicacion> publicaciones = publicacionDAO.findAll();
        if (publicaciones.isEmpty()) {
            throw new NullPointerException();
        } else {
            Collections.sort(publicaciones, Comparator.comparing(Publicacion::getFechaPublicacion).reversed());
            List<Publicacion> ultimas3Publicaciones = publicaciones.subList(0, Math.min(3, publicaciones.size()));
            return ultimas3Publicaciones;
        }
    }

}
