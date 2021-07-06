package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.ModaliteTransaction;

@Repository
public interface ModaliteTransactionDao extends JpaRepository<ModaliteTransaction, Integer> {

}
