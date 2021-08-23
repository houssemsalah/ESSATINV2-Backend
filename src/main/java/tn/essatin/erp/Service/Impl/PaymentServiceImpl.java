package tn.essatin.erp.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.essatin.erp.model.financier.ETypeTransaction;
import tn.essatin.erp.model.financier.Employer;
import tn.essatin.erp.strategy.payement.PayementStrategy;
import tn.essatin.erp.strategy.payement.PayementStrategyFactory;
import tn.essatin.erp.strategy.payement.PayementStrategyName;
import tn.essatin.erp.Service.PayementService;
import tn.essatin.erp.model.financier.ModaliteTransaction;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Session;

import java.util.Set;

@Service
public class PaymentServiceImpl implements PayementService {
    private final PayementStrategyFactory strategyFactory;
    @Autowired
    public PaymentServiceImpl(PayementStrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    @Override
    public void studentPay(Personne personne,  Session session,
                           ETypeTransaction typeTransaction,  Employer Financier,
                           Set<ModaliteTransaction> modaliteTransactionSet) {

        PayementStrategy payementStrategy = strategyFactory.findStrategy(PayementStrategyName.PAYEMENT_STRATEGY_ETUDIANT);
        payementStrategy.payer(personne, session, typeTransaction,Financier, modaliteTransactionSet);
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

    @Override
    public void formateurPay() {

    }
}
