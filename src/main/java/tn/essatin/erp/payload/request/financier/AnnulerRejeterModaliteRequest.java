package tn.essatin.erp.payload.request.financier;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class AnnulerRejeterModaliteRequest {

    @Min(value = 1,message="idModalite doit être strictement positif")
    int idModalite;
    @Min(value = 1,message="idCompteFinancier doit être strictement positif")
    int idCompteFinancier;
    @NotBlank
    String motif;

    public AnnulerRejeterModaliteRequest(int idModalite, int idCompteFinancier, String motif) {
        this.idModalite = idModalite;
        this.idCompteFinancier = idCompteFinancier;
        this.motif = motif;
    }

    public int getIdModalite() {
        return idModalite;
    }

    public int getIdCompteFinancier() {
        return idCompteFinancier;
    }

    public String getMotif() {
        return motif;
    }

    @Override
    public String toString() {
        return "AnnulerRejeterModaliteRequest{" +
                "idModalite=" + idModalite +
                ", idCompteFinancier=" + idCompteFinancier +
                ", motif='" + motif + '\'' +
                '}';
    }
}
