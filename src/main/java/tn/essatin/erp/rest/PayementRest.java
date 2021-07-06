package tn.essatin.erp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.Service.PayementService;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.payload.request.PaymentRequest;
import tn.essatin.erp.payload.response.MessageResponse;
import tn.essatin.erp.dao.PersonneDao;
import tn.essatin.erp.dao.SessionDao;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/pay/")
//@PreAuthorize("hasRole('ADMIN')")
public class PayementRest {
    @Autowired
    PayementService payementService;
    @Autowired
    PersonneDao personneDao;
    @Autowired
    SessionDao sessionDao;

    @PostMapping("/etudiant")
    public ResponseEntity<?> payEtudiant(@Valid @RequestBody PaymentRequest paymentRequest) {
        Optional<Personne> personne = personneDao.findById(paymentRequest.getPersonne());
        if (personne.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Personne est introvable"));
        }
        Optional<Session> session = sessionDao.findById(paymentRequest.getSession());
        if (session.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Session est introvable"));
        }
        payementService.studentPay(personne.get(), paymentRequest.getMontant(), session.get(), paymentRequest.getDatePayement(),
            paymentRequest.getTypeTransaction(), paymentRequest.getStatusTransaction(), paymentRequest.getIdFinancier(),
            paymentRequest.getModaliteTransactionSet());
        return ResponseEntity.ok(new MessageResponse("Transaction effectuer avec succée"));
    }
}
