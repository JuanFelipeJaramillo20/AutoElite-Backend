package com.autoelite.AutoElite.Usuarios;

import com.autoelite.AutoElite.security.ConfirmationMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    private final UsuarioService userService;
    private final AdminService adminService;

    public AdminController(UsuarioService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Usuario>> getAllUsers() {
        List<Usuario> users = userService.getAllUsuarios();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> getUserById(@PathVariable String userId) {
        Usuario user = userService.getUsuarioById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/users/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> updateUserAsAdmin(@PathVariable String userId, @RequestBody Usuario updatedUser) {
        Usuario user = adminService.updateUserAsAdmin(userId, updatedUser);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/users/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUserAsAdmin(@PathVariable String userId) {
        boolean deleted = adminService.deleteUserAsAdmin(userId);
        if (deleted) {
            return ResponseEntity.ok(new ConfirmationMessage("Usuario con ID " + userId + " ha sido eliminado exitosamente"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/users/{userId}/block")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> blockUserAsAdmin(@PathVariable String userId) {
        boolean blocked = adminService.blockUserAsAdmin(userId);
        if (blocked) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

