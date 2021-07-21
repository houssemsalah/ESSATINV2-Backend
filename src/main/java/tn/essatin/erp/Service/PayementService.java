package tn.essatin.erp.Service;

import tn.essatin.erp.model.ModaliteTransaction;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Session;

import java.util.Set;

public interface PayementService {
   void studentPay(Personne personne, float montant, Session session, String datePayement,
                   int typeTransaction, int statusTransaction, int idFinancier,
                   Set<ModaliteTransaction> modaliteTransactionSet);
   void salaryPay();
   void servicePay();
   void vacatairPay();
   void avancePay();
   void rembourcementPay();
   void formateurPay();

}
