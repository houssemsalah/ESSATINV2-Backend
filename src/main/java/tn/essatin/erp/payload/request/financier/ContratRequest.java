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
    private String typeContrat;
    @NotBlank(message = "Ennumeration de type 'EUniteSalaire'")
    private String uniteSalaire;
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



    public ContratRequest(String typeContrat, String uniteSalaire,
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
}
