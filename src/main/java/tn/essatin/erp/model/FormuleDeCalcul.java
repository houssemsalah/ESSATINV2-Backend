package tn.essatin.erp.model;

import tn.essatin.erp.model.Scolarite.Cycle;
import tn.essatin.erp.model.TypeDeNote;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class FormuleDeCalcul {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFormule;

    @ManyToOne
    private Cycle cycle;
    @ManyToOne
    Session session;

    //   @ManyToMany(cascade = CascadeType.ALL)
    //   private Set<TypeDeNote> typeDeNote= new HashSet<>();


    // private Double[] coeficients

    public FormuleDeCalcul() {
    }

  /*  public Set<TypeDeNote> getTypeDeNote() {
        return typeDeNote;
    }

    public void setTypeDeNote(Set<TypeDeNote> typeDeNote) {
        this.typeDeNote = typeDeNote;
    }
    */
}
