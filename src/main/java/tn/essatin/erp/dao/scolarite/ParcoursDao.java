package tn.essatin.erp.dao.scolarite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Scolarite.Parcours;
import tn.essatin.erp.model.Scolarite.Specialite;

import java.util.List;

@Repository
public interface ParcoursDao extends JpaRepository<Parcours, Integer> {
    Parcours findByDescription(String description);
    List<Parcours> findBySpecialite(Specialite specialite);

}
