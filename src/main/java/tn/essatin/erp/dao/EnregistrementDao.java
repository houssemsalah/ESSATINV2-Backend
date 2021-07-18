package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Enregistrement;
import tn.essatin.erp.model.Inscription;
import tn.essatin.erp.model.Niveau;
import tn.essatin.erp.model.Session;

import java.util.List;

@Repository
public interface EnregistrementDao extends JpaRepository<Enregistrement, Integer> {
    Enregistrement findByIdInscriptionAndIdSession(Inscription idInscription, Session idSession);
    List<Enregistrement> findByIdNiveauAndIdSession(Niveau idNiveau, Session idSession);
    List<Enregistrement> findByIdSession(Session idsession);
    Enregistrement findTopByIdInscriptionOrderByIdEnregistrementDesc(Inscription idInscription);
    List<Enregistrement> findByIdInscription(Inscription inscription);

}
