package it.epicode.biblioteca.dao;

import it.epicode.biblioteca.cataloghi.Libro;
import jakarta.persistence.EntityManager;

public class LibroDAO {
    private EntityManager em;

    public LibroDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Libro libro) {
        em.persist(libro);
    }


}
