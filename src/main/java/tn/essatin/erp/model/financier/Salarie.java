package tn.essatin.erp.model.financier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Salarie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSalarie;
    private String typeSalarie;

    public Salarie(Integer idSalarie, String typeSalarie) {
        this.idSalarie = idSalarie;
        this.typeSalarie = typeSalarie;
    }

    public Salarie() {
    }

    public Integer getIdSalarie() {
        return idSalarie;
    }

    public void setIdSalarie(Integer idSalarie) {
        this.idSalarie = idSalarie;
    }

    public String getTypeSalarie() {
        return typeSalarie;
    }

    public void setTypeSalarie(String typeSalarie) {
        this.typeSalarie = typeSalarie;
    }

    @Override
    public String toString() {
        return "Salarie{" +
                "idSalarie=" + idSalarie +
                ", typeSalarie='" + typeSalarie + '\'' +
                '}';
    }
}
