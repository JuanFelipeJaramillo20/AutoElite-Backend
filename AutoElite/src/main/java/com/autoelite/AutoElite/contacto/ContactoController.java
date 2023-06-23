package com.autoelite.AutoElite.contacto;

import com.autoelite.AutoElite.errores.ErrorMessage;
import com.autoelite.AutoElite.security.ConfirmationMessage;
import jakarta.persistence.PrePersist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/contacto")
public class ContactoController {

    private final ContactoService contactoService;
    private final ContactoDAO contactoDAO;

    public ContactoController(ContactoService contactoService, ContactoDAO contactoDAO) {
        this.contactoService = contactoService;
        this.contactoDAO = contactoDAO;
    }

    @GetMapping
    public ResponseEntity<?> getContacto(){
        try {
            return ResponseEntity.ok(contactoService.getAllContacto());
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("Mensaje de contacto no encontrado"));
        }
    }

    @GetMapping("{emailContacto}")
    public ResponseEntity<?> getContactoByEmail(@PathVariable("emailContacto") String email) {
        try {
            return ResponseEntity.ok(contactoService.getContactoByEmail(email));
        } catch (NullPointerException e) {
            String errorMessage = "Mensaje de contacto no encontrado con email: " + email;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(errorMessage));
        }
    }

    @PostMapping
    @PrePersist
    public ResponseEntity<?> addContacto(@RequestBody Contacto contacto){
        contacto.setFecha(new Date());
        contactoService.addContacto(contacto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ConfirmationMessage("Mensaje enviado con exito"));
    }

    @DeleteMapping("{idContacto}")
    public ResponseEntity<?> deletePublicaciones(@PathVariable("idContacto") String id) {
        if (contactoService.existsContacto(id)) {
            contactoService.deleteContacto(id);
            return ResponseEntity.ok(new ConfirmationMessage("Mensaje con ID " + id + " ha sido eliminado exitosamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("El mensaje con el ID " + id + " no existe"));
        }
    }
}
