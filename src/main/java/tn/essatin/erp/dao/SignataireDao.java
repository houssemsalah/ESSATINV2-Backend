package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.model.Signataire;

@Repository
public interface SignataireDao extends JpaRepository<Signataire, Integer> {
}
