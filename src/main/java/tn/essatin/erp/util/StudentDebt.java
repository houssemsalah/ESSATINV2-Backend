package tn.essatin.erp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tn.essatin.erp.model.*;
import tn.essatin.erp.dao.EnregistrementDao;
import tn.essatin.erp.dao.InscriptionDao;
import tn.essatin.erp.dao.PrixNiveauParSessionDao;
import tn.essatin.erp.dao.TransactionDao;


import java.util.Collection;
import java.util.Optional;

@Component
public class StudentDebt {
    @Autowired
    PrixNiveauParSessionDao prixNiveauParSessionDao;
    @Autowired
    TransactionDao transactionDao;
    @Autowired
    InscriptionDao inscriptionDao;
    @Autowired
    EnregistrementDao enregistrementDao;

    public double debt(Etudiants etudiants, Session session) {
        System.out.println("debt enter");
        Collection<Transaction> transactions = transactionDao.findAllByIdClientAndSession(etudiants.getIdPersonne(), session);
        Inscription inscription = inscriptionDao.findByIdEtudiant(etudiants);
        System.out.println(session);
        Enregistrement enregistrement = enregistrementDao.findByIdInscriptionAndAndIdSession(inscription, session);
        System.out.println(enregistrement);

        Optional<PrixNiveauParSession> prixNiveauParSession = prixNiveauParSessionDao.findBySessionAndNiveau(session, enregistrement.getIdNiveau());
        System.out.println(prixNiveauParSession.isPresent());
        System.out.println(prixNiveauParSession.isPresent());
        double sum =0 ;
        if (!transactions.isEmpty()){
            sum = transactions.stream().filter(transaction -> transaction.getType().equals(ETypeTransaction.CREDIT.name())).mapToDouble(Transaction::getMontant).sum();
        }
        return prixNiveauParSession.get().getMontantNiveau() - sum;
    }
}
