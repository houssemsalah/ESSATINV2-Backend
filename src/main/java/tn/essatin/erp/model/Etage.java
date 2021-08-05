package tn.essatin.erp.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Etage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String Designantion;
    @OneToOne
    private Batiment batiment;

    public Etage() {
    }

    public Etage(Integer id, String designantion, Batiment batiment) {
        Id = id;
        Designantion = designantion;
        this.batiment = batiment;
    }

    public Etage(String designantion, Batiment batiment) {
        Designantion = designantion;
        this.batiment = batiment;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getDesignantion() {
        return Designantion;
    }

    public void setDesignantion(String designantion) {
        Designantion = designantion;
    }

    public Batiment getBatiment() {
        return batiment;
    }

    public void setBatiment(Batiment batiment) {
        this.batiment = batiment;
    }

    @Override
    public String toString() {
        return "Etage{" +
                "Id=" + Id +
                ", Designantion='" + Designantion + '\'' +
                ", batiment=" + batiment +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Etage)) return false;
        Etage etage = (Etage) o;
        return getId().equals(etage.getId()) && getDesignantion().equals(etage.getDesignantion()) && getBatiment().equals(etage.getBatiment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDesignantion(), getBatiment());
    }
}
