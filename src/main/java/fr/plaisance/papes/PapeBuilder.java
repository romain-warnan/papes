package fr.plaisance.papes;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;

@Service
public class PapeBuilder {

    private static final String SAINT = "St ";

    public Pape buildOne(String line) {
        String[] tokens = line.split(";");
        return papeFromTokens(tokens);
    }

    private static Pape papeFromTokens(String[] tokens) {
        Pape pape = new Pape();
        pape.setRang(rang(tokens));
        pape.setNom(nom(tokens));
        pape.setNationalite(nationalite(tokens));
        pape.setSaint(saint(tokens));
        pape.setNumero(numero(tokens));
        pape.setElection(election(tokens));
        pape.setFin(fin(tokens));
        return pape;
    }

    private static Integer rang(String[] tokens) {
        return Integer.valueOf(tokens[0]);
    }

    private static LocalDate election(String[] tokens) {
        String election = tokens[3];
        String[] parts = election.split("/");
        if (parts.length == 1) {
            Integer annee = Integer.valueOf(parts[0]);
            return Year.of(annee).atMonth(1).atDay(1);
        } else if (parts.length == 2) {
            Integer annee = Integer.valueOf(parts[1]);
            Integer mois = Integer.valueOf(parts[0]);
            return Year.of(annee).atMonth(mois).atDay(1);
        }
        Integer annee = Integer.valueOf(parts[2]);
        Integer mois = Integer.valueOf(parts[1]);
        Integer jour = Integer.valueOf(parts[0]);
        return Year.of(annee).atMonth(mois).atDay(jour);
    }

    private static LocalDate fin(String[] tokens) {
        String election = tokens[4];
        if (election.length() == 0) {
            return null;
        }

        String[] parts = election.split("/");
        if (parts.length == 1) {
            Integer annee = Integer.valueOf(parts[0]);
            return Year.of(annee).atMonth(12).atEndOfMonth();
        } else if (parts.length == 2) {
            Integer annee = Integer.valueOf(parts[1]);
            Integer mois = Integer.valueOf(parts[0]);
            return Year.of(annee).atMonth(mois).atEndOfMonth();
        }
        Integer annee = Integer.valueOf(parts[2]);
        Integer mois = Integer.valueOf(parts[1]);
        Integer jour = Integer.valueOf(parts[0]);
        return Year.of(annee).atMonth(mois).atDay(jour);
    }

    private static Boolean saint(String[] tokens) {
        return tokens[1].startsWith(SAINT);
    }

    private static String numero(String[] tokens) {
        String nomComplet = tokens[1].replace(SAINT, "");
        int index = nomComplet.lastIndexOf(" ");
        return index > 0 ? nomComplet.substring(index + 1, nomComplet.length()) : "";
    }

    private static String nationalite(String[] tokens) {
        return tokens[2];
    }

    private static String nom(String[] tokens) {
        String numero = numero(tokens);
        return tokens[1].replace(SAINT, "").replace(" " + numero, "");
    }
}
