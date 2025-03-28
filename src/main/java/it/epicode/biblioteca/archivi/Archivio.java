package it.epicode.biblioteca.archivi;

import it.epicode.biblioteca.cataloghi.ElementoCatalogo;
import it.epicode.biblioteca.cataloghi.Libro;
import it.epicode.biblioteca.prestiti.Prestito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Archivio {
    private List<ElementoCatalogo> catalogo = new ArrayList<>();
    private List<Prestito> prestiti = new ArrayList<>();

    public void aggiungiElemento(ElementoCatalogo elemento) {
        catalogo.add(elemento);
    }

    public void rimuoviElemento(String isbn) {
        catalogo.removeIf(e -> e.getIsbn().equals(isbn));
    }

    public ElementoCatalogo ricercaPerIsbn(String isbn) {
        return catalogo.stream().filter(e -> e.getIsbn().equals(isbn)).findFirst().orElse(null);
    }

    public List<ElementoCatalogo> ricercaPerAnno(int anno) {
        return catalogo.stream().filter(e -> e.getAnnoPubblicazione() == anno).collect(Collectors.toList());
    }
        //return catalogo.stream().filter(e -> e instanceof Libro && ((Libro) e).getAutore().equalsIgnoreCase(autore)).map(e -> (Libro) e).collect(Collectors.toList());
    //}

    public List<ElementoCatalogo> ricercaPerTitolo(String titolo) {
        return catalogo.stream().filter(e -> e.getTitolo().toLowerCase().contains(titolo.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Prestito> prestitiAttiviPerUtente(String numeroTessera) {
        return prestiti.stream()
                .filter(p -> p.getUtente().getNumeroTessera().equals(numeroTessera) && p.getDataRestituzioneEffettiva() == null)
                .collect(Collectors.toList());
    }

    public List<Prestito> prestitiScaduti() {
        return prestiti.stream().filter(Prestito::isScaduto).collect(Collectors.toList());
    }

}
