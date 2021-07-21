package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Salarie;

@Repository
public interface SalarieDao extends JpaRepository<Salarie, Integer> {
}
