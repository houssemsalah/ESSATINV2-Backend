package tn.essatin.erp.model.financier;

import tn.essatin.erp.model.Personne;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Enseignant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String etablissementOrigine;
    @OneToOne
    private Personne personne;

    public Enseignant(Integer id, String etablissementOrigine, Personne personne) {
        this.id = id;
        this.etablissementOrigine = etablissementOrigine;
        this.personne = personne;
    }

    public Enseignant(String etablissementOrigine, Personne personne) {
        this.etablissementOrigine = etablissementOrigine;
        this.personne = personne;
    }

    public Enseignant() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEtablissementOrigine() {
        return etablissementOrigine;
    }

    public void setEtablissementOrigine(String etablissementOrigine) {
        this.etablissementOrigine = etablissementOrigine;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    @Override
    public String toString() {
        return "Enseignant{" +
                "id=" + id +
                ", etablissementOrigine='" + etablissementOrigine + '\'' +
                ", personne=" + personne +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enseignant)) return false;
        Enseignant that = (Enseignant) o;
        return getId().equals(that.getId()) &&
                getEtablissementOrigine().equals(that.getEtablissementOrigine()) &&
                getPersonne().equals(that.getPersonne());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEtablissementOrigine(), getPersonne());
    }
}
