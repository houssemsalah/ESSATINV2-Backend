package tn.essatin.erp.model.financier;

import tn.essatin.erp.model.Personne;

import javax.persistence.*;

@Entity
public class Vacataire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Personne personne;

    public Vacataire(Personne personne) {
        this.personne = personne;

    }

    public Vacataire() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    @Override
    public String toString() {
        return "Vacataire{" +
                "id=" + id +
                ", Personne=" + personne +
                '}';
    }
}
