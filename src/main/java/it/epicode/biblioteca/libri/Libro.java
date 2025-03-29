package it.epicode.biblioteca.libri;

import it.epicode.biblioteca.cataloghi.ElementoCatalogo;
import jakarta.persistence.*;

@Entity
@Table(name = "libri")
public class Libro extends ElementoCatalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 200)
    private String autore;

    @Column(nullable = false, length = 200)
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
