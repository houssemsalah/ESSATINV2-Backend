package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Reçus;

@Repository
public interface ReçuDao extends JpaRepository<Reçus, Long> {
}
