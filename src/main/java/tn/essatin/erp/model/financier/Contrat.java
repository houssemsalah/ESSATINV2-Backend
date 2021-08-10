package tn.essatin.erp.model.financier;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private ETypeContrat typeContrat;
    @Enumerated(EnumType.STRING)
    private EUniteSalaire uniteSalaire;
    private Double prixUnite;
    private LocalDate dateDebutContrat;
    private LocalDate dateFinContrat;
    private LocalDate dateSignatureContrat;
    private LocalDate dateResiliationContrat;
    private String observation;
    @ManyToOne
    private Employer employer;

    public Contrat(ETypeContrat typeContrat, EUniteSalaire uniteSalaire, Double prixUnite,
                   LocalDate dateDebutContrat, LocalDate dateFinContrat, LocalDate dateSignatureContrat, LocalDate dateResiliationContrat, String observation,Employer employer) {
        this.typeContrat = typeContrat;
        this.uniteSalaire = uniteSalaire;
        this.prixUnite = prixUnite;
        this.dateDebutContrat = dateDebutContrat;
        this.dateFinContrat = dateFinContrat;
        this.dateSignatureContrat = dateSignatureContrat;
        this.dateResiliationContrat = dateResiliationContrat;
        this.observation = observation;
        this.employer = employer;
    }

    public Contrat() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ETypeContrat getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(ETypeContrat typeContrat) {
        this.typeContrat = typeContrat;
    }

    public EUniteSalaire getUniteSalaire() {
        return uniteSalaire;
    }

    public void setUniteSalaire(EUniteSalaire uniteSalaire) {
        this.uniteSalaire = uniteSalaire;
    }

    public Double getPrixUnite() {
        return prixUnite;
    }

    public void setPrixUnite(Double prixUnite) {
        this.prixUnite = prixUnite;
    }

    public LocalDate getDateDebutContrat() {
        return dateDebutContrat;
    }

    public void setDateDebutContrat(LocalDate dateDebutContrat) {
        this.dateDebutContrat = dateDebutContrat;
    }

    public LocalDate getDateFinContrat() {
        return dateFinContrat;
    }

    public void setDateFinContrat(LocalDate dateFinContrat) {
        this.dateFinContrat = dateFinContrat;
    }

    public LocalDate getDateSignatureContrat() {
        return dateSignatureContrat;
    }

    public void setDateSignatureContrat(LocalDate dateSignatureContrat) {
        this.dateSignatureContrat = dateSignatureContrat;
    }

    public LocalDate getDateResiliationContrat() {
        return dateResiliationContrat;
    }

    public void setDateResiliationContrat(LocalDate dateResiliationContrat) {
        this.dateResiliationContrat = dateResiliationContrat;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observationContrat) {
        this.observation = observationContrat;
    }

    @Override
    public String toString() {
        return "Contrat{" +
                "id=" + id +
                ", typeContrat=" + typeContrat +
                ", uniteSalaire=" + uniteSalaire +
                ", prixUnite=" + prixUnite +
                ", dateDebutContrat=" + dateDebutContrat +
                ", dateFinContrat=" + dateFinContrat +
                ", dateSignatureContrat=" + dateSignatureContrat +
                ", dateResiliationContrat=" + dateResiliationContrat +
                ", observation='" + observation + '\'' +
                ", employer=" + employer +
                '}';
    }
}
