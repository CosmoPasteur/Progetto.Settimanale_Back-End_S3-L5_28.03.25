package it.epicode.biblioteca.dao;

import it.epicode.biblioteca.prestiti.Utente;
import it.epicode.biblioteca.util.EntityManagerFactoryUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UtenteDAO {
    private EntityManagerFactory emf = EntityManagerFactoryUtil.getEntityManagerFactory();

    public void save(Utente utente) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(utente);  // Salva l'utente nel database
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public Utente findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Utente.class, id);
        } finally {
            em.close();
        }
    }

}
