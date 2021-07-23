package tn.essatin.erp.model.Scolarite;

import javax.persistence.*;

@Entity
public class DiplomeEtudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDiplomeEtudiant;
    @OneToOne
    private Diplome idDiplome;
    @ManyToOne
    private Etudiants idEtudiant;
    private String annee;
    private String specialite;
    private Integer niveau;
    private String status;
    private String etablissement;


    public DiplomeEtudiant(Diplome idDiplome, Etudiants idEtudiant,
                           String annee, String specialite, Integer niveau, String status,
                           String etablissement) {
        this.idDiplome = idDiplome;
        this.idEtudiant = idEtudiant;
        this.annee = annee;
        this.specialite = specialite;
        this.niveau = niveau;
        this.status = status;
        this.etablissement = etablissement;
    }

    public DiplomeEtudiant() {
    }

    public Integer getIdDiplomeEtudiant() {
        return idDiplomeEtudiant;
    }

    public void setIdDiplomeEtudiant(Integer idDiplomeEtudiant) {
        this.idDiplomeEtudiant = idDiplomeEtudiant;
    }

    public Diplome getIdDiplome() {
        return idDiplome;
    }

    public void setIdDiplome(Diplome idDiplome) {
        this.idDiplome = idDiplome;
    }

    public Etudiants getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Etudiants idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    @Override
    public String toString() {
        return "DiplomeEtudiant{" +
            "idDiplomeEtudiant=" + idDiplomeEtudiant +
            ", idDiplome=" + idDiplome +
            ", idEtudiant=" + idEtudiant +
            ", annee='" + annee + '\'' +
            ", specialite='" + specialite + '\'' +
            ", niveau=" + niveau +
            ", status='" + status + '\'' +
            ", etablissement='" + etablissement + '\'' +
            '}';
    }
}
