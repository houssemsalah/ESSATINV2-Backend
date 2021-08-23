package tn.essatin.erp.model.financier;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class DateDesactivationEtudiants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String JourDesactivation;
    private String moisDesactivation;
    private double pourcentagePayement;
    private String observation;


    public DateDesactivationEtudiants() {
    }

    public DateDesactivationEtudiants(String jourDesactivation, String moisDesactivation, double pourcentagePayement, String observation) {
        JourDesactivation = jourDesactivation;
        this.moisDesactivation = moisDesactivation;
        this.pourcentagePayement = pourcentagePayement;
        this.observation = observation;
    }

    public DateDesactivationEtudiants(Integer id, String jourDesactivation, String moisDesactivation, double pourcentagePayement, String observation) {
        this.id = id;
        JourDesactivation = jourDesactivation;
        this.moisDesactivation = moisDesactivation;
        this.pourcentagePayement = pourcentagePayement;
        this.observation = observation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJourDesactivation() {
        return JourDesactivation;
    }

    public void setJourDesactivation(String jourDesactivation) {
        JourDesactivation = jourDesactivation;
    }

    public String getMoisDesactivation() {
        return moisDesactivation;
    }

    public void setMoisDesactivation(String moisDesactivation) {
        this.moisDesactivation = moisDesactivation;
    }

    public double getPourcentagePayement() {
        return pourcentagePayement;
    }

    public void setPourcentagePayement(double pourcentagePayement) {
        this.pourcentagePayement = pourcentagePayement;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    @Override
    public String toString() {
        return "DateDesactivationEtudiants{" +
                "id=" + id +
                ", JourDesactivation='" + JourDesactivation + '\'' +
                ", moisDesactivation='" + moisDesactivation + '\'' +
                ", pourcentagePayement=" + pourcentagePayement +
                ", observation='" + observation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DateDesactivationEtudiants)) return false;
        DateDesactivationEtudiants that = (DateDesactivationEtudiants) o;
        return Double.compare(that.getPourcentagePayement(), getPourcentagePayement()) == 0 && getId().equals(that.getId()) && getJourDesactivation().equals(that.getJourDesactivation()) && getMoisDesactivation().equals(that.getMoisDesactivation()) && getObservation().equals(that.getObservation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getJourDesactivation(), getMoisDesactivation(), getPourcentagePayement(), getObservation());
    }
}
