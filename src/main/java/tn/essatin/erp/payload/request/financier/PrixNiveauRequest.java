package tn.essatin.erp.payload.request.financier;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class PrixNiveauRequest {
    @NotNull(message="id niveau ne peut etre null")
    private int idNiveau;
    @NotNull(message="id session ne peut etre null")
    private int idSession;
    @NotNull(message="montant ne peut etre null")
    private Double montant;
    private LocalDate date;

    public PrixNiveauRequest(int idNiveau, int idSession, Double montant, LocalDate date) {
        this.idNiveau = idNiveau;
        this.idSession = idSession;
        this.montant = montant;
        this.date = date;
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
