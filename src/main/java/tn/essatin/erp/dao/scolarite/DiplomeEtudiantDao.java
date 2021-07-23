package tn.essatin.erp.dao.scolarite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Scolarite.DiplomeEtudiant;
import tn.essatin.erp.model.Scolarite.Etudiants;

import java.util.List;

@Repository
public interface DiplomeEtudiantDao extends JpaRepository<DiplomeEtudiant, Integer> {
    List<DiplomeEtudiant> findByIdEtudiant(Etudiants etudiants);
}
