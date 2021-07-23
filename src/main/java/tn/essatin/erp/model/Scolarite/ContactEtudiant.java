package tn.essatin.erp.model.Scolarite;

import javax.persistence.*;

@Entity
public class ContactEtudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idContact;
    @ManyToOne
    private Etudiants idEtudiant;
    private String numero;
    private String nom;
    private String designation;

    public ContactEtudiant(Etudiants idEtudiant, String numero, String nom, String designation) {

        this.idEtudiant = idEtudiant;
        this.numero = numero;
        this.nom = nom;
        this.designation = designation;
    }

    public ContactEtudiant() {
    }

    public Integer getIdContact() {
        return idContact;
    }

    public void setIdContact(Integer idContact) {
        this.idContact = idContact;
    }

    public Etudiants getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Etudiants idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "ContactEtudiant{" +
                "idContact=" + idContact +
                ", idEtudiant=" + idEtudiant +
                ", numero='" + numero + '\'' +
                ", nom='" + nom + '\'' +
                ", designation='" + designation + '\'' +
                '}';
    }
}
