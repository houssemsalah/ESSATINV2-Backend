package tn.essatin.erp.payload.request.financier;

import tn.essatin.erp.model.financier.ETypeContrat;
import tn.essatin.erp.model.financier.EUniteSalaire;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ContratUpdateRequest {
    @Min(value = 1, message = "l'ID ne peut etre inferieur a 1")
    int id;
    @NotBlank(message = "Ennumeration de type 'ETypeContrat'")
    private ETypeContrat typeContrat;
    @NotBlank(message = "Ennumeration de type 'EUniteSalaire'")
    private EUniteSalaire uniteSalaire;
    @NotNull(message = "prixUnite ne peut etre null")
    private  Double prixUnite;
    private  LocalDate dateDebutContrat;
    private  LocalDate dateFinContrat;
    private  LocalDate dateSignatureContrat;
    private  LocalDate dateResiliationContrat;
    private  String observation;

    public ContratUpdateRequest(int id, ETypeContrat typeContrat, EUniteSalaire uniteSalaire,
                                Double prixUnite, LocalDate dateDebutContrat, LocalDate dateFinContrat,
                                LocalDate dateSignatureContrat, LocalDate dateResiliationContrat,
                                String observation) {
        this.id = id;
        this.typeContrat = typeContrat;
        this.uniteSalaire = uniteSalaire;
        this.prixUnite = prixUnite;
        this.dateDebutContrat = dateDebutContrat;
        this.dateFinContrat = dateFinContrat;
        this.dateSignatureContrat = dateSignatureContrat;
        this.dateResiliationContrat = dateResiliationContrat;
        this.observation = observation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
