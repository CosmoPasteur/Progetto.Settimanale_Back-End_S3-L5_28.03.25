package it.epicode.biblioteca.dao;

import it.epicode.biblioteca.eccezioni.ElementoNonTrovatoException;
import it.epicode.biblioteca.libri.Libro;
import it.epicode.biblioteca.riviste.Rivista;
import it.epicode.biblioteca.util.EntityManagerFactoryUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

import static it.epicode.biblioteca.util.EntityManagerFactoryUtil.emf;


public class LibroDAO {
    private EntityManager em;

    public LibroDAO() {
        this.em = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
    }

    public void save(Libro libro) {
        try {
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public Libro findById(String isbn) throws ElementoNonTrovatoException {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT l FROM Libro l WHERE l.isbn = :isbn", Libro.class)
                    .setParameter("isbn", isbn)
                    .getSingleResult();
        } catch (Exception e) {
            throw new ElementoNonTrovatoException("Libro con ISBN " + isbn + " non trovato.");
        } finally {
            em.close();
        }
    }

    public List<Libro> findByIsbn(String isbn) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT l FROM Libro l WHERE l.isbn = :isbn", Libro.class)
                    .setParameter("isbn", isbn)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Libro> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT l FROM Libro l", Libro.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Libro libro) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(libro);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(String isbn) throws ElementoNonTrovatoException {
        Libro libro = findById(isbn);
        if (libro != null) {
            try {
                em.getTransaction().begin();
                em.remove(libro);
                em.getTransaction().commit();
            } catch (RuntimeException e) {
                em.getTransaction().rollback();
                throw e;
            }
        }
    }

    public List<Libro> findByGenere(String genere) {
        TypedQuery<Libro> query = em.createQuery("SELECT l FROM Libro l WHERE l.genere = :genere", Libro.class);
        query.setParameter("genere", genere);
        return query.getResultList();
    }

    // Metodo che restituisce una lista di libri filtrati per autore
    public List<Libro> findByAutore(String autore) {
        TypedQuery<Libro> query = em.createQuery("SELECT l FROM Libro l WHERE l.autore = :autore", Libro.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }

    // Metodo che restituisce una lista di libri filtrati per anno
    public List<Libro> findByAnno(int anno) {
        TypedQuery<Libro> query = em.createQuery("SELECT l FROM Libro l WHERE l.annoPubblicazione = :anno", Libro.class);
        query.setParameter("anno", anno);
        return query.getResultList();
    }

}
