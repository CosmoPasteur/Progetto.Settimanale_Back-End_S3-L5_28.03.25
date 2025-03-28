package it.epicode.biblioteca.main;



import it.epicode.biblioteca.cataloghi.Libro;
import it.epicode.biblioteca.cataloghi.Rivista;
import it.epicode.biblioteca.dao.BibliotecaDAO;
import it.epicode.biblioteca.prestiti.Prestito;
import it.epicode.biblioteca.prestiti.Utente;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Creazione dell'archivio
        BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();

        // Aggiungere un libro
        Libro libro = new Libro();
        libro.setCodiceIsbn("978-3-16-148410-0");
        libro.setTitolo("Il Grande Libro");
        libro.setAutore("Mario Rossi");
        libro.setAnnoPubblicazione(2020);
        bibliotecaDAO.addLibro(libro);

        // Aggiungere una rivista
        Rivista rivista = new Rivista();
        rivista.setTitolo("Rivista Mensile");
        rivista.setNumero(100);
        rivista.setMese("Marzo");
        rivista.setAnno(2025);
        bibliotecaDAO.addRivista(rivista);

        // Aggiungere un utente
        Utente utente = new Utente();
        utente.setNumeroTessera();
        utente.setNome("Giuseppe");
        utente.setCognome("Bianchi");
        utente.setEmail("giuseppe.bianchi@example.com");
        utente.setNumeroTessera("TESSERA001");
        bibliotecaDAO.addUtente(utente);

        // Aggiungere un prestito
        Prestito prestito = new Prestito();
        prestito.setIdUtente(utente);
        prestito.setIdLibro(libro);
        prestito.setDataPrestito(LocalDate.now());
        prestito.setDataRestituzionePrevista(LocalDate.now().plusWeeks(2));
        bibliotecaDAO.addPrestito(prestito);

        // Ricerca di un libro tramite ISBN
        Libro libroCercato = bibliotecaDAO.findLibroByIsbn("978-3-16-148410-0");
        if (libroCercato != null) {
            System.out.println("Libro trovato: " + libroCercato.getTitolo());
        } else {
            System.out.println("Libro non trovato.");
        }

        // Ricerca di un utente tramite ID
        Utente utenteCercato = bibliotecaDAO.findUtenteById(1);
        if (utenteCercato != null) {
            System.out.println("Utente trovato: " + utenteCercato.getNome() + " " + utenteCercato.getCognome());
        } else {
            System.out.println("Utente non trovato.");
        }

        // Trova tutti gli utenti
        System.out.println("Lista di tutti gli utenti:");
        bibliotecaDAO.findAllUtenti().forEach(u -> System.out.println(u.getNome() + " " + u.getCognome()));

        // Trova i prestiti attivi per una tessera
        System.out.println("Prestiti attivi per la tessera TESSERA001:");
        bibliotecaDAO.findPrestitiAttiviByTessera("TESSERA001").forEach(p -> System.out.println(p.getIdLibro().getTitolo()));

        // Trova i prestiti scaduti
        System.out.println("Prestiti scaduti:");
        bibliotecaDAO.findPrestitiScaduti().forEach(p -> System.out.println(p.getIdLibro().getTitolo()));

        // Chiudere la connessione
        bibliotecaDAO.close();
    }
}

