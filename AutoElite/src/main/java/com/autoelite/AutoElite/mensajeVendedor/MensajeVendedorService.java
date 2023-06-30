package com.autoelite.AutoElite.mensajeVendedor;

import com.autoelite.AutoElite.Usuarios.Usuario;
import com.autoelite.AutoElite.Usuarios.UsuarioDAO;
import com.autoelite.AutoElite.errores.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MensajeVendedorService {

    private final MensajeVendedorDAO mensajeVendedorDAO;
    private final UsuarioDAO usuarioDAO;

    public MensajeVendedorService(MensajeVendedorDAO mensajeVendedorDAO, UsuarioDAO usuarioDAO) {
        this.mensajeVendedorDAO = mensajeVendedorDAO;
        this.usuarioDAO = usuarioDAO;
    }

    public ResponseEntity<?> getAllMensajeVendedor(Usuario usuario) {
        List<MensajeVendedor> findReceiver = mensajeVendedorDAO.findByReceiver(usuario);
        if (findReceiver.isEmpty()){
            ErrorMessage mensaje = new ErrorMessage("Mensajes no encontrados");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
        return ResponseEntity.ok().body(findReceiver);
    }

    public ResponseEntity<?> addMensajeVendedor(MensajeVendedorRequest mensajeVendedorRequest) {
        Usuario sender = new Usuario();
        sender.setId(Long.valueOf(mensajeVendedorRequest.getSender()));

        Usuario receiver = new Usuario();
        receiver.setId(Long.valueOf(mensajeVendedorRequest.getReceiver()));

        Optional<Usuario> findReceiver = usuarioDAO.findById(receiver.getId() + "");
        Optional<Usuario> findSender = usuarioDAO.findById(sender.getId() + "");
        if (findReceiver.isEmpty() || findSender.isEmpty()){
            ErrorMessage mensaje = new ErrorMessage("No existe el id del sender o el receiver");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }

        Date fechaActual = new Date();
        MensajeVendedor mensajeVendedor = new MensajeVendedor();
        mensajeVendedor.setSender(sender);
        mensajeVendedor.setReceiver(receiver);
        mensajeVendedor.setFecha(fechaActual);
        mensajeVendedor.setMensaje(mensajeVendedorRequest.getMensaje());
        mensajeVendedor.setTelefono(mensajeVendedorRequest.getTelefono());
        mensajeVendedorDAO.save(mensajeVendedor);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
