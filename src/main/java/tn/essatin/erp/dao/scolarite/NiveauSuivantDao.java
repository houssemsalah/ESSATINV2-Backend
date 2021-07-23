package tn.essatin.erp.dao.scolarite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Scolarite.Niveau;
import tn.essatin.erp.model.Scolarite.NiveauSuivant;

import java.util.List;

@Repository
public interface NiveauSuivantDao extends JpaRepository<NiveauSuivant, Integer> {
     List<NiveauSuivant> findByIdNiveau(Niveau idNiveaux);
}
