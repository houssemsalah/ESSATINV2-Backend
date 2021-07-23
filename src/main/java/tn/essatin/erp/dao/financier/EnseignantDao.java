package tn.essatin.erp.dao.financier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.financier.Enseignant;

@Repository
public interface EnseignantDao extends JpaRepository<Enseignant, Integer> {


}
