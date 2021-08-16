package tn.essatin.erp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tn.essatin.erp.dao.*;
import tn.essatin.erp.dao.financier.EmployerDao;
import tn.essatin.erp.dao.financier.ModaliteTransactionDao;
import tn.essatin.erp.dao.financier.PrixNiveauParSessionDao;
import tn.essatin.erp.dao.financier.TransactionDao;
import tn.essatin.erp.dao.scolarite.EnregistrementDao;
import tn.essatin.erp.dao.scolarite.EtudiantsDao;
import tn.essatin.erp.dao.scolarite.InscriptionDao;
import tn.essatin.erp.model.*;
import tn.essatin.erp.model.financier.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Component
public class ManageTransaction {
    final SessionDao sessionDao;
    final TransactionDao transactionDao;
    final PersonneDao personneDao;
    final EmployerDao employerDao;
    final PrixNiveauParSessionDao prixNiveauParSessionDao;
    final InscriptionDao inscriptionDao;
    final EnregistrementDao enregistrementDao;
    final EtudiantsDao etudiantsDao;
    final StudentDebt studentDebt;
    final ModaliteTransactionDao modaliteTransactionDao;

    public ManageTransaction(SessionDao sessionDao, TransactionDao transactionDao, PersonneDao personneDao, EmployerDao employerDao, PrixNiveauParSessionDao prixNiveauParSessionDao, InscriptionDao inscriptionDao, EnregistrementDao enregistrementDao, EtudiantsDao etudiantsDao, StudentDebt studentDebt, ModaliteTransactionDao modaliteTransactionDao) {
        this.sessionDao = sessionDao;
        this.transactionDao = transactionDao;
        this.personneDao = personneDao;
        this.employerDao = employerDao;
        this.prixNiveauParSessionDao = prixNiveauParSessionDao;
        this.inscriptionDao = inscriptionDao;
        this.enregistrementDao = enregistrementDao;
        this.etudiantsDao = etudiantsDao;
        this.studentDebt = studentDebt;
        this.modaliteTransactionDao = modaliteTransactionDao;
    }

    public void add(ETypeTransaction typeTransaction, EStatus statusTransaction, Set<ModaliteTransaction> modalite, Personne personne,
                    Session session, int idFinancier) {
        Employer employer = employerDao.findById(idFinancier)
            .orElseThrow(() -> new RuntimeException("error financier not found"));
        Transaction transaction = new Transaction(typeTransaction, LocalDate.now(), employer,
                personne, session, statusTransaction );
        transactionDao.save(transaction);
        for (ModaliteTransaction m :modalite)
            m.setTransaction(transaction);
        modaliteTransactionDao.saveAll(modalite);
    }
}
