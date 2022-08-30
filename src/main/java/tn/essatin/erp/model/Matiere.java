package tn.essatin.erp.model;

import tn.essatin.erp.model.Scolarite.Niveau;
import tn.essatin.erp.model.financier.Enseignant;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class Matiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMatiere;
    @NotBlank
    private String nomMatiere;
    private String description;
    @ManyToMany
    @NotNull
    private Collection<Enseignant> enseignants;
    @OneToOne
    @NotNull
    private Niveau niveau;
    @Min(value = 0,message = "la coefficient ne peut pas etre inferieur a 0")
    private double coefficient;


    public Matiere() {
    }

    public Matiere(Integer idMatiere, String nomMatiere, String description, Collection<Enseignant> enseignants, Niveau niveau, double coefficient) {
        this.idMatiere = idMatiere;
        this.nomMatiere = nomMatiere;
        this.description = description;
        this.enseignants = enseignants;
        this.niveau = niveau;
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        return "Matiere{" +
                "idMatiere=" + idMatiere +
                ", nomMatiere='" + nomMatiere + '\'' +
                ", description='" + description + '\'' +
                ", enseignants=" + enseignants +
                ", niveau=" + niveau +
                ", coefficient=" + coefficient +
                '}';
    }

    public Integer getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(Integer idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Enseignant> getEnseignants() {
        return enseignants;
    }

    public void setEnseignants(Collection<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }


}
