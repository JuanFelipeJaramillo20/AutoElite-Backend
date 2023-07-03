package com.autoelite.AutoElite.Calificacion;

import com.autoelite.AutoElite.Usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalificacionDAO extends JpaRepository<Calificacion, String> {

    List<Calificacion> findByReceiver(Usuario receiver);

}
