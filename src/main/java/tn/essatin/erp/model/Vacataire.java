package tn.essatin.erp.model;

import javax.persistence.*;

@Entity
public class Vacataire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVacataire;
    @ManyToOne
    private Personne idPersonne;
    @ManyToOne
    private Salarie idSalarie;

    public Vacataire(Personne idPersonne, Salarie idSalarie) {
        this.idPersonne = idPersonne;
        this.idSalarie = idSalarie;
    }

    public Vacataire() {
    }

    public Integer getIdVacataire() {
        return idVacataire;
    }

    public void setIdVacataire(Integer idVacataire) {
        this.idVacataire = idVacataire;
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
        return "Vacataire{" +
                "idVacataire=" + idVacataire +
                ", idPersonne=" + idPersonne +
                ", idSalarie=" + idSalarie +
                '}';
    }
}
