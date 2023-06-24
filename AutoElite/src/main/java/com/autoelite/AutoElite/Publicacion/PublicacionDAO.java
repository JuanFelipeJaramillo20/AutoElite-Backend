package com.autoelite.AutoElite.Publicacion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicacionDAO extends JpaRepository<Publicacion, String> {
}
