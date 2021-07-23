package tn.essatin.erp.strategy.payement.Impl;

import org.springframework.stereotype.Component;
import tn.essatin.erp.strategy.payement.PayementStrategyName;
import tn.essatin.erp.model.financier.ModaliteTransaction;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.strategy.payement.PayementStrategy;

import java.util.Set;

@Component
public class PayementStrategyVacatair implements PayementStrategy {
    @Override
    public void payer(Personne personne, float montant, Session session, String datePayement, int typeTransaction, int statusTransaction, int idFinancier, Set<ModaliteTransaction> modaliteTransactionSet) {

    }

    @Override
    public PayementStrategyName getPayementStrategyName() {
        return PayementStrategyName.PAYEMENT_STRATEGY_VACATAIR;
    }
}
