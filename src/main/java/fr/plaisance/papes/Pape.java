package fr.plaisance.papes;

import java.time.LocalDate;
import java.util.Objects;

public class Pape {

    private Integer rang;
    private LocalDate election, fin;
    private String nom, numero, nationalite;
    private Boolean saint;

    public Integer getRang() {
        return rang;
    }

    public void setRang(Integer rang) {
        this.rang = rang;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public Boolean getSaint() {
        return saint;
    }

    public void setSaint(Boolean saint) {
        this.saint = saint;
    }

    public LocalDate getElection() {
        return election;
    }

    public void setElection(LocalDate election) {
        this.election = election;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    @Override
    public int hashCode() {
        return this.rang;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Pape) {
            Pape other = (Pape) object;
            return Objects.equals(this.rang, other.rang);
        }
        return false;
    }

    @Override
    public String toString() {
        return this.rang + " – " + (this.saint ? "St " : "") + this.nom + (this.numero.length() > 0 ? " " + this.numero : "");
    }
}
