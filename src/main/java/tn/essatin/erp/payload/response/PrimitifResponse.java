package tn.essatin.erp.payload.response;

public class PrimitifResponse {
    String nom;
    String typeDeDonnee;
    String cheminDeLaClasse;
    Object valeur;

    public PrimitifResponse(String nom, Object valeur) {
        this.nom = nom;
        this.typeDeDonnee=valeur.getClass().getSimpleName();
        this.cheminDeLaClasse = valeur.getClass().getName();
        this.valeur = valeur;
    }

    public String getCheminDeLaClasse() {
        return cheminDeLaClasse;
    }

    public void setCheminDeLaClasse(String cheminDeLaClasse) {
        this.cheminDeLaClasse = cheminDeLaClasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTypeDeDonnee() {
        return typeDeDonnee;
    }

    public void setTypeDeDonnee(String typeDeDonnee) {
        this.typeDeDonnee = typeDeDonnee;
    }

    public Object getValeur() {
        return valeur;
    }

    public void setValeur(Object valeur) {
        this.valeur = valeur;
    }
}
