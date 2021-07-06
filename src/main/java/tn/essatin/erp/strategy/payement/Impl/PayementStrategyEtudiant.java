package tn.essatin.erp.strategy.payement.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tn.essatin.erp.dao.EtudiantsDao;
import tn.essatin.erp.model.Etudiants;
import tn.essatin.erp.model.ModaliteTransaction;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.strategy.payement.PayementStrategy;
import tn.essatin.erp.strategy.payement.PayementStrategyName;
import tn.essatin.erp.util.ManageTransaction;
import tn.essatin.erp.util.StudentDebt;

import java.util.Optional;
import java.util.Set;

@Component
public class PayementStrategyEtudiant implements PayementStrategy {
    @Autowired
    EtudiantsDao etudiantsDao;
    @Autowired
    StudentDebt studentDebt;
    @Autowired
    ManageTransaction manageTransaction;

    @Override
    public void payer(Personne personne, float montant, Session session, String datePayement,
                      int typeTransaction, int statusTransaction, int idFinancier,
                      Set<ModaliteTransaction> modaliteTransactionSet) {
        Optional<Etudiants> etudiant = etudiantsDao.findByIdPersonne(personne);
        if (etudiant.isPresent()) {
            System.out.println(etudiant.get()); System.out.println(session);
            double debt = studentDebt.debt(etudiant.get(), session);
            System.out.println(debt);
            if (debt != 0 || debt <= montant) {
                manageTransaction.add(typeTransaction, statusTransaction, modaliteTransactionSet, personne,
                    session, idFinancier, datePayement, montant);
            }
        }
    }

    @Override
    public PayementStrategyName getPayementStrategyName() {
        return PayementStrategyName.PAYEMENT_STRATEGY_ETUDIANT;
    }
}
