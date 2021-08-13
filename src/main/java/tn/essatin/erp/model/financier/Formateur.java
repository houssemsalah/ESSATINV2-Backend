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
    private Personne personne;

    public Formateur(Integer id, Personne personne) {
        this.id = id;
        this.personne = personne;
    }

    public Formateur() {
    }

    public Formateur(Personne personne) {
        this.personne = personne;
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
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    @Override
    public String toString() {
        return "Formateur{" +
                "id=" + id +
                ", Personne=" + personne +
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
