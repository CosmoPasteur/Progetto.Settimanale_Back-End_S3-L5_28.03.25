package it.epicode.biblioteca.dao;

import it.epicode.biblioteca.prestiti.Prestito;
import it.epicode.biblioteca.util.EntityManagerFactoryUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

public class PrestitoDAO {
    private EntityManagerFactory emf = EntityManagerFactoryUtil.getEntityManagerFactory();

    public void save(Prestito prestito) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(prestito);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Prestito findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Prestito.class, id);
        } finally {
            em.close();
        }
    }

    public List<Prestito> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Prestito p", Prestito.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Prestito prestito) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(prestito);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Prestito prestito = em.find(Prestito.class, id);
            if (prestito != null) {
                em.remove(prestito);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public boolean isPrestitoScaduto(Prestito prestito) {
        LocalDate dataRestituzione = prestito.getDataRestituzioneEffettiva() != null
                ? prestito.getDataRestituzioneEffettiva()
                : LocalDate.now();

        return dataRestituzione.isAfter(prestito.getDataRestituzionePrevista());
    }

}
