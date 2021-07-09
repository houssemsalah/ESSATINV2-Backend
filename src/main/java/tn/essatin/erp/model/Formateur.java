package tn.essatin.erp.model;

import javax.persistence.*;

@Entity
public class Formateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFormateur;
    @ManyToOne
    private Personne idPersonne;
    @ManyToOne
    private Salarie idSalarie;

    public Formateur(Personne idPersonne, Salarie idSalarie) {
        this.idPersonne = idPersonne;
        this.idSalarie = idSalarie;
    }

    public Formateur() {
    }

    public Integer getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(Integer idFormateur) {
        this.idFormateur = idFormateur;
    }

    public Personne getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Personne idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Salarie getIdSalarie() {
        return idSalarie;
    }

    public void setIdSalarie(Salarie idSalarie) {
        this.idSalarie = idSalarie;
    }

    @Override
    public String toString() {
        return "Formateur{" +
                "idFormateur=" + idFormateur +
                ", idPersonne=" + idPersonne +
                ", idSalarie=" + idSalarie +
                '}';
    }
}
