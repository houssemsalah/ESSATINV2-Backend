package tn.essatin.erp.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Enseignant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEnseignant;
    private String CNSS;
    private String CNRPS;
    private LocalDate dateEntree;
    private String observation;
    private String situationM;
    private int nbrEnfants;
    private String diplome;
    private String ripIBAN;
    private String img;
    private String poste;
    private String etablissementOrigine;
    @ManyToOne
    private Personne idPersonne;
    @ManyToOne
    private Salarie idSalarie;

    public Enseignant(String CNSS, String CNRPS,
                      LocalDate dateEntree, String observation, String situationM,
                      int nbrEnfants, String diplome, String ripIBAN, String img,
                      String poste, String etablissementOrigine, Personne idPersonne,
                      Salarie idSalarie) {

        this.CNSS = CNSS;
        this.CNRPS = CNRPS;
        this.dateEntree = dateEntree;
        this.observation = observation;
        this.situationM = situationM;
        this.nbrEnfants = nbrEnfants;
        this.diplome = diplome;
        this.ripIBAN = ripIBAN;
        this.img = img;
        this.poste = poste;
        this.etablissementOrigine = etablissementOrigine;
        this.idPersonne = idPersonne;
        this.idSalarie = idSalarie;
    }

    public Enseignant() {
    }

    public Integer getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(Integer idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public String getCNSS() {
        return CNSS;
    }

    public void setCNSS(String CNSS) {
        this.CNSS = CNSS;
    }

    public String getCNRPS() {
        return CNRPS;
    }

    public void setCNRPS(String CNRPS) {
        this.CNRPS = CNRPS;
    }

    public LocalDate getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(LocalDate dateEntree) {
        this.dateEntree = dateEntree;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getSituationM() {
        return situationM;
    }

    public void setSituationM(String situationM) {
        this.situationM = situationM;
    }

    public int getNbrEnfants() {
        return nbrEnfants;
    }

    public void setNbrEnfants(int nbrEnfants) {
        this.nbrEnfants = nbrEnfants;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getRipIBAN() {
        return ripIBAN;
    }

    public void setRipIBAN(String ripIBAN) {
        this.ripIBAN = ripIBAN;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getEtablissementOrigine() {
        return etablissementOrigine;
    }

    public void setEtablissementOrigine(String etablissementOrigine) {
        this.etablissementOrigine = etablissementOrigine;
    }

    public Personne getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Personne idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Salarie getIdSalarie() {
        return idSalarie;
    }

    public void setIdSalarie(Salarie idSalarie) {
        this.idSalarie = idSalarie;
    }

    @Override
    public String toString() {
        return "Enseignant{" +
                "idEnseignant=" + idEnseignant +
                ", CNSS='" + CNSS + '\'' +
                ", CNRPS='" + CNRPS + '\'' +
                ", dateEntree=" + dateEntree +
                ", observation='" + observation + '\'' +
                ", situationM='" + situationM + '\'' +
                ", nbrEnfants=" + nbrEnfants +
                ", diplome='" + diplome + '\'' +
                ", ripIBAN='" + ripIBAN + '\'' +
                ", img='" + img + '\'' +
                ", poste='" + poste + '\'' +
                ", etablissementOrigine='" + etablissementOrigine + '\'' +
                ", idPersonne=" + idPersonne +
                ", idSalarie=" + idSalarie +
                '}';
    }
}
