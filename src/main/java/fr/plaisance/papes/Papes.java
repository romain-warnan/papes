package fr.plaisance.papes;

import java.time.Year;

public abstract class Papes {

    public static Integer dureeRegne(Pape pape) {
        int anneeDeFinDeRegne = pape.getFin() == null ? Year.now().getValue() : pape.getFin();
        return anneeDeFinDeRegne - pape.getElection();
    }

    public static String nomComplet(Pape pape) {
        return pape.getNom() + (pape.getNumero().length() > 0 ? " " + pape.getNumero() : "");
    }

    public static Integer dureeVacanceTrone(Succession succession) {
        return succession.getPredecesseur().getFin() - succession.getSuccesseur().getElection();
    }
}
