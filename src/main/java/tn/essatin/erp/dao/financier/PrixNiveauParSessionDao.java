package tn.essatin.erp.dao.financier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Scolarite.Niveau;
import tn.essatin.erp.model.financier.PrixNiveauParSession;
import tn.essatin.erp.model.Session;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PrixNiveauParSessionDao extends JpaRepository<PrixNiveauParSession, Integer> {
    Collection<PrixNiveauParSession> findAllBySession(Session session);
    List<PrixNiveauParSession> findBySessionAndNiveau(Session session, Niveau niveau);
    Optional<PrixNiveauParSession> findTopBySessionAndNiveauOrderByDateDesc(Session session, Niveau niveau);
    List<PrixNiveauParSession> findByNiveau(Niveau niveau);
}
