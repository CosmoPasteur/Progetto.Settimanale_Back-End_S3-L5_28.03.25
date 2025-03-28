package it.epicode.biblioteca.dao;

import it.epicode.biblioteca.cataloghi.Libro;
import it.epicode.biblioteca.cataloghi.Rivista;
import it.epicode.biblioteca.prestiti.Prestito;
import it.epicode.biblioteca.prestiti.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class BibliotecaDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public BibliotecaDAO() {

        emf = Persistence.createEntityManagerFactory("epicode");
        em = emf.createEntityManager();
    }

    public void addLibro(Libro libro) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(libro);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public void addRivista(Rivista rivista) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(rivista);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public void addUtente(Utente utente) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(utente);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public void addPrestito(Prestito prestito) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(prestito);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public Libro findLibroByIsbn(String isbn) {
        return em.find(Libro.class, isbn);
    }

    public Utente findUtenteById(int id) {
        return em.find(Utente.class, id);
    }

    public List<Utente> findAllUtenti() {
        return em.createQuery("SELECT u FROM Utente u", Utente.class).getResultList();
    }

    public List<Prestito> findPrestitiAttiviByTessera(String tessera) {
        return em.createQuery("SELECT p FROM Prestito p WHERE p.idUtente.numeroTessera = :tessera AND p.dataRestituzioneEffettiva IS NULL", Prestito.class)
                .setParameter("tessera", tessera)
                .getResultList();
    }

    public List<Prestito> findPrestitiScaduti() {
        return em.createQuery("SELECT p FROM Prestito p WHERE p.dataRestituzioneEffettiva IS NULL AND p.dataRestituzionePrevista < CURRENT_DATE", Prestito.class)
                .getResultList();
    }

    public void close() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }


}
