package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Niveau;
import tn.essatin.erp.model.NiveauSuivant;

import java.util.List;

@Repository
public interface NiveauSuivantDao extends JpaRepository<NiveauSuivant, Integer> {
     List<NiveauSuivant> findByIdNiveau(Niveau idNiveaux);
}
