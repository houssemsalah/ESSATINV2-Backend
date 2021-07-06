package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Cycle;
import tn.essatin.erp.model.Specialite;

import java.util.List;

@Repository
public interface SpecialiteDao extends JpaRepository<Specialite, Integer> {
    Specialite findByDescription(String description);
    List<Specialite> findAllByCycle(Cycle cycle);
}
