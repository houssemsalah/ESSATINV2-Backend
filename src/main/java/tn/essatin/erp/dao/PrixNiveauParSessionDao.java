package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Niveau;
import tn.essatin.erp.model.PrixNiveauParSession;
import tn.essatin.erp.model.Session;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface PrixNiveauParSessionDao extends JpaRepository<PrixNiveauParSession, Integer> {
    Collection<PrixNiveauParSession> findAllBySession(Session session);

    Optional<PrixNiveauParSession> findBySessionAndNiveau(Session session, Niveau niveau);
}
