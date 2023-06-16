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

    public ResponseEntity<?> addCalificacion(CalificacionRequest calificacionRequest) {
        Usuario sender = new Usuario();
        sender.setId(Integer.parseInt(calificacionRequest.getSender()));

        Usuario receiver = new Usuario();
        receiver.setId(Integer.parseInt(calificacionRequest.getReceiver()));

        Optional<Usuario> findReceiver = usuarioDAO.findById(receiver.getId() + "");
        Optional<Usuario> findSender = usuarioDAO.findById(sender.getId() + "");
        if (findReceiver.isEmpty() || findSender.isEmpty()){
            ErrorMessage mensaje = new ErrorMessage("No existe el id del sender o el receiver");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }

        Date fechaActual = new Date();
        Calificacion calificacion = new Calificacion();
        calificacion.setSender(sender);
        calificacion.setReceiver(receiver);
        calificacion.setFecha(fechaActual);
        calificacion.setComentarios(calificacionRequest.getComentarios());
        calificacion.setNumEstrellas(calificacionRequest.getNumEstrellas());
        calificacionDAO.save(calificacion);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<?> deleteCalificacion(String id) {
        Optional<Calificacion> findCalificacionById = calificacionDAO.findById(id);
        if (findCalificacionById.isEmpty()){
            ErrorMessage mensaje = new ErrorMessage("no existe la calificacion con id " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
        calificacionDAO.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
