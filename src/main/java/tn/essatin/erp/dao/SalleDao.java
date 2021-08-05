package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Salle;

@Repository
public interface SalleDao extends JpaRepository<Salle,Integer> {
}
