package tn.essatin.erp.model.financier;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idContrat;
    @ManyToOne
    private Salarie idSalarie;
    @ManyToOne
    private ModeleContrat idModeleContrat;
    @ManyToOne
    private TypeContrat idtypeContrat;
    private LocalDate dateDebutContrat;
    private LocalDate dateFinContrat;
    private LocalDate dateSignatureContrat;
    private LocalDate dateResiliationContrat;
    private String observationContrat;

    public Contrat(Salarie idSalarie, ModeleContrat idModeleContrat, TypeContrat idtypeContrat,
                   LocalDate dateDebutContrat, LocalDate dateFinContrat, LocalDate dateSignatureContrat,
                   LocalDate dateResiliationContrat, String observationContrat) {
        this.idSalarie = idSalarie;
        this.idModeleContrat = idModeleContrat;
        this.idtypeContrat = idtypeContrat;
        this.dateDebutContrat = dateDebutContrat;
        this.dateFinContrat = dateFinContrat;
        this.dateSignatureContrat = dateSignatureContrat;
        this.dateResiliationContrat = dateResiliationContrat;
        this.observationContrat = observationContrat;
    }

    public Contrat() {
    }

    public Integer getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(Integer idContrat) {
        this.idContrat = idContrat;
    }

    public Salarie getIdSalarie() {
        return idSalarie;
    }

    public void setIdSalarie(Salarie idSalarie) {
        this.idSalarie = idSalarie;
    }

    public ModeleContrat getIdModeleContrat() {
        return idModeleContrat;
    }

    public void setIdModeleContrat(ModeleContrat idModeleContrat) {
        this.idModeleContrat = idModeleContrat;
    }

    public TypeContrat getIdtypeContrat() {
        return idtypeContrat;
    }

    public void setIdtypeContrat(TypeContrat idtypeContrat) {
        this.idtypeContrat = idtypeContrat;
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

    public String getObservationContrat() {
        return observationContrat;
    }

    public void setObservationContrat(String observationContrat) {
        this.observationContrat = observationContrat;
    }

    @Override
    public String toString() {
        return "Contrat{" +
                "idContrat=" + idContrat +
                ", idSalarie=" + idSalarie +
                ", idModeleContrat=" + idModeleContrat +
                ", idtypeContrat=" + idtypeContrat +
                ", dateDebutContrat=" + dateDebutContrat +
                ", dateFinContrat=" + dateFinContrat +
                ", dateSignatureContrat=" + dateSignatureContrat +
                ", dateResiliationContrat=" + dateResiliationContrat +
                ", observationContrat='" + observationContrat + '\'' +
                '}';
    }
}
