package tn.essatin.erp.model;

import javax.persistence.*;
@Entity
public class TypeDeNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private ETypeDeNote typeDeNote;

    public TypeDeNote() {
    }

    public TypeDeNote(Integer id, ETypeDeNote typeDeNote) {
        this.id = id;
        this.typeDeNote = typeDeNote;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ETypeDeNote getTypeDeNote() {
        return typeDeNote;
    }

    public void setTypeDeNote(ETypeDeNote typeDeNote) {
        this.typeDeNote = typeDeNote;
    }
}
