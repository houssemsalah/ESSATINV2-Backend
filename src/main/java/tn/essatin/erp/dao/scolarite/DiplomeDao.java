package tn.essatin.erp.dao.scolarite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Scolarite.Diplome;

@Repository
public interface DiplomeDao extends JpaRepository<Diplome, Integer> {
}
