package tn.essatin.erp.strategy.payement.Impl;

import org.springframework.stereotype.Component;
import tn.essatin.erp.model.financier.EStatus;
import tn.essatin.erp.model.financier.ETypeTransaction;
import tn.essatin.erp.model.financier.Employer;
import tn.essatin.erp.model.financier.ModaliteTransaction;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.strategy.payement.PayementStrategy;
import tn.essatin.erp.strategy.payement.PayementStrategyName;

import java.util.Set;

@Component
public class PayementStrategyFormateur implements PayementStrategy {
    @Override
    public void payer(Personne personne,Session session,
                      ETypeTransaction typeTransaction,  Employer Financier,
                      Set<ModaliteTransaction> modaliteTransactionSet) {
    }

    @Override
    public PayementStrategyName getPayementStrategyName() {
        return null;
    }
}
