package tn.essatin.erp.payload.request;

import javax.validation.constraints.Min;

public class GetByIdRequest {
    @Min(value = 1, message = "la variable 'id' doit avoir une valeur strictement supperieur a 0")
    int id;

    public int getId() {
        return id;
    }
}
