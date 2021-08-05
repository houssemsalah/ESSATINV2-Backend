package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Etage;
@Repository
public interface EtageDao extends JpaRepository<Etage,Integer> {
}
