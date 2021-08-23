package tn.essatin.erp.strategy.payement.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.model.financier.EStatus;
import tn.essatin.erp.model.financier.ETypeTransaction;
import tn.essatin.erp.model.financier.Employer;
import tn.essatin.erp.model.financier.ModaliteTransaction;
import tn.essatin.erp.strategy.payement.PayementStrategy;
import tn.essatin.erp.strategy.payement.PayementStrategyName;
import tn.essatin.erp.util.ManageTransaction;

import java.util.Set;

@Component
public class PayementStrategyEtudiant implements PayementStrategy {

    final ManageTransaction manageTransaction;

    @Autowired
    public PayementStrategyEtudiant(ManageTransaction manageTransaction) {

        this.manageTransaction = manageTransaction;
    }

    @Override
    public void payer(Personne personne, Session session,
                      ETypeTransaction typeTransaction, Employer Financier,
                      Set<ModaliteTransaction> modaliteTransactionSet) {

        manageTransaction.add(typeTransaction, modaliteTransactionSet, personne,
                session, Financier);
    }


    @Override
    public PayementStrategyName getPayementStrategyName() {
        return PayementStrategyName.PAYEMENT_STRATEGY_ETUDIANT;
    }
}
