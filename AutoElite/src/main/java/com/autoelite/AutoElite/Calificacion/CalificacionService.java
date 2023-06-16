package com.autoelite.AutoElite.Calificacion;

import com.autoelite.AutoElite.Usuarios.Usuario;
import com.autoelite.AutoElite.Usuarios.UsuarioDAO;
import com.autoelite.AutoElite.errores.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CalificacionService {
    private final CalificacionDAO calificacionDAO;
    private final UsuarioDAO usuarioDAO;

    public CalificacionService(CalificacionDAO calificacionDAO, UsuarioDAO usuarioDAO) {
        this.calificacionDAO = calificacionDAO;
        this.usuarioDAO = usuarioDAO;
    }

    public ResponseEntity<?> getAllCalificacion(Usuario usuario) {
        List<Calificacion> findReceiver = calificacionDAO.findByReceiver(usuario);
       if (findReceiver.isEmpty()){
           ErrorMessage mensaje = new ErrorMessage("calificaciones no encontradas");
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
       }
        return ResponseEntity.ok().body(findReceiver);
    }

    public void addCalificacion(Calificacion calificacion) {
        /*Optional<Usuario> findReceiver = usuarioDAO.findById(calificacion.getReceiver().getId() + "");
        Optional<Usuario> findSender = usuarioDAO.findById(calificacion.getSender().getId() + "");
        if (findReceiver.isEmpty() || findSender.isEmpty()){
            ErrorMessage mensaje = new ErrorMessage("No existe el id del sender o el receiver");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
        if (calificacion.getComentarios().isEmpty() || calificacion.getNumEstrellas() == 0){
            ErrorMessage mensaje = new ErrorMessage("Calificacion o estrellas vacias");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
        }*/
        Date fechaActual = new Date();
        calificacion.setFecha(fechaActual);
        calificacionDAO.save(calificacion);
        /*return ResponseEntity.status(HttpStatus.CREATED).build();*/
    }

    public void deleteClasificacion(String id) {
        calificacionDAO.deleteById(id);
    }

}
