package tn.essatin.erp.model.Scolarite;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Diplome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDiplome;
    private String nomDiplome;

    public Diplome(String nomDiplome) {
        this.nomDiplome = nomDiplome;
    }

    public Diplome() {
    }

    public Integer getIdDiplome() {
        return idDiplome;
    }

    public void setIdDiplome(Integer idDiplome) {
        this.idDiplome = idDiplome;
    }

    public String getNomDiplome() {
        return nomDiplome;
    }

    public void setNomDiplome(String nomDiplome) {
        this.nomDiplome = nomDiplome;
    }

    @Override
    public String toString() {
        return "Diplome{" +
                "idDiplome=" + idDiplome +
                ", nomDiplome='" + nomDiplome + '\'' +
                '}';
    }
}
