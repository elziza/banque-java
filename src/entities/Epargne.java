package entities;

public class Epargne extends Compte{
    private double taux;

    
    public Epargne(int id, String numero, double solde, double taux) {
        super(id, numero, solde);
        this.taux = taux;
    }

    public Epargne(double solde, double taux) {
        super(solde);
        this.taux = taux;
        typeCompte=TypeCompte.Epargne;
    }

    public Epargne() {
        super();
        typeCompte=TypeCompte.Epargne;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    @Override
    public String toString() {
        return "Epargne"+super.toString()+"taux=" + taux + "]";
    }
    
}
