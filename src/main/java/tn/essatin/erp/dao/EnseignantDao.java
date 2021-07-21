package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Enseignant;

import java.util.Optional;

@Repository
public interface EnseignantDao extends JpaRepository<Enseignant, Integer> {


}
