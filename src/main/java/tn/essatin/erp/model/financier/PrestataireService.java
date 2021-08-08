package tn.essatin.erp.model.financier;

import tn.essatin.erp.model.Personne;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class PrestataireService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private ETypePrestataire typePrestataire;
    @OneToOne
    private Personne Personne;

    public PrestataireService(Integer id, ETypePrestataire typePrestataire, tn.essatin.erp.model.Personne personne) {
        this.id = id;
        this.typePrestataire = typePrestataire;
        Personne = personne;
    }

    public PrestataireService(ETypePrestataire typePrestataire, tn.essatin.erp.model.Personne personne) {
        this.typePrestataire = typePrestataire;
        Personne = personne;
    }

    public PrestataireService() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ETypePrestataire getTypePrestataire() {
        return typePrestataire;
    }

    public void setTypePrestataire(ETypePrestataire typePrestataire) {
        this.typePrestataire = typePrestataire;
    }

    public tn.essatin.erp.model.Personne getPersonne() {
        return Personne;
    }

    public void setPersonne(tn.essatin.erp.model.Personne personne) {
        Personne = personne;
    }

    @Override
    public String toString() {
        return "PrestataireService{" +
                "id=" + id +
                ", typePrestataire=" + typePrestataire +
                ", Personne=" + Personne +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrestataireService)) return false;
        PrestataireService that = (PrestataireService) o;
        return getId().equals(that.getId()) &&
                getTypePrestataire() == that.getTypePrestataire() &&
                getPersonne().equals(that.getPersonne());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTypePrestataire(), getPersonne());
    }
}
