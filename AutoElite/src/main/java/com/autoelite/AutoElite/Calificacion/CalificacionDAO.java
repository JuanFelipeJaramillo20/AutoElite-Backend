package com.autoelite.AutoElite.Calificacion;

import com.autoelite.AutoElite.Usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalificacionDAO extends JpaRepository<Calificacion, String> {
    /*@Query("SELECT COUNT(*) from Calificacion c WHERE c.receiver = ?1")*/
    List<Calificacion> findByReceiver(Usuario receiver);

    /*@Query("SELECT COUNT(*) from Calificacion c WHERE c.sender = ?1")*/
    /*Optional<Calificacion> findBySender(String id);*/

    /*@Query()
    List<Calificacion> findByAllComents(Integer id);*/
}
