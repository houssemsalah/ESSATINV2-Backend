package tn.essatin.erp.model;

import tn.essatin.erp.model.financier.Employer;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Signataire {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String poste;
    @ManyToOne
    private Employer employer;

    public Signataire(String poste, Employer employer) {
        this.poste = poste;
        this.employer = employer;
    }

    public Signataire(Integer id, String poste, Employer employer) {
        Id = id;
        this.poste = poste;
        this.employer = employer;
    }

    public Signataire() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    @Override
    public String toString() {
        return "Signataire{" +
                "Id=" + Id +
                ", poste='" + poste + '\'' +
                ", employer=" + employer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Signataire)) return false;
        Signataire that = (Signataire) o;
        return getId().equals(that.getId()) && getPoste().equals(that.getPoste()) && getEmployer().equals(that.getEmployer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPoste(), getEmployer());
    }
}
