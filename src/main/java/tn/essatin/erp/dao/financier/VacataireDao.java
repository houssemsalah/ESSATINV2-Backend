package tn.essatin.erp.dao.financier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.financier.Vacataire;

@Repository
public interface VacataireDao extends JpaRepository<Vacataire, Integer> {
}
