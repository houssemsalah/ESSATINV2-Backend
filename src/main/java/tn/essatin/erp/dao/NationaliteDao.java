package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Nationalite;

@Repository
public interface NationaliteDao extends JpaRepository<Nationalite, Integer> {
    Nationalite findByLibelle(String libelle);
}
