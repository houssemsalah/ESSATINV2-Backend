package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Diplome;

@Repository
public interface DiplomeDao extends JpaRepository<Diplome, Integer> {
}
