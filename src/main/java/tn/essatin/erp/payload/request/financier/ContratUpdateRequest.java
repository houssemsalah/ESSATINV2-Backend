package tn.essatin.erp.payload.request.financier;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ContratUpdateRequest {
    @Min(value = 1, message = "l'ID ne peut etre inferieur a 1")
    int id;
    @NotBlank(message = "Ennumeration de type 'ETypeContrat'")
    private final String typeContrat;
    @NotBlank(message = "Ennumeration de type 'EUniteSalaire'")
    private final String uniteSalaire;
    @NotNull(message = "prixUnite ne peut etre null")
    private final Double prixUnite;
    private final LocalDate dateDebutContrat;
    private final LocalDate dateFinContrat;
    private final LocalDate dateSignatureContrat;
    private final LocalDate dateResiliationContrat;
    private final String observation;

    public ContratUpdateRequest(int id, String typeContrat, String uniteSalaire, Double prixUnite,
                                LocalDate dateDebutContrat, LocalDate dateFinContrat,
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

    public String getTypeContrat() {
        return typeContrat;
    }

    public String getUniteSalaire() {
        return uniteSalaire;
    }

    public Double getPrixUnite() {
        return prixUnite;
    }

    public LocalDate getDateDebutContrat() {
        return dateDebutContrat;
    }

    public LocalDate getDateFinContrat() {
        return dateFinContrat;
    }

    public LocalDate getDateSignatureContrat() {
        return dateSignatureContrat;
    }

    public LocalDate getDateResiliationContrat() {
        return dateResiliationContrat;
    }

    public String getObservation() {
        return observation;
    }

    public int getId() {
        return id;
    }
}
