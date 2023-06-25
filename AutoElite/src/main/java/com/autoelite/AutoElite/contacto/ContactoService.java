package com.autoelite.AutoElite.contacto;

import com.autoelite.AutoElite.Publicacion.Publicacion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactoService {
    private final ContactoDAO contactoDAO;
    @PersistenceContext
    private EntityManager entityManager;

    public ContactoService(ContactoDAO contactoDAO) {
        this.contactoDAO = contactoDAO;
    }

    public List<Contacto> getAllContacto(){
        List<Contacto> contacto = contactoDAO.findAll();
        if (contacto.isEmpty()) {
            throw new NullPointerException();
        }else {
            return contactoDAO.findAll();
        }
    }

    public List<Contacto> getContactoByEmail(String email){
        String jpql = "SELECT c FROM Contacto c WHERE c.email = '" + email + "'";
        TypedQuery<Contacto> query = entityManager.createQuery(jpql, Contacto.class);
        List<Contacto> contactos = query.getResultList();
        if (contactos.isEmpty()) {
            throw new NullPointerException();
        }
        return contactos;
        //return contactoDAO.findByEmail(email).orElseThrow(()-> new NullPointerException());
    }

    public void addContacto(Contacto contacto){
        contactoDAO.save(contacto);
    }

    public void deleteContacto(String id){
        contactoDAO.deleteById(id);
    }

    public boolean existsContacto(String id) {
        return contactoDAO.existsById(id);
    }
}
