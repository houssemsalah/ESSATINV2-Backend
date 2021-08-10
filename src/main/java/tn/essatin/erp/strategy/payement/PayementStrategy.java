package tn.essatin.erp.strategy.payement;

import tn.essatin.erp.model.financier.EStatus;
import tn.essatin.erp.model.financier.ETypeTransaction;
import tn.essatin.erp.model.financier.ModaliteTransaction;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Session;

import java.util.Set;

public interface PayementStrategy {
    void payer(Personne personne, double montant, Session session, String datePayement,
               ETypeTransaction typeTransaction, EStatus statusTransaction, int idFinancier,
               Set<ModaliteTransaction> modaliteTransactionSet);

    PayementStrategyName getPayementStrategyName();
}
