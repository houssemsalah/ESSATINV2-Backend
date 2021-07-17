package tn.essatin.erp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String login;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private Personne Id_Personne;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "compte_role", joinColumns = @JoinColumn(name = "compte_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Compte(String login, String password, Personne id_Personne) {
        this.login = login;
        this.password = password;
        Id_Personne = id_Personne;
    }

    public Compte() {
    }

    public Integer getId() {
        return id;
    }

    public void setID_Compte(Integer ID_Compte) {
        this.id = ID_Compte;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Personne getId_Personne() {
        return Id_Personne;
    }

    public void setId_Personne(Personne id_Personne) {
        Id_Personne = id_Personne;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "ID_Compte=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", Id_Personne=" + Id_Personne +
                '}';
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
