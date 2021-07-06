package tn.essatin.erp.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tn.essatin.erp.model.Compte;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CompteDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    private final String password;
    private final Integer id;
    private final String login;
    private final Collection<? extends GrantedAuthority> authorities;
    private String firstname;
    private String lastname;

    public CompteDetailsImpl(String password, Integer id, String login, Collection<? extends GrantedAuthority> authorities, String firstname, String lastname) {
        this.password = password;
        this.id = id;
        this.login = login;
        this.authorities = authorities;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public static CompteDetailsImpl build(Compte compte) {
        List<GrantedAuthority> authorities = compte.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getRole().name()))
            .collect(Collectors.toList());
        return new CompteDetailsImpl(
            compte.getPassword(),
            compte.getId(),
            compte.getLogin(),
            authorities, compte.getId_Personne().getNom(), compte.getId_Personne().getPrenom());
    }

    public Integer getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompteDetailsImpl compte = (CompteDetailsImpl) o;
        return Objects.equals(id, compte.id);
    }


}
