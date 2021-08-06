package tn.essatin.erp.payload.request.financier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ContratRequest {
    @NotNull
    private int idContrat;
    @NotBlank
    private List<Salarie> salaries;
    @NotBlank
    private List<ModeleContrat> modeleContrats;
    @NotBlank
    private List<TypeContrat>  typeContrats;
    @NotBlank
    private String dateDebut;
    @NotBlank
    private String dateFin;
    @NotBlank
    private String dateSingature;
    @NotBlank
    private String dateResiliation;
    @NotBlank
    private String observation;

    public int getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(int idContrat) {
        this.idContrat = idContrat;
    }

    public List<Salarie> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Salarie> salaries) {
        this.salaries = salaries;
    }

    public List<ModeleContrat> getModeleContrats() {
        return modeleContrats;
    }

    public void setModeleContrats(List<ModeleContrat> modeleContrats) {
        this.modeleContrats = modeleContrats;
    }

    public List<TypeContrat> getTypeContrats() {
        return typeContrats;
    }

    public void setTypeContrats(List<TypeContrat> typeContrats) {
        this.typeContrats = typeContrats;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getDateSingature() {
        return dateSingature;
    }

    public void setDateSingature(String dateSingature) {
        this.dateSingature = dateSingature;
    }

    public String getDateResiliation() {
        return dateResiliation;
    }

    public void setDateResiliation(String dateResiliation) {
        this.dateResiliation = dateResiliation;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
