package tn.essatin.erp.payload.request.financier;

import tn.essatin.erp.model.financier.ETypeContrat;
import tn.essatin.erp.model.financier.EUniteSalaire;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class ContratRequest {
    private ETypeContrat typeContrat;
    private EUniteSalaire uniteSalaire;
    private Double prixUnite;
    private LocalDate dateDebutContrat;
    private LocalDate dateFinContrat;
    private LocalDate dateSignatureContrat;
    private LocalDate dateResiliationContrat;
    private String observationContrat;
}
