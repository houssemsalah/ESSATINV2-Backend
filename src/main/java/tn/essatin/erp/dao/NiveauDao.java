package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Niveau;
import tn.essatin.erp.model.Parcours;

import java.util.List;

@Repository
public interface NiveauDao extends JpaRepository<Niveau, Integer> {
    Niveau findByDescription(String description);
    List<Niveau> findByParcours(Parcours parcours);
}
