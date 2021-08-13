package tn.essatin.erp.Service;

import tn.essatin.erp.model.financier.EStatus;
import tn.essatin.erp.model.financier.ETypeTransaction;
import tn.essatin.erp.model.financier.ModaliteTransaction;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Session;

import java.util.Set;

public interface PayementService {
   void studentPay(Personne personne, double montant, Session session, String datePayement,
                   ETypeTransaction typeTransaction, EStatus statusTransaction, int idFinancier,
                   Set<ModaliteTransaction> modaliteTransactionSet) ;
   void salaryPay();
   void servicePay();
   void vacatairPay();
   void avancePay();
   void rembourcementPay();
   void formateurPay();

}
