package tn.essatin.erp.payload.request.scolarite;

import javax.validation.constraints.Min;

public class EnseignantRequest {
    @Min(value = 1, message = "la valeur 'id' ne peut etre null ou inferieur a 1")
    private int id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

