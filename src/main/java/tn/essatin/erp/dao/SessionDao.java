package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Session;


@Repository
public interface SessionDao extends JpaRepository<Session, Integer> {
    Session findBySession(String session);
    Session findTopByOrderByIdSessionDesc();
}
