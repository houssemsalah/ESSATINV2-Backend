package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.model.Transaction;

import java.util.Collection;


@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer> {
    Collection<Transaction> findAllByIdClientAndSession(Personne idClient, Session session);
}
