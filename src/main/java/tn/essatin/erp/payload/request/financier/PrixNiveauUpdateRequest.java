package tn.essatin.erp.payload.request.financier;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class PrixNiveauUpdateRequest {
    @Min(value = 1, message = "l'ID ne peut etre inferieur a 1")
    int id;
    @NotNull(message="id niveau ne peut etre null")
    private int idNiveau;
    @NotNull(message="id session ne peut etre null")
    private int idSession;
    @NotNull(message="montant ne peut etre null")
    private Double montant;
    private LocalDate date;

    public PrixNiveauUpdateRequest(int id, int idNiveau, int idSession, Double montant, LocalDate date) {
        this.id = id;
        this.idNiveau = idNiveau;
        this.idSession = idSession;
        this.montant = montant;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(int idNiveau) {
        this.idNiveau = idNiveau;
    }

    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
