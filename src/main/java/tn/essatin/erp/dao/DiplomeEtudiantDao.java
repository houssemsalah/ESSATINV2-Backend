package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.DiplomeEtudiant;
import tn.essatin.erp.model.Etudiants;

import java.util.List;

@Repository
public interface DiplomeEtudiantDao extends JpaRepository<DiplomeEtudiant, Integer> {
    List<DiplomeEtudiant> findByIdEtudiant(Etudiants etudiants);
}
