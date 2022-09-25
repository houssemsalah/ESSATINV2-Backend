package tn.essatin.erp.model;

import tn.essatin.erp.model.Scolarite.Etudiants;

import javax.persistence.*;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNote;
    private double note;
    @OneToOne
    private Matiere matiere;
    @OneToOne
    private Etudiants etudiant;
    @OneToOne
    private TypeDeNote typeDeNote;

    public Note() {
    }

    public Note(Integer idNote, Float note, Matiere matiere, Etudiants etudiant, TypeDeNote typeDeNote) {
        this.idNote = idNote;
        this.note = note;
        this.matiere = matiere;
        this.etudiant = etudiant;
        this.typeDeNote = typeDeNote;
    }

    public Integer getIdNote() {
        return idNote;
    }

    public void setIdNote(Integer idNote) {
        this.idNote = idNote;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Etudiants getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiants etudiant) {
        this.etudiant = etudiant;
    }

    public TypeDeNote getTypeDeNote() {
        return typeDeNote;
    }

    public void setTypeDeNote(TypeDeNote typeDeNote) {
        this.typeDeNote = typeDeNote;
    }

    @Override
    public String toString() {
        return "Note{" +
                "idNote=" + idNote +
                ", note=" + note +
                ", matiere=" + matiere +
                ", etudiant=" + etudiant +
                ", typeDeNote=" + typeDeNote +
                '}';
    }
}
