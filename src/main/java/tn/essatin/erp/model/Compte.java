package tn.essatin.erp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String login;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private Personne idPersonne;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "compte_role", joinColumns = @JoinColumn(name = "compte_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public Compte(){}

    public Compte(Integer id, String login, String password, Personne idPersonne, Set<Role> roles) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.idPersonne = idPersonne;
        this.roles = roles;
    }

    public Compte(String login, String password, Personne idPersonne) {
        this.login = login;
        this.password = password;
        this.idPersonne = idPersonne;
    }

    public Compte(String login, String password, Optional<Personne> personne, String r) {
    }
    public Compte(String login, String password, String idPersonne, String r) {
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

    public Personne getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Personne idPersonne) {
        this.idPersonne = idPersonne;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "ID_Compte=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", Id_Personne=" + idPersonne +
                '}';
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
