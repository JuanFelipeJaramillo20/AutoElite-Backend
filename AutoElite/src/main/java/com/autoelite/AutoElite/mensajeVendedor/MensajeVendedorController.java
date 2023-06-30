package com.autoelite.AutoElite.mensajeVendedor;


import com.autoelite.AutoElite.Usuarios.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/mensajeVendedor")
public class MensajeVendedorController {

    private final MensajeVendedorService mensajeVendedorService;

    public MensajeVendedorController(MensajeVendedorService mensajeVendedorService) {
        this.mensajeVendedorService = mensajeVendedorService;
    }

    @GetMapping("{receiverId}")
    public ResponseEntity<?> getAllMensajeVendedor(@PathVariable("receiverId") Long id) {
        Usuario receiver = new Usuario();
        receiver.setId(id);
        return mensajeVendedorService.getAllMensajeVendedor(receiver);
    }

    @PostMapping
    public ResponseEntity<?> addMensajeVendedor(@RequestBody MensajeVendedorRequest mensaje) {
        return mensajeVendedorService.addMensajeVendedor(mensaje);
    }
}
