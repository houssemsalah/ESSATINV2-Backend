package tn.essatin.erp.payload.request.financier;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ModaliteByTransactionRequest {
    @Min(value = 1,message = "id modalite est strictemt positif")
    private Integer id;
    @NotBlank(message = "la numero ne peut etre vide")
    private String numero;
    @NotNull(message = "montant ne peut etre null")
    private double montant;
    @NotBlank(message = "le type de modalité ne peut etre vide")
    private String type;
    private LocalDate date;

    public ModaliteByTransactionRequest(Integer id, String numero, double montant, String type, LocalDate date) {
        this.id = id;
        this.numero = numero;
        this.montant = montant;
        this.type = type;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
