package tn.essatin.erp.dao.financier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.financier.ETypeModaliteTransaction;
import tn.essatin.erp.model.financier.ETypeTransaction;
import tn.essatin.erp.model.financier.ModaliteTransaction;
import tn.essatin.erp.model.financier.Transaction;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModaliteTransactionDao extends JpaRepository<ModaliteTransaction, Integer> {

    List<ModaliteTransaction> findModaliteTransactionByTransaction(Transaction transaction);

    Optional<ModaliteTransaction> findModaliteTransactionByNumeroAndType(String numero, ETypeModaliteTransaction type);
}
