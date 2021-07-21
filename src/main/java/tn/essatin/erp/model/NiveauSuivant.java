package tn.essatin.erp.model;

import javax.persistence.*;

@Entity
public class NiveauSuivant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private Niveau idNiveau;
    @OneToOne
    private Niveau idNiveauSuivant;

    public NiveauSuivant(Niveau idNiveau, Niveau idNiveauSuivant) {
        this.idNiveau = idNiveau;
        this.idNiveauSuivant = idNiveauSuivant;
    }

    public NiveauSuivant() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Niveau getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(Niveau idNiveau) {
        this.idNiveau = idNiveau;
    }

    public Niveau getIdNiveauSuivant() {
        return idNiveauSuivant;
    }

    public void setIdNiveauSuivant(Niveau idNiveauSuivant) {
        this.idNiveauSuivant = idNiveauSuivant;
    }

    @Override
    public String toString() {
        return "NiveauSuivant{" +
                "id=" + id +
                ", idNiveau=" + idNiveau +
                ", idNiveauSuivant=" + idNiveauSuivant +
                '}';
    }
}
