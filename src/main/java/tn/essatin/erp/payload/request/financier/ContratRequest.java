package tn.essatin.erp.payload.request.financier;

import org.springframework.format.annotation.DateTimeFormat;
import tn.essatin.erp.model.financier.ETypeContrat;
import tn.essatin.erp.model.financier.EUniteSalaire;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

public class ContratRequest {
    @NotBlank(message = "Ennumeration de type 'ETypeContrat'")
    private ETypeContrat typeContrat;
    @NotBlank(message = "Ennumeration de type 'EUniteSalaire'")
    private EUniteSalaire uniteSalaire;
    @NotNull(message="prixUnite ne peut etre null")
    private Double prixUnite;
    private LocalDate dateDebutContrat;
    private LocalDate dateFinContrat;
    private LocalDate dateSignatureContrat;
    private LocalDate dateResiliationContrat;
    private String observation;
    private int idEmployer;

    public int getIdEmployer() {
        return idEmployer;
    }

    public ContratRequest(ETypeContrat typeContrat, EUniteSalaire uniteSalaire,
                          Double prixUnite, LocalDate dateDebutContrat, LocalDate dateFinContrat,
                          LocalDate dateSignatureContrat, LocalDate dateResiliationContrat, String observation, int idEmployer) {
        this.typeContrat = typeContrat;
        this.uniteSalaire = uniteSalaire;
        this.prixUnite = prixUnite;
        this.dateDebutContrat = dateDebutContrat;
        this.dateFinContrat = dateFinContrat;
        this.dateSignatureContrat = dateSignatureContrat;
        this.dateResiliationContrat = dateResiliationContrat;
        this.observation = observation;
        this.idEmployer = idEmployer;
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

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public void setIdEmployer(int idEmployer) {
        this.idEmployer = idEmployer;
    }
}
