package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Etudiants;
import tn.essatin.erp.model.Inscription;

import java.util.List;

@Repository
public interface InscriptionDao extends JpaRepository<Inscription, Integer> {
    List<Inscription> findAllByIdEtudiant(Etudiants idEtudiant);
    Inscription findByIdEtudiantAndTopByOrderByDateDesc(Etudiants idEtudiant);
    int countAllByNumeroInscriptionEndsWith(String suffixInscription);
}
