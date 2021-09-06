package tn.essatin.erp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tn.essatin.erp.dao.financier.ModaliteTransactionDao;
import tn.essatin.erp.dao.financier.PrixNiveauParSessionDao;
import tn.essatin.erp.dao.financier.TransactionDao;
import tn.essatin.erp.dao.scolarite.EnregistrementDao;
import tn.essatin.erp.dao.scolarite.InscriptionDao;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Scolarite.Enregistrement;
import tn.essatin.erp.model.Scolarite.Etudiants;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.model.financier.EStatus;
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
    public StudentDebt(PrixNiveauParSessionDao prixNiveauParSessionDao, TransactionDao transactionDao, InscriptionDao inscriptionDao, EnregistrementDao enregistrementDao, ModaliteTransactionDao modaliteTransactionDao) {
        this.prixNiveauParSessionDao = prixNiveauParSessionDao;
        this.transactionDao = transactionDao;
        this.inscriptionDao = inscriptionDao;
        this.enregistrementDao = enregistrementDao;
        this.modaliteTransactionDao = modaliteTransactionDao;
    }

    public double debt(Enregistrement enregistrement) {
        try {
            Etudiants etudiants = enregistrement.getIdInscription().getIdEtudiant();
            Personne personne = etudiants.getIdPersonne();
            Session session = enregistrement.getIdSession();
            Collection<Transaction> transactions = transactionDao.findAllByClientAndSession(personne, session);
            Optional<PrixNiveauParSession> prixNiveauParSession = prixNiveauParSessionDao.findTopBySessionAndNiveauOrderByDateDesc(session, enregistrement.getIdNiveau());
            List<ModaliteTransaction> modaliteTransactionList;
            double sum = 0;
            if (!transactions.isEmpty()) {
                for (Transaction transaction : transactions) {
                    modaliteTransactionList = modaliteTransactionDao.findModaliteTransactionByTransaction(transaction);
                    if (!modaliteTransactionList.isEmpty()) {
                        for (ModaliteTransaction modaliteTransaction : modaliteTransactionList) {
                            if (!modaliteTransaction.getStatus().equals(EStatus.CANCELED) && !modaliteTransaction.getStatus().equals(EStatus.REJECTED))
                                sum += modaliteTransaction.getMontant();
                        }
                    }
                }
            }
            if (prixNiveauParSession.isEmpty()) {
                return 0.0;
            }
            return prixNiveauParSession.get().getMontantNiveau() - sum;
        } catch (Exception E) {
            return 0.0;
        }
    }

    public double PayerEnPourcent(Enregistrement enregistrement) {
        double rest = debt(enregistrement);
        Optional<PrixNiveauParSession> prixNiveauParSession = prixNiveauParSessionDao.findTopBySessionAndNiveauOrderByDateDesc(enregistrement.getIdSession(), enregistrement.getIdNiveau());
        if (prixNiveauParSession.isEmpty())
            return 100.0;
        double payer = prixNiveauParSession.get().getMontantNiveau() - rest;
        return (payer / prixNiveauParSession.get().getMontantNiveau()) * 100.0;
    }
}
