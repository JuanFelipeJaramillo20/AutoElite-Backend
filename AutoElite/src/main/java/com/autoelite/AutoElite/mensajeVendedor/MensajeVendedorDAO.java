package com.autoelite.AutoElite.mensajeVendedor;

import com.autoelite.AutoElite.Usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeVendedorDAO extends JpaRepository<MensajeVendedor, String> {
    List<MensajeVendedor> findByReceiver(Usuario receiver);
}
