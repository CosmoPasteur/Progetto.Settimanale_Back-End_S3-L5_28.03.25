/*package it.epicode.biblioteca.archivi;

import it.epicode.biblioteca.cataloghi.ElementoCatalogo;
import it.epicode.biblioteca.cataloghi.Libro;
import it.epicode.biblioteca.prestiti.Prestito;

import java.util.*;
import java.util.stream.Collectors;

public class Archivio {
    private List<ElementoCatalogo> catalogo = new ArrayList<>();
    private List<Prestito> prestiti = new ArrayList<>();

    // 🔹 Aggiungere un elemento al catalogo
    public void aggiungiElemento(ElementoCatalogo e) {
        catalogo.add(e);
    }

    // 🔹 Rimuovere un elemento dato il codice ISBN
    public void rimuoviElemento(String codiceISBN) {
        catalogo.removeIf(e -> e.getCodiceIsbn().equals(codiceISBN));
    }

    // 🔹 Ricerca per codice ISBN
    public Optional<ElementoCatalogo> ricercaPerISBN(String codiceISBN) {
        return catalogo.stream()
                .filter(e -> e.getCodiceIsbn().equals(codiceISBN))
                .findFirst();
    }

    // 🔹 Ricerca per anno di pubblicazione
    public List<ElementoCatalogo> ricercaPerAnno(int anno) {
        return catalogo.stream()
                .filter(e -> e.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList());
    }

    // 🔹 Ricerca per autore (solo per i libri)
    public List<ElementoCatalogo> ricercaPerAutore(String autore) {
        return catalogo.stream()
                .filter(e -> e instanceof Libro && ((Libro) e).getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());
    }

    // 🔹 Ricerca per titolo o parte di esso
    public List<ElementoCatalogo> ricercaPerTitolo(String titolo) {
        return catalogo.stream()
                .filter(e -> e.getTitolo().toLowerCase().contains(titolo.toLowerCase()))
                .collect(Collectors.toList());
    }

    // 🔹 Aggiungere un prestito
    public void aggiungiPrestito(Prestito prestito) {
        prestiti.add(prestito);
    }

    // 🔹 Ricerca prestiti attivi per numero tessera
    public List<Prestito> ricercaPrestitiUtente(String numeroTessera) {
        return prestiti.stream()
                .filter(p -> p.getUtente().getNumeroTessera().equals(numeroTessera) && p.getDataRestituzioneEffettiva() == null)
                .collect(Collectors.toList());
    }

    // 🔹 Ricerca prestiti scaduti e non restituiti
    public List<Prestito> ricercaPrestitiScaduti() {
        return prestiti.stream()
                .filter(Prestito::isScaduto)
                .collect(Collectors.toList());
    }

    // 🔹 Registrare la restituzione di un elemento prestato
    public void restituisciElemento(String codiceISBN) {
        Optional<Prestito> prestito = prestiti.stream()
                .filter(p -> p.getElementoPrestato().getCodiceIsbn().equals(codiceISBN) && p.getDataRestituzioneEffettiva() == null)
                .findFirst();

        prestito.ifPresent(p -> p.setDataRestituzioneEffettiva(java.time.LocalDate.now()));
    }
}*/