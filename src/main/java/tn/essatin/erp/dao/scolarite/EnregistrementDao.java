package tn.essatin.erp.dao.scolarite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Scolarite.Enregistrement;
import tn.essatin.erp.model.Scolarite.Inscription;
import tn.essatin.erp.model.Scolarite.Niveau;
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
