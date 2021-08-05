package tn.essatin.erp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Batiment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String designantion;

    public Batiment() {
    }

    public Batiment(Integer id, String designantion) {
        Id = id;
        this.designantion = designantion;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getDesignantion() {
        return designantion;
    }

    public void setDesignantion(String designantion) {
        this.designantion = designantion;
    }

    @Override
    public String toString() {
        return "Batiment{" +
                "Id=" + Id +
                ", designantion='" + designantion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Batiment)) return false;
        Batiment batiment = (Batiment) o;
        return Objects.equals(getId(), batiment.getId()) && Objects.equals(getDesignantion(), batiment.getDesignantion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDesignantion());
    }
}
