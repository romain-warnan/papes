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
        return String.format("%s – %s (%s)", Papes.nomComplet(predecesseur), Papes.nomComplet(successeur), Papes.affichageVacanceTrone(this));
    }
}
