package tn.essatin.erp.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String Designantion;
    private int nombreDePlace;
    private int NombreDePlaceExamen;
    private int nombreDeRangee;
    private String description;
    @OneToOne
    Etage etage;

    public Salle() {
    }

    public Salle(String designantion, int nombreDePlace, int nombreDePlaceExamen, int nombreDeRangee, Etage etage) {
        Designantion = designantion;
        this.nombreDePlace = nombreDePlace;
        NombreDePlaceExamen = nombreDePlaceExamen;
        this.nombreDeRangee = nombreDeRangee;
        this.etage = etage;
    }

    public Salle(Integer id, String designantion, int nombreDePlace, int nombreDePlaceExamen, int nombreDeRangee, Etage etage) {
        Id = id;
        Designantion = designantion;
        this.nombreDePlace = nombreDePlace;
        NombreDePlaceExamen = nombreDePlaceExamen;
        this.nombreDeRangee = nombreDeRangee;
        this.etage = etage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getNombreDePlace() {
        return nombreDePlace;
    }

    public void setNombreDePlace(int nombreDePlace) {
        this.nombreDePlace = nombreDePlace;
    }

    public int getNombreDePlaceExamen() {
        return NombreDePlaceExamen;
    }

    public void setNombreDePlaceExamen(int nombreDePlaceExamen) {
        NombreDePlaceExamen = nombreDePlaceExamen;
    }

    public int getNombreDeRangee() {
        return nombreDeRangee;
    }

    public void setNombreDeRangee(int nombreDeRangee) {
        this.nombreDeRangee = nombreDeRangee;
    }

    public Etage getEtage() {
        return etage;
    }

    public void setEtage(Etage etage) {
        this.etage = etage;
    }

    @Override
    public String toString() {
        return "Salle{" +
                "Id=" + Id +
                ", Designantion='" + Designantion + '\'' +
                ", nombreDePlace=" + nombreDePlace +
                ", NombreDePlaceExamen=" + NombreDePlaceExamen +
                ", nombreDeRangee=" + nombreDeRangee +
                ", description='" + description + '\'' +
                ", etage=" + etage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Salle)) return false;
        Salle salle = (Salle) o;
        return getNombreDePlace() == salle.getNombreDePlace() && getNombreDePlaceExamen() == salle.getNombreDePlaceExamen() && getNombreDeRangee() == salle.getNombreDeRangee() && getId().equals(salle.getId()) && getDesignantion().equals(salle.getDesignantion()) && getDescription().equals(salle.getDescription()) && getEtage().equals(salle.getEtage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDesignantion(), getNombreDePlace(), getNombreDePlaceExamen(), getNombreDeRangee(), getDescription(), getEtage());
    }
}
