package tn.essatin.erp.payload.request;

import javax.validation.constraints.Min;

public class CycleRequest {
    @Min(value = 1, message = "le champ idcycle est obligatoire")
    private Integer idcycle;

    public Integer getIdcycle() {
        return idcycle;
    }
}
