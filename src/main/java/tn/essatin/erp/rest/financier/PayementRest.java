package tn.essatin.erp.rest.financier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.Service.PayementService;
import tn.essatin.erp.dao.PersonneDao;
import tn.essatin.erp.dao.SessionDao;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.model.financier.EStatus;
import tn.essatin.erp.model.financier.ETypeTransaction;
import tn.essatin.erp.payload.request.financier.PaymentRequest;
import tn.essatin.erp.payload.response.CombinedResponse;
import tn.essatin.erp.payload.response.MessageResponse;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/pay/")
//@PreAuthorize("hasRole('ADMIN')")
public class PayementRest {
    final PayementService payementService;
    final PersonneDao personneDao;
    final SessionDao sessionDao;

    @Autowired
    public PayementRest(PayementService payementService, PersonneDao personneDao, SessionDao sessionDao) {
        this.payementService = payementService;
        this.personneDao = personneDao;
        this.sessionDao = sessionDao;
    }

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
        ETypeTransaction typeTransaction;
        try {
            typeTransaction = ETypeTransaction.valueOf(paymentRequest.getTypeTransaction());
        } catch (IllegalArgumentException illegalArgumentException) {
            return new ResponseEntity<>(
                    new CombinedResponse(new MessageResponse("TypeTransaction est introvable"),
                            "ETypeTransaction[]", ETypeTransaction.values())
                    , HttpStatus.FORBIDDEN);
        }
        EStatus status;
        try {
            status = EStatus.valueOf(paymentRequest.getStatusTransaction());
        } catch (IllegalArgumentException illegalArgumentException) {
            return new ResponseEntity<>(
                    new CombinedResponse(new MessageResponse("TypeTransaction est introvable"),
                            "EStatus[]", EStatus.values())
                    , HttpStatus.FORBIDDEN);
        }
        payementService.studentPay(personne.get(), paymentRequest.getMontant(), session.get(),
                paymentRequest.getDatePayement(), typeTransaction,
                status, paymentRequest.getIdFinancier(),
                paymentRequest.getModaliteTransactionSet());
        return ResponseEntity.ok(new MessageResponse("Transaction effectuer avec succée"));
    }
}
