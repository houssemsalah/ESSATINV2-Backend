package tn.essatin.erp.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.essatin.erp.strategy.payement.PayementStrategy;
import tn.essatin.erp.strategy.payement.PayementStrategyFactory;
import tn.essatin.erp.strategy.payement.PayementStrategyName;
import tn.essatin.erp.Service.PayementService;
import tn.essatin.erp.model.ModaliteTransaction;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Session;

import java.util.Set;

@Service
public class PaymentServiceImpl implements PayementService {
    @Autowired
    private PayementStrategyFactory strategyFactory;

    @Override
    public void studentPay(Personne personne, float montant, Session session, String datePayement,
                           int typeTransaction, int statusTransaction, int idFinancier,
                           Set<ModaliteTransaction> modaliteTransactionSet) {
        PayementStrategy payementStrategy = strategyFactory.findStrategy(PayementStrategyName.PAYEMENT_STRATEGY_ETUDIANT);
        payementStrategy.payer(personne, montant, session, datePayement, typeTransaction,
            statusTransaction,idFinancier, modaliteTransactionSet);
    }

    @Override
    public void salaryPay() {

    }

    @Override
    public void servicePay() {

    }

    @Override
    public void vacatairPay() {

    }

    @Override
    public void avancePay() {

    }

    @Override
    public void rembourcementPay() {

    }
}
