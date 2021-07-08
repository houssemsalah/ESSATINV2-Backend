package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.ContactEtudiant;
import tn.essatin.erp.model.Etudiants;

import java.util.List;

@Repository
public interface ContactEtudiantDao extends JpaRepository<ContactEtudiant, Integer> {
    List<ContactEtudiant> findByIdEtudiant(Etudiants etudiants);
}
