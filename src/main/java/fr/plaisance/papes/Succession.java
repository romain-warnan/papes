package fr.plaisance.papes;

public class Succession {

    private Pape predecesseur;
    private Pape successeur;

    public Succession(Pape predecesseur, Pape successeur) {
        this.predecesseur = predecesseur;
        this.successeur = successeur;
    }

    public Pape getPredecesseur() {
        return predecesseur;
    }

    public Pape getSuccesseur() {
        return successeur;
    }

    @Override
    public String toString() {
        return String.format("%s – %s (%d ans)", Papes.nomComplet(predecesseur), Papes.nomComplet(successeur), Papes.dureeVacanceTrone(this));
    }
}
