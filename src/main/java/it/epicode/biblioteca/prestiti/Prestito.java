package it.epicode.biblioteca.prestiti;

import it.epicode.biblioteca.cataloghi.ElementoCatalogo;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "prestiti")
public class Prestito {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ElementoCatalogo elementoPrestato;

    //@OneToMany
    @Column(nullable = false)
    private LocalDate dataInizioPrestito;

    @Column(nullable = false)
    private LocalDate dataRestituzionePrevista;
    private LocalDate dataRestituzioneEffettiva;

    public Prestito(Utente utente, ElementoCatalogo elementoPrestato, LocalDate dataInizioPrestito, LocalDate dataRestituzionePrevista, LocalDate dataRestituzioneEffettiva) {
        this.utente = utente;
        this.elementoPrestato = elementoPrestato;
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzionePrevista = dataRestituzionePrevista;
        this.dataRestituzioneEffettiva = null;
    }

    public Prestito() {
    }

    public boolean isScaduto() {
        return dataRestituzioneEffettiva == null && LocalDate.now().isAfter(dataRestituzionePrevista);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public ElementoCatalogo getElementoPrestato() {
        return elementoPrestato;
    }

    public void setElementoPrestato(ElementoCatalogo elementoPrestato) {
        this.elementoPrestato = elementoPrestato;
    }

    public LocalDate getDataInizioPrestito() {
        return dataInizioPrestito;
    }

    public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
    }

    public LocalDate getDataRestituzionePrevista() {
        return dataRestituzionePrevista;
    }

    public void setDataRestituzionePrevista(LocalDate dataRestituzionePrevista) {
        this.dataRestituzionePrevista = dataRestituzionePrevista;
    }

    public LocalDate getDataRestituzioneEffettiva() {
        return dataRestituzioneEffettiva;
    }

    public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "utente=" + utente +
                ", elementoPrestato=" + elementoPrestato +
                ", dataInizioPrestito=" + dataInizioPrestito +
                ", dataRestituzionePrevista=" + dataRestituzionePrevista +
                ", dataRestituzioneEffettiva=" + dataRestituzioneEffettiva +
                '}';
    }
}
