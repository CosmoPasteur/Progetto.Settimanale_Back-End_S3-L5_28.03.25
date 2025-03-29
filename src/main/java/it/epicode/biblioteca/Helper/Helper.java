package it.epicode.biblioteca.Helper;

import it.epicode.biblioteca.archivi.Archivio;
import it.epicode.biblioteca.cataloghi.ElementoCatalogo;
import it.epicode.biblioteca.dao.LibroDAO;
import it.epicode.biblioteca.dao.RivistaDAO;
import it.epicode.biblioteca.dao.UtenteDAO;
import it.epicode.biblioteca.libri.Libro;
import it.epicode.biblioteca.riviste.Rivista;
import it.epicode.biblioteca.dao.PrestitoDAO;
import it.epicode.biblioteca.eccezioni.ElementoNonTrovatoException;
import it.epicode.biblioteca.enums.Periodicita;
import it.epicode.biblioteca.prestiti.Prestito;

import java.util.List;
import java.util.Scanner;

public class Helper {
    private Archivio archivio;
    private Scanner scanner;
    private PrestitoDAO prestitoDAO;
    private LibroDAO libroDAO;
    private RivistaDAO rivistaDAO;
    private UtenteDAO utenteDAO;

    public Helper() {
        this.archivio = new Archivio();
        this.scanner = new Scanner(System.in);
        this.prestitoDAO = new PrestitoDAO();
        this.libroDAO = new LibroDAO();
        this.rivistaDAO = new RivistaDAO();
        this.utenteDAO = new UtenteDAO();
    }

    public void startMenu() {
        while (true) {
            System.out.println("------- MENU -------");
            System.out.println("1. Aggiungi libro");
            System.out.println("2. Aggiungi rivista");
            System.out.println("3. Cerca elemento per ISBN");
            System.out.println("4. Rimuovi elemento per ISBN");
            System.out.println("5. Ricerca per anno di pubblicazione");
            System.out.println("6. Ricerca per autore");
            System.out.println("7. Ricerca per genere");
            System.out.println("8. Aggiorna elemento esistente per ISBN");
            System.out.println("9. Mostra prestiti");
            System.out.println("0. Esci");

            System.out.println("Seleziona una opzione: ");
            int scelta = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (scelta) {
                    case 1:
                        aggiungiElemento();
                        break;
                    case 2:
                        cercaElemento();
                        break;
                    case 3:
                        rimuoviElemento();
                        break;
                    case 4:
                        ricercaPerAnno();
                        break;
                    case 5:
                        ricercaPerAutore();
                        break;
                    case 6:
                        ricercaPerGenere();
                        break;
                    case 7:
                        aggiornaElemento();
                        break;
                    case 8:
                        archivio.stampaStatistiche();
                        break;
                    case 9:
                        mostraPrestiti();
                        break;
                    case 0:
                        System.out.println("Arrivederci!");
                        return;
                    default:
                        System.out.println("Errore: Scelta non valida.");
                }
            } catch (ElementoNonTrovatoException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }

    private void aggiungiElemento() {
        System.out.println("Che tipo di elemento vuoi aggiungere?");
        System.out.println("1. Libro");
        System.out.println("2. Rivista");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        switch (tipo) {
            case 1:
                aggiungiLibro();
                break;
            case 2:
                aggiungiRivista();
                break;
            default:
                System.out.println("Scelta non valida.");
        }
    }

    private void aggiungiLibro() {
        System.out.println("Inserisci il codice ISBN: ");
        String isbn = scanner.nextLine();
        System.out.println("Inserisci il Titolo: ");
        String titolo = scanner.nextLine();
        System.out.println("Anno di pubblicazione: ");
        int anno = scanner.nextInt();
        System.out.println("Numero di Pagine: ");
        int pagine = scanner.nextInt();
        scanner.nextLine();  // Consuma il newline rimasto
        System.out.println("Inserisci Autore: ");
        String autore = scanner.nextLine();
        System.out.println("Inserisci Genere: ");
        String genere = scanner.nextLine();

        Libro libro = new Libro(isbn, titolo, anno, pagine, autore, genere);
        libroDAO.save(libro);
        System.out.println("Libro aggiunto con successo!");
    }

    private void aggiungiRivista() {
        System.out.print("Inserisci ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Inserisci Titolo: ");
        String titolo = scanner.nextLine();
        System.out.print("Inserisci Anno di Pubblicazione: ");
        int anno = scanner.nextInt();
        System.out.print("Inserisci Numero di Pagine: ");
        int pagine = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Inserisci Periodicità (SETTIMANALE, MENSILE, SEMESTRALE): ");
        String periodicitaStr = scanner.nextLine().toUpperCase();

        try {
            Periodicita periodicita = Periodicita.valueOf(periodicitaStr);
            Rivista rivista = new Rivista(isbn, titolo, anno, pagine, periodicita);
            rivistaDAO.save(rivista);
            System.out.println("Rivista aggiunta con successo!");
        } catch (IllegalArgumentException e) {
            System.out.println("Errore: Periodicità non valida.");
        }
    }

    private void cercaElemento() throws ElementoNonTrovatoException {
        System.out.println("Inserisci il codice ISBN dell'elemento da cercare: ");
        String isbn = scanner.nextLine();  // L'ISBN è una Stringa

        // Cerchiamo prima il libro
        Libro libro = null;
        Rivista rivista = null;

        libro = libroDAO.findById(isbn);  // Cerca un libro con ISBN
        System.out.println("Elemento trovato: " + libro);  // Se trovato, stampa il libro
    }

    private void rimuoviElemento() throws ElementoNonTrovatoException {
        System.out.println("Inserisci il codice ISBN dell'elemento da rimuovere: ");
        String isbn = scanner.nextLine();
        libroDAO.delete(isbn);
        System.out.println("Libro rimosso con successo.");
    }

    private void ricercaPerAnno() {
        System.out.println("Inserisci l'anno di pubblicazione: ");
        int anno = getIntInput();
        List<Libro> libri = libroDAO.findByAnno(anno);
        if (libri.isEmpty()) {
            System.out.println("Nessun libro trovato per l'anno " + anno);
        } else {
            libri.forEach(System.out::println);
        }
    }

    private void ricercaPerAutore() {
        System.out.println("Inserisci l'autore: ");
        String autore = scanner.nextLine();
        List<Libro> libri = libroDAO.findByAutore(autore);
        if (libri.isEmpty()) {
            System.out.println("Nessun libro trovato per l'autore " + autore);
        } else {
            libri.forEach(System.out::println);
        }
    }

    private void ricercaPerGenere() {
        System.out.println("Inserisci il genere: ");
        String genere = scanner.nextLine();
        List<Libro> libri = libroDAO.findByGenere(genere);
        if (libri.isEmpty()) {
            System.out.println("Nessun libro trovato per il genere " + genere);
        } else {
            libri.forEach(System.out::println);
        }
    }

    private void aggiornaElemento() throws ElementoNonTrovatoException {
        System.out.println("Inserisci il codice ISBN dell'elemento da aggiornare: ");
        String isbn = scanner.nextLine();
        // Chiedi quale elemento aggiornare (libro o rivista)
        System.out.println("1. Libro");
        System.out.println("2. Rivista");
        int scelta = getIntInput();

        switch (scelta) {
            case 1: aggiungiLibro(); break;
            case 2: aggiungiRivista(); break;
            default: System.out.println("Scelta non valida."); break;
        }
    }

    private void mostraPrestiti() {
        List<Prestito> prestiti = prestitoDAO.findAll();
        if (prestiti.isEmpty()) {
            System.out.println("Non ci sono prestiti nel sistema.");
        } else {
            prestiti.forEach(System.out::println);
        }
    }

    // Metodo per ottenere input interi in modo sicuro
    private int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Input non valido, per favore inserisci un numero.");
            scanner.nextLine(); // Consuma l'input non valido
        }
        return scanner.nextInt();
    }

}
