package tn.essatin.erp.rest.financier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.Service.PayementService;
import tn.essatin.erp.dao.CompteDao;
import tn.essatin.erp.dao.PersonneDao;
import tn.essatin.erp.dao.SessionDao;
import tn.essatin.erp.dao.financier.EmployerDao;
import tn.essatin.erp.dao.financier.ModaliteTransactionDao;
import tn.essatin.erp.dao.scolarite.EtudiantsDao;
import tn.essatin.erp.model.*;
import tn.essatin.erp.model.Scolarite.Etudiants;
import tn.essatin.erp.model.financier.*;
import tn.essatin.erp.payload.request.financier.PaymentRequest;
import tn.essatin.erp.payload.response.CombinedResponse;
import tn.essatin.erp.payload.response.MessageResponse;
import tn.essatin.erp.util.StudentDebt;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/pay/")
//@PreAuthorize("hasRole('ADMIN')")
public class PayementRest {
    final PayementService payementService;
    final PersonneDao personneDao;
    final SessionDao sessionDao;
    final EmployerDao employerDao;
    final CompteDao compteDao;
    final StudentDebt studentDebt;
    final EtudiantsDao etudiantsDao;
    final ModaliteTransactionDao modaliteTransactionDao;


    @Autowired
    public PayementRest(PayementService payementService, PersonneDao personneDao, StudentDebt studentDebt, ModaliteTransactionDao modaliteTransactionDao,
                        SessionDao sessionDao, EmployerDao employerDao, CompteDao compteDao, EtudiantsDao etudiantsDao) {
        this.payementService = payementService;
        this.personneDao = personneDao;
        this.sessionDao = sessionDao;
        this.employerDao = employerDao;
        this.compteDao = compteDao;
        this.studentDebt = studentDebt;
        this.etudiantsDao = etudiantsDao;
        this.modaliteTransactionDao = modaliteTransactionDao;

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

        EStatus status;
        try {
            status = EStatus.valueOf(paymentRequest.getStatusTransaction());
        } catch (IllegalArgumentException illegalArgumentException) {
            return new ResponseEntity<>(
                    new CombinedResponse(new MessageResponse("TypeTransaction est introvable"),
                            "EStatus[]", EStatus.values())
                    , HttpStatus.FORBIDDEN);
        }
        Optional<Employer> employer = employerDao.findById(paymentRequest.getIdFinancier());
        if (employer.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("employer introvable", 403), HttpStatus.FORBIDDEN);
        }
        Optional<Compte> compte = compteDao.findByIdPersonne(employer.get().getPersonne());
        if (compte.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("compte introvable", 403), HttpStatus.FORBIDDEN);
        }
        Set<Role> role = compte.get().getRoles();
        boolean financier = false;
        for (Role r : role) {
            if (r.getRole().equals(ERole.ROLE_FINANCIER)) {
                financier = true;
                break;
            }
        }
        if (!financier)
            return new ResponseEntity<>(new MessageResponse("financier introvable", 403), HttpStatus.FORBIDDEN);
        Optional<Etudiants> etudiant = etudiantsDao.findByIdPersonne(personne.get());
        if (etudiant.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Etuduant introvable", 403), HttpStatus.FORBIDDEN);
        }
        double debt = studentDebt.debt(etudiant.get(), session.get());
        double montant = 0;
        if (!paymentRequest.getModaliteTransactionSet().isEmpty()) {
            for (ModaliteTransaction modalite : paymentRequest.getModaliteTransactionSet()) {
                montant += modalite.getMontant();
            }
        } else {
            return new ResponseEntity<>(new MessageResponse("une transaction doit avoir au moin une modalité de transaction", 403), HttpStatus.FORBIDDEN);
        }
        Optional<ModaliteTransaction> modaliteTransaction;
        if (!paymentRequest.getModaliteTransactionSet().isEmpty()) {
            for (ModaliteTransaction modalite : paymentRequest.getModaliteTransactionSet()) {
                modaliteTransaction = modaliteTransactionDao.findModaliteTransactionByNumeroAndType(modalite.getNumero(), ETypeModaliteTransaction.CHEQUE);
                if (modaliteTransaction.isPresent()) {
                    return new ResponseEntity<>(new MessageResponse("ce numero de cheque existe deja", 403), HttpStatus.FORBIDDEN);
                }
                modaliteTransaction = modaliteTransactionDao.findModaliteTransactionByNumeroAndType(modalite.getNumero(), ETypeModaliteTransaction.VIREMENT_BANCAIRE);
                if (modaliteTransaction.isPresent()) {
                    return new ResponseEntity<>(new MessageResponse("ce code virement existe deja", 403), HttpStatus.FORBIDDEN);
                }
            }
            Object[] modalite = paymentRequest.getModaliteTransactionSet().toArray();
            for (int i = 0; i < modalite.length; i++) {
                ModaliteTransaction mo = (ModaliteTransaction) modalite[i];
                if (mo.getType().equals(ETypeModaliteTransaction.CHEQUE)) {
                    String numero = mo.getNumero();
                    for (int j = i + 1; j < modalite.length; j++) {
                        ModaliteTransaction mo1 = (ModaliteTransaction) modalite[j];
                        if (mo1.getType().equals(ETypeModaliteTransaction.CHEQUE)) {
                            if (mo1.getNumero().equalsIgnoreCase(numero)) {
                                return new ResponseEntity<>(new MessageResponse("un numero de cheque est dupliqué", 403), HttpStatus.FORBIDDEN);
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < modalite.length; i++) {
                ModaliteTransaction mo = (ModaliteTransaction) modalite[i];
                if (mo.getType().equals(ETypeModaliteTransaction.VIREMENT_BANCAIRE)) {
                    String numero = mo.getNumero();
                    for (int j = i + 1; j < modalite.length; j++) {
                        ModaliteTransaction mo1 = (ModaliteTransaction) modalite[j];
                        if (mo1.getType().equals(ETypeModaliteTransaction.VIREMENT_BANCAIRE)) {
                            if (mo1.getNumero().equalsIgnoreCase(numero)) {
                                return new ResponseEntity<>(new MessageResponse("un code de virement est dupliqué", 403), HttpStatus.FORBIDDEN);
                            }
                        }
                    }
                }
            }
        }

        if (montant <= debt) {
            payementService.studentPay(personne.get(), session.get(),
                    ETypeTransaction.CREDIT,
                    status, paymentRequest.getIdFinancier(),
                    paymentRequest.getModaliteTransactionSet());
            return ResponseEntity.ok(new MessageResponse("Transaction effectuer avec succée"));
        } else {
            return new ResponseEntity<>(new MessageResponse("le monant est superieur a ce qui reste a payer", 403), HttpStatus.FORBIDDEN);
        }
    }
}
