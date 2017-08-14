package fr.plaisance.papes;

import org.springframework.stereotype.Service;

@Service
public class PapeBuilder {

    private static final String SAINT = "St ";

    public Pape buildOne(String line) {
        String[] tokens = line.split(";");
        Integer rang = Integer.valueOf(tokens[0]);
        Integer election = Integer.valueOf(tokens[1]);
        String nomComplet = tokens[2];
        Boolean saint = nomComplet.startsWith(SAINT);
        String nom = nomComplet.replace(SAINT, "");
        int index = nomComplet.lastIndexOf(" ");
        String numero = index > 0 ? nomComplet.substring(index + 1, nomComplet.length()) : "";
        nom = nom.replace(numero, "");
        String nationalite = tokens[3];

        Pape pape = new Pape();
        pape.setRang(this.rang(tokens));
        pape.setElection(this.election(tokens));
        pape.setNom(this.nom(tokens));
        pape.setNationalite(this.nationalite(tokens));
        pape.setSaint(this.saint(tokens));
        pape.setNumero(this.numero(tokens));

        return pape;
    }

    private Integer rang(String[] tokens) {
        return Integer.valueOf(tokens[0]);
    }

    private Integer election(String[] tokens) {
        return Integer.valueOf(tokens[1]);
    }

    private Boolean saint(String[] tokens) {
        return  tokens[2].startsWith(SAINT);
    }

    private String numero(String[] tokens) {
        String nomComplet = tokens[2];
        int index = nomComplet.lastIndexOf(" ");
        return index > 0 ? nomComplet.substring(index + 1, nomComplet.length()) : "";
    }

    private String nationalite(String[] tokens) {
        return tokens[3];
    }

    private String nom(String[] tokens) {
        String numero = numero(tokens);
        return tokens[2].replace(SAINT, "").replace(" " + numero, "");
    }
}
