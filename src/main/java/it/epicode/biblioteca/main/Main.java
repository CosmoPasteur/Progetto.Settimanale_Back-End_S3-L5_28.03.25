package it.epicode.biblioteca.main;

import it.epicode.biblioteca.Helper.Helper;
import it.epicode.biblioteca.libri.Libro;
import it.epicode.biblioteca.dao.LibroDAO;
import it.epicode.biblioteca.dao.PrestitoDAO;
import it.epicode.biblioteca.dao.UtenteDAO;
import it.epicode.biblioteca.prestiti.Prestito;
import it.epicode.biblioteca.prestiti.Utente;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Helper helper = new Helper();

        // Esegui l'inserimento degli utenti e dei prestiti di esempio
        aggiungiPrestitiEsempio();

        // Avvia il menu interattivo
        helper.startMenu();
    }
    public static void aggiungiPrestitiEsempio() {
        // Creazione di utenti di esempio
        Utente utente1 = new Utente("Mario", "Rossi", LocalDate.of(1990, 5, 15), "12345");
        Utente utente2 = new Utente("Giorgia", "Verdi", LocalDate.of(1995, 8, 20), "67890");
        Utente utente3 = new Utente("Luca", "Bianchi", LocalDate.of(1988, 12, 10), "23456");

        UtenteDAO utenteDAO = new UtenteDAO();
        utenteDAO.save(utente1);
        utenteDAO.save(utente2);
        utenteDAO.save(utente3);

        // Creazione di libri di esempio (Assicurati che la classe Libro esista e abbia un costruttore adatto)
        Libro libro1 = new Libro("978-3-16-148410-0", "Il Grande Libro", 2020, 350, "Mario Rossi", "Avventura");
        Libro libro2 = new Libro("978-3-16-148410-1", "Il Grande Libro 2", 2021, 320, "Ginevra Verdi", "Giallo" );
        Libro libro3 = new Libro("978-1-234-56789-0", "Rivista Mensile", 2025, 50, "Luca Bianchi", "Informatica" );

        LibroDAO libroDAO = new LibroDAO();
        libroDAO.save(libro1);
        libroDAO.save(libro2);
        libroDAO.save(libro3);

        // Creazione di prestiti di esempio

        Prestito prestito1 = new Prestito();
        prestito1.setUtente(utente1);
        prestito1.setElementoPrestato(libro1);
        prestito1.setDataInizioPrestito(LocalDate.now());
        prestito1.setDataRestituzionePrevista(LocalDate.now().plusDays(30));  // 30 giorni da oggi
        //prestito1.setDataRestituzioneEffettiva(null);  // Non ancora restituito

        Prestito prestito2 = new Prestito();
        prestito2.setUtente(utente2);
        prestito2.setElementoPrestato(libro2);
        prestito2.setDataInizioPrestito(LocalDate.now());
        prestito2.setDataRestituzionePrevista(LocalDate.now().plusDays(20));  // 20 giorni da oggi
        prestito2.setDataRestituzioneEffettiva(null);  // Non ancora restituito

        Prestito prestito3 = new Prestito();
        prestito3.setUtente(utente3);
        prestito3.setElementoPrestato(libro3);
        prestito3.setDataInizioPrestito(LocalDate.now().minusDays(40));  // 40 giorni fa
        prestito3.setDataRestituzionePrevista(LocalDate.now().minusDays(10));  // Scaduto 10 giorni fa
        prestito3.setDataRestituzioneEffettiva(LocalDate.now().minusDays(10));  // Restituito ma troppo tardi

        //Salvataggio dei prestiti nel database tramite il PrestitoDAO
        PrestitoDAO prestitoDAO = new PrestitoDAO();
        try {
            prestitoDAO.save(prestito1);
            prestitoDAO.save(prestito2);
            prestitoDAO.save(prestito3);
        } catch (Exception e) {
            e.printStackTrace();  // Stampa il dettaglio dell'errore
            System.out.println("Errore durante il salvataggio del prestito. Operazione annullata.");
        }
    }
}