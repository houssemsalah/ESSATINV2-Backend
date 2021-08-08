package tn.essatin.erp.model.financier;

import tn.essatin.erp.model.Personne;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Formateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private Personne Personne;

    public Formateur(Integer id, tn.essatin.erp.model.Personne personne) {
        this.id = id;
        Personne = personne;
    }

    public Formateur() {
    }

    public Formateur(tn.essatin.erp.model.Personne personne) {
        Personne = personne;
    }

    public Formateur(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public tn.essatin.erp.model.Personne getPersonne() {
        return Personne;
    }

    public void setPersonne(tn.essatin.erp.model.Personne personne) {
        Personne = personne;
    }

    @Override
    public String toString() {
        return "Formateur{" +
                "id=" + id +
                ", Personne=" + Personne +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Formateur)) return false;
        Formateur formateur = (Formateur) o;
        return getId().equals(formateur.getId()) &&
                getPersonne().equals(formateur.getPersonne());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPersonne());
    }
}
