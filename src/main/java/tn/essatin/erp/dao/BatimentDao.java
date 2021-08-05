package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Batiment;

@Repository

public interface BatimentDao extends JpaRepository<Batiment, Integer> {
}
