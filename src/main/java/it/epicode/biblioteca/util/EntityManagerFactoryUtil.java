package it.epicode.biblioteca.util;

//una classe di utilità per EntityManagerFactory per gestire la creazione dell'EntityManagerFactory, rendendola accessibile a tutti i DAO senza doverla creare ogni volta.

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryUtil {

    public static EntityManagerFactory emf;

    private EntityManagerFactoryUtil() {
        // Costruttore privato per evitare istanziazione esterna
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("epicode");
        }
        return emf;
    }

    public static void closeEntityManagerFactory() {
        if (emf != null) {
            emf.close();
        }
    }

}
