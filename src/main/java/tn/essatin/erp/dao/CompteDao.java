package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Compte;
import tn.essatin.erp.model.Personne;

import java.util.Optional;

@Repository
public interface CompteDao extends JpaRepository<Compte, Integer> {
    Optional<Compte> findByLogin(String login);
    Optional<Compte> findByIdPersonne(Personne idPersonne);
    Boolean existsByLogin(String login);


}
