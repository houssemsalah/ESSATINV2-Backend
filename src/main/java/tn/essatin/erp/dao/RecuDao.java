package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Recus;

@Repository
public interface RecuDao extends JpaRepository<Recus, Long> {
}
