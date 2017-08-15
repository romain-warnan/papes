package fr.plaisance.papes;

import org.springframework.stereotype.Service;

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
        pape.setElection(election(tokens));
        pape.setFin(fin(tokens));
        pape.setNom(nom(tokens));
        pape.setNationalite(nationalite(tokens));
        pape.setSaint(saint(tokens));
        pape.setNumero(numero(tokens));
        return pape;
    }

    private static Integer rang(String[] tokens) {
        return Integer.valueOf(tokens[0]);
    }

    private static Integer election(String[] tokens) {
        return Integer.valueOf(tokens[1]);
    }

    private static Integer fin(String[] tokens) {
        return tokens[2].length() == 0 ? null : Integer.valueOf(tokens[2]);
    }

    private static Boolean saint(String[] tokens) {
        return tokens[3].startsWith(SAINT);
    }

    private static String numero(String[] tokens) {
        String nomComplet = tokens[3].replace(SAINT, "");
        int index = nomComplet.lastIndexOf(" ");
        return index > 0 ? nomComplet.substring(index + 1, nomComplet.length()) : "";
    }

    private static String nationalite(String[] tokens) {
        return tokens[4];
    }

    private static String nom(String[] tokens) {
        String numero = numero(tokens);
        return tokens[3].replace(SAINT, "").replace(" " + numero, "");
    }
}
