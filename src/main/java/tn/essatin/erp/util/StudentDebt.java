package tn.essatin.erp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tn.essatin.erp.dao.scolarite.EnregistrementDao;
import tn.essatin.erp.dao.scolarite.InscriptionDao;
import tn.essatin.erp.dao.financier.PrixNiveauParSessionDao;
import tn.essatin.erp.dao.financier.TransactionDao;
import tn.essatin.erp.model.*;
import tn.essatin.erp.model.Scolarite.Enregistrement;
import tn.essatin.erp.model.Scolarite.Etudiants;
import tn.essatin.erp.model.Scolarite.Inscription;
import tn.essatin.erp.model.financier.ETypeTransaction;
import tn.essatin.erp.model.financier.PrixNiveauParSession;
import tn.essatin.erp.model.financier.Transaction;

import java.util.Collection;
import java.util.Optional;

@Component
public class StudentDebt {
    final PrixNiveauParSessionDao prixNiveauParSessionDao;
    final TransactionDao transactionDao;
    final InscriptionDao inscriptionDao;
    final EnregistrementDao enregistrementDao;

    @Autowired
    public StudentDebt(PrixNiveauParSessionDao prixNiveauParSessionDao, TransactionDao transactionDao, InscriptionDao inscriptionDao, EnregistrementDao enregistrementDao) {
        this.prixNiveauParSessionDao = prixNiveauParSessionDao;
        this.transactionDao = transactionDao;
        this.inscriptionDao = inscriptionDao;
        this.enregistrementDao = enregistrementDao;
    }

    public double debt(Etudiants etudiants, Session session) {
        System.out.println("debt enter");
        Collection<Transaction> transactions = transactionDao.findAllByIdClientAndSession(etudiants.getIdPersonne(), session);
        Inscription inscription = inscriptionDao.findTopByIdEtudiantOrderByDateDesc(etudiants);
        System.out.println(session);
        Enregistrement enregistrement = enregistrementDao.findByIdInscriptionAndIdSession(inscription, session);
        System.out.println(enregistrement);

        Optional<PrixNiveauParSession> prixNiveauParSession = prixNiveauParSessionDao.findBySessionAndNiveau(session, enregistrement.getIdNiveau());
        System.out.println(prixNiveauParSession.isPresent());
        System.out.println(prixNiveauParSession.isPresent());
        double sum = 0;
        if (!transactions.isEmpty()) {
            sum = transactions.stream().filter(transaction -> transaction.getType().equals(ETypeTransaction.CREDIT.name())).mapToDouble(Transaction::getMontant).sum();
        }
        return prixNiveauParSession.get().getMontantNiveau() - sum;
    }
}
