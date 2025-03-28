package it.epicode.biblioteca.cataloghi;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "libri")
public class Libro extends ElementoCatalogo {

    @Column(nullable = false)
    private String autore;

    @Column(nullable = false)
    private String genere;

    public Libro(String codiceIsbn, String titolo, int annoPubblicazione, int numeroPagine,  String autore, String genere) {
        super(codiceIsbn, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public Libro() {
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "Autore='" + autore + '\'' +
                ", Genere='" + genere + '\'' +
                '}';
    }
}
