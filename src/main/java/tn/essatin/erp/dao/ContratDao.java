package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Contrat;

@Repository
public interface ContratDao extends JpaRepository<Contrat, Integer> {

}
