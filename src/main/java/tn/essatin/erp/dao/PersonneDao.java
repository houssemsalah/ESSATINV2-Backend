package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Personne;

import java.util.Optional;

@Repository
public interface PersonneDao extends JpaRepository<Personne, Integer> {
    Boolean existsByNumeroIdentificateur(String numeroIdentificateur);
    Optional<Personne> findByNomAndPrenom(String nom, String prenom);
    Optional<Personne> findByNumeroIdentificateur(String identificateur);

}
