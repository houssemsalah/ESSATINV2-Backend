package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.EtatInscription;

@Repository
public interface EtatInscriptionDao extends JpaRepository<EtatInscription, Integer> {
    EtatInscription findByNom(String nom);
}
