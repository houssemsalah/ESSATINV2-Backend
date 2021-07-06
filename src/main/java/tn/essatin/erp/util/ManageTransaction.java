package tn.essatin.erp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tn.essatin.erp.dao.*;
import tn.essatin.erp.model.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Component
public class ManageTransaction {
    @Autowired
    SessionDao sessionDao;
    @Autowired
    TransactionDao transactionDao;
    @Autowired
    PersonneDao personneDao;
    @Autowired
    EmployerDao employerDao;
    @Autowired
    PrixNiveauParSessionDao prixNiveauParSessionDao;
    @Autowired
    InsecriptionDao insecriptionDao;
    @Autowired
    EnregistrementDao enregistrementDao;
    @Autowired
    EtudiantsDao etudiantsDao;
    @Autowired
    StudentDebt studentDebt;
    @Autowired
    ModaliteTransactionDao modaliteTransactionDao;

    public void add(int typeTransaction, int statusTransaction, Set<ModaliteTransaction> modalite, Personne personne,
                    Session session, int idFinancier, String datePayement, Float montant) {
        String type;
        String status = "";
        if (typeTransaction == 1) {
            type = ETypeTransaction.DEBIT.name();
        } else {
            type = ETypeTransaction.CREDIT.name();
        }
        switch (statusTransaction) {
            case 1: {
                status = EStatus.INCOMPLETE.name();
                break;
            }
            case 2: {
                status = EStatus.CANCELED.name();
                break;
            }
            case 3: {
                status = EStatus.COMPLETE.name();
                break;
            }
            case 4: {
                status = EStatus.REJECTED.name();
                break;
            }
        }

        List<ModaliteTransaction> modaliteTransactionList = modaliteTransactionDao.saveAll(modalite);
        Employer employer = employerDao.findById(idFinancier)
            .orElseThrow(() -> new RuntimeException("error financier not found"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(datePayement, formatter);

        Transaction transaction = new Transaction(type, modaliteTransactionList, localDate, employer, personne, session, status,
            montant);
        transactionDao.save(transaction);
    }
}
