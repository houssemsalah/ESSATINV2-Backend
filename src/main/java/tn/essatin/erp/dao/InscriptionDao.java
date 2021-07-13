package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Etudiants;
import tn.essatin.erp.model.Inscription;

@Repository
public interface InscriptionDao extends JpaRepository<Inscription, Integer> {
    Inscription findByIdEtudiant(Etudiants idEtudiant);
    int countAllByNumeroInscriptionEndsWith(String suffixInscription);
}
