package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.ContactEtudiant;

@Repository
public interface ContactEtudiantDao extends JpaRepository<ContactEtudiant, Integer> {
}
