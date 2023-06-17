package com.autoelite.AutoElite.Publicacion;

import com.autoelite.AutoElite.Carro.Carro;
import com.autoelite.AutoElite.Carro.CarroDAO;
import com.autoelite.AutoElite.Usuarios.Usuario;
import com.autoelite.AutoElite.Usuarios.UsuarioDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;

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
        }else {
            return publicacionDAO.findAll();
        }
    }

    public void deletePublicaciones(String id) {
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
            if (publicacion.getFechaPublicacion() != null && !publicacion.getFechaPublicacion().isEmpty()){
                existingPublicaciones.get().setFechaPublicacion(publicacion.getFechaPublicacion());
            }
        }else{
            throw new NullPointerException();
        }
    }

}
