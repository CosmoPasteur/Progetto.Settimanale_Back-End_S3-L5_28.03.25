package it.epicode.biblioteca.dao;

import it.epicode.biblioteca.riviste.Rivista;
import it.epicode.biblioteca.util.EntityManagerFactoryUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class RivistaDAO {
    private EntityManagerFactory emf = EntityManagerFactoryUtil.getEntityManagerFactory();

    // Salva una rivista
    public void save(Rivista rivista) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(rivista);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Trova una rivista per ID
    public Rivista findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Rivista.class, id);
        } finally {
            em.close();
        }
    }

    // Trova tutte le riviste
    public List<Rivista> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT r FROM Rivista r", Rivista.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Aggiorna una rivista
    public void update(Rivista rivista) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(rivista);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Elimina una rivista
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Rivista rivista = em.find(Rivista.class, id);
            if (rivista != null) {
                em.remove(rivista);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
