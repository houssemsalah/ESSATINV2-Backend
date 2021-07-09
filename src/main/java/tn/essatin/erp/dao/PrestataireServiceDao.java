package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.PrestataireService;

@Repository
public interface PrestataireServiceDao extends JpaRepository<PrestataireService, Integer> {
}
