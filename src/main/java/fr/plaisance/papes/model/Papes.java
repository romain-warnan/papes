package fr.plaisance.papes.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Papes {

    public static String nomComplet(Pape pape) {
        return pape.getNom() + (pape.getNumero().length() > 0 ? " " + pape.getNumero() : "");
    }

    public static Long dureeRegne(Pape pape) {
        return ChronoUnit.DAYS.between(pape.getElection(), pape.getFin());
    }

    public static Long dureeVacanceTrone(Succession succession) {
        return ChronoUnit.DAYS.between(succession.getPredecesseur().getFin(), succession.getSuccesseur().getElection());
    }

    public static String affichageDureeRegne(Pape pape) {
        return affichageDuree(pape.getElection(), pape.getFin());
    }

    public static String affichageVacanceTrone(Succession succession) {
        return affichageDuree(succession.getPredecesseur().getFin(), succession.getSuccesseur().getElection());
    }

    private static String affichageDuree(LocalDate debut, LocalDate fin) {
        LocalDate localDate = LocalDate.from(debut);
        long years = localDate.until(fin, ChronoUnit.YEARS);
        long months = localDate.until(fin, ChronoUnit.MONTHS);
        long days = localDate.until(fin, ChronoUnit.DAYS);
        StringBuilder builder = new StringBuilder();
        if (years > 0) {
            if (years == 1) {
                builder.append("1 an, ");
            }
            else {
                builder.append(String.format("%d ans, ", years));
            }
        }
        if (months > 0) {
            builder.append(String.format("%d mois, ", months));
        }
        if (days > 0) {
            if (days == 1) {
                builder.append("1 jour");
            }
            else {
                builder.append(String.format("%d jours", days));
            }
        }
        return builder.toString().trim();
    }
}
