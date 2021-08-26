package tn.essatin.erp.dao.financier;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.financier.ModaliteTransaction;
import tn.essatin.erp.model.financier.MotifAnnulationRejetModalite;

import java.util.List;
import java.util.Optional;

public interface MotifAnnulationRejetModaliteDao extends JpaRepository<MotifAnnulationRejetModalite, Integer> {
    Optional<MotifAnnulationRejetModalite> findByModaliteTransaction(ModaliteTransaction modaliteTransaction);
    List<MotifAnnulationRejetModalite> findByPersonneFinancier(Personne financier);
}
