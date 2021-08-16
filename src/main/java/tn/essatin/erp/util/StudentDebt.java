package tn.essatin.erp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tn.essatin.erp.dao.financier.ModaliteTransactionDao;
import tn.essatin.erp.dao.financier.PrixNiveauParSessionDao;
import tn.essatin.erp.dao.financier.TransactionDao;
import tn.essatin.erp.dao.scolarite.EnregistrementDao;
import tn.essatin.erp.dao.scolarite.InscriptionDao;
import tn.essatin.erp.model.Scolarite.Enregistrement;
import tn.essatin.erp.model.Scolarite.Etudiants;
import tn.essatin.erp.model.Scolarite.Inscription;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.model.financier.ModaliteTransaction;
import tn.essatin.erp.model.financier.PrixNiveauParSession;
import tn.essatin.erp.model.financier.Transaction;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class StudentDebt {
    final PrixNiveauParSessionDao prixNiveauParSessionDao;
    final TransactionDao transactionDao;
    final InscriptionDao inscriptionDao;
    final EnregistrementDao enregistrementDao;
    final ModaliteTransactionDao modaliteTransactionDao;

    @Autowired
    public StudentDebt(PrixNiveauParSessionDao prixNiveauParSessionDao, TransactionDao transactionDao, InscriptionDao inscriptionDao, EnregistrementDao enregistrementDao,ModaliteTransactionDao modaliteTransactionDao) {
        this.prixNiveauParSessionDao = prixNiveauParSessionDao;
        this.transactionDao = transactionDao;
        this.inscriptionDao = inscriptionDao;
        this.enregistrementDao = enregistrementDao;
        this.modaliteTransactionDao = modaliteTransactionDao;
    }

    public double debt(Etudiants etudiants, Session session) {
        Collection<Transaction> transactions = transactionDao.findAllByClientAndSession(etudiants.getIdPersonne(), session);
        Inscription inscription = inscriptionDao.findTopByIdEtudiantOrderByDateDesc(etudiants);
        Enregistrement enregistrement = enregistrementDao.findByIdInscriptionAndIdSession(inscription, session);
        Optional<PrixNiveauParSession> prixNiveauParSession = prixNiveauParSessionDao.findBySessionAndNiveau(session, enregistrement.getIdNiveau());
        List<ModaliteTransaction> modaliteTransactionList;
        double sum = 0;
        if (!transactions.isEmpty()) {
            //sum = transactions.stream().filter(transaction -> transaction.getType().equals(ETypeTransaction.CREDIT.name())).mapToDouble(Transaction::getMontant).sum();
            for (Transaction transaction: transactions) {
                modaliteTransactionList = modaliteTransactionDao.findModaliteTransactionByTransaction(transaction);
                if(!modaliteTransactionList.isEmpty()){
                    for(ModaliteTransaction modaliteTransaction: modaliteTransactionList){
                        sum+=modaliteTransaction.getMontant();
                    }
                }
            }
        }
        return prixNiveauParSession.get().getMontantNiveau() - sum;
    }
}
