package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Cycle;

@Repository
public interface CycleDao extends JpaRepository<Cycle, Integer> {
    Cycle findByDescription(String description);

}
