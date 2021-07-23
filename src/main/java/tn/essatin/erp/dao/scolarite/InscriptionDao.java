package tn.essatin.erp.dao.scolarite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Scolarite.Etudiants;
import tn.essatin.erp.model.Scolarite.Inscription;

import java.util.List;

@Repository
public interface InscriptionDao extends JpaRepository<Inscription, Integer> {
    List<Inscription> findAllByIdEtudiant(Etudiants idEtudiant);
    Inscription findTopByIdEtudiantOrderByDateDesc(Etudiants idEtudiant);
    int countAllByNumeroInscriptionEndsWith(String suffixInscription);
}
