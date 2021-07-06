package tn.essatin.erp.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class MotDePasseOublier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_MDPO;
    @OneToOne
    private Compte Id_Compte;
    private String Token;
    private LocalDate dateExpiration;

    public MotDePasseOublier(Integer id_MDPO, Compte id_Compte, String token, LocalDate dateExpiration) {
        Id_MDPO = id_MDPO;
        Id_Compte = id_Compte;
        Token = token;
        this.dateExpiration = dateExpiration;
    }

    public MotDePasseOublier() {
    }

    public Integer getId_MDPO() {
        return Id_MDPO;
    }

    public void setId_MDPO(Integer id_MDPO) {
        Id_MDPO = id_MDPO;
    }

    public Compte getId_Compte() {
        return Id_Compte;
    }

    public void setId_Compte(Compte id_Compte) {
        Id_Compte = id_Compte;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public LocalDate getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    @Override
    public String toString() {
        return "MotDePasseOublier{" +
            "Id_MDPO=" + Id_MDPO +
            ", Id_Compte=" + Id_Compte +
            ", Token='" + Token + '\'' +
            ", dateExpiration=" + dateExpiration +
            '}';
    }
}
