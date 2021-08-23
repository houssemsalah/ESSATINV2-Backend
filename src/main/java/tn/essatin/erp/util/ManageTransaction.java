package tn.essatin.erp.util;

import org.springframework.stereotype.Component;
import tn.essatin.erp.dao.PersonneDao;
import tn.essatin.erp.dao.SessionDao;
import tn.essatin.erp.dao.financier.EmployerDao;
import tn.essatin.erp.dao.financier.ModaliteTransactionDao;
import tn.essatin.erp.dao.financier.PrixNiveauParSessionDao;
import tn.essatin.erp.dao.financier.TransactionDao;
import tn.essatin.erp.dao.scolarite.EnregistrementDao;
import tn.essatin.erp.dao.scolarite.EtudiantsDao;
import tn.essatin.erp.dao.scolarite.InscriptionDao;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.model.financier.ETypeTransaction;
import tn.essatin.erp.model.financier.Employer;
import tn.essatin.erp.model.financier.ModaliteTransaction;
import tn.essatin.erp.model.financier.Transaction;

import java.time.LocalDate;
import java.util.Set;

@Component
public class ManageTransaction {
    final TransactionDao transactionDao;
    final ModaliteTransactionDao modaliteTransactionDao;

    public ManageTransaction(TransactionDao transactionDao, ModaliteTransactionDao modaliteTransactionDao) {
        this.transactionDao = transactionDao;
        this.modaliteTransactionDao = modaliteTransactionDao;
    }

    public void add(ETypeTransaction typeTransaction, Set<ModaliteTransaction> modalite, Personne personne,
                    Session session, Employer financier) {
        Transaction transaction = new Transaction(typeTransaction, LocalDate.now(), financier,
                personne, session);
        transactionDao.save(transaction);
        for (ModaliteTransaction m : modalite)
            m.setTransaction(transaction);
        modaliteTransactionDao.saveAll(modalite);
    }
}
