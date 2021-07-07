package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Etudiants;
import tn.essatin.erp.model.Personne;

import java.util.Optional;

@Repository
public interface EtudiantsDao extends JpaRepository<Etudiants, Integer> {
    Optional<Etudiants> findByIdPersonne(Personne personne);


}
