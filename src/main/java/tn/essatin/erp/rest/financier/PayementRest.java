package tn.essatin.erp.rest.financier;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.Service.PayementService;
import tn.essatin.erp.dao.CompteDao;
import tn.essatin.erp.dao.PersonneDao;
import tn.essatin.erp.dao.SessionDao;
import tn.essatin.erp.dao.financier.EmployerDao;
import tn.essatin.erp.dao.financier.ModaliteTransactionDao;
import tn.essatin.erp.dao.scolarite.EnregistrementDao;
import tn.essatin.erp.dao.scolarite.EtatInscriptionDao;
import tn.essatin.erp.dao.scolarite.EtudiantsDao;
import tn.essatin.erp.dao.scolarite.InscriptionDao;
import tn.essatin.erp.model.*;
import tn.essatin.erp.model.Scolarite.Enregistrement;
import tn.essatin.erp.model.financier.*;
import tn.essatin.erp.payload.request.financier.PaymentRequest;
import tn.essatin.erp.payload.response.MessageResponse;
import tn.essatin.erp.util.ApiInfo;
import tn.essatin.erp.util.StudentDebt;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/pay/")
//@PreAuthorize("hasRole('ROLE_FINANCIER')")
public class PayementRest {
    final PayementService payementService;
    final PersonneDao personneDao;
    final SessionDao sessionDao;
    final EmployerDao employerDao;
    final CompteDao compteDao;
    final StudentDebt studentDebt;
    final EtudiantsDao etudiantsDao;
    final ModaliteTransactionDao modaliteTransactionDao;
    final EnregistrementDao enregistrementDao;
    final EtatInscriptionDao etatInscriptionDao;
    final InscriptionDao inscriptionDao;
    final MessageSource messageSource;

    public PayementRest(MessageSource messageSource, PayementService payementService, PersonneDao personneDao, StudentDebt studentDebt, ModaliteTransactionDao modaliteTransactionDao,
                        SessionDao sessionDao, EmployerDao employerDao, CompteDao compteDao, EtudiantsDao etudiantsDao,
                        EnregistrementDao enregistrementDao, EtatInscriptionDao etatInscriptionDao,
                        InscriptionDao inscriptionDao) {
        this.messageSource = messageSource;
        this.payementService = payementService;
        this.personneDao = personneDao;
        this.sessionDao = sessionDao;
        this.employerDao = employerDao;
        this.compteDao = compteDao;
        this.studentDebt = studentDebt;
        this.etudiantsDao = etudiantsDao;
        this.modaliteTransactionDao = modaliteTransactionDao;
        this.enregistrementDao = enregistrementDao;
        this.etatInscriptionDao = etatInscriptionDao;
        this.inscriptionDao = inscriptionDao;
    }

    @GetMapping("/")
    public ResponseEntity<?> infoApi() {
        List<ApiInfo> infos = new ArrayList<>();
        List<MessageResponse> responses;
        ApiInfo info;
        /////
        responses = new ArrayList<>();
        responses.add(new MessageResponse(messageSource.getMessage("message.transaction.success", null, Locale.FRENCH), 200));
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.enregistrement", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.compteFinancier", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.employer", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.financier", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.modalite.vide", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.existe.cheque", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.existe.virement", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.duplicated.cheque", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.duplicated.virement", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.montant.supdept", null, Locale.FRENCH), 403));
        Set<ModaliteTransaction> modaliteTransactionSet = new HashSet<>();
        modaliteTransactionSet.add(new ModaliteTransaction(null, 300.0, ETypeModaliteTransaction.ESPECES, null, null, null));
        modaliteTransactionSet.add(new ModaliteTransaction("123456789", 600, ETypeModaliteTransaction.CHEQUE, LocalDate.now().plusDays(30), EStatus.INCOMPLETE, null));
        modaliteTransactionSet.add(new ModaliteTransaction("987654321", 1000, ETypeModaliteTransaction.VIREMENT_BANCAIRE, LocalDate.now().minusDays(3), null, null));

        ApiInfo payementEtudiant = new ApiInfo("/api/pay/etudiant", "Post",
                messageSource.getMessage("desc.api.pay.etudiant", null, Locale.FRENCH),
                new PaymentRequest(589, modaliteTransactionSet, 1), "JSON text Message", responses);
        infos.add(payementEtudiant);
        /////////////////////
        return new ResponseEntity<>(infos, HttpStatus.OK);


    }

    @PostMapping("/etudiant")
    public ResponseEntity<?> payEtudiant(@Valid @RequestBody PaymentRequest paymentRequest) {
        Optional<Enregistrement> enregistrement = enregistrementDao.findById(paymentRequest.getIdEnregistrement());
        if (enregistrement.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.introuvable.enregistrement", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        Personne personne = enregistrement.get().getIdInscription().getIdEtudiant().getIdPersonne();
        Session session = enregistrement.get().getIdSession();

        Optional<Compte> compteEmployer = compteDao.findById(paymentRequest.getIdCompteFinancier());
        if (compteEmployer.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.introuvable.compteFinancier", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        Personne personneEmployer = compteEmployer.get().getIdPersonne();
        Optional<Employer> employer = employerDao.findByPersonne(personneEmployer);
        if (employer.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.introuvable.employer", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        Set<Role> role = compteEmployer.get().getRoles();
        boolean financier = false;
        for (Role r : role) {
            if (r.getRole().equals(ERole.ROLE_FINANCIER)) {
                financier = true;
                break;
            }
        }
        if (!financier)
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.introuvable.financier", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        double debt = studentDebt.debt(enregistrement.get());
        double montant = 0;
        if (!paymentRequest.getModaliteTransactionSet().isEmpty()) {
            for (ModaliteTransaction modalite : paymentRequest.getModaliteTransactionSet()) {
                montant += modalite.getMontant();
            }
        } else {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.modalite.vide", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        Optional<ModaliteTransaction> modaliteTransaction;
        if (!paymentRequest.getModaliteTransactionSet().isEmpty()) {
            for (ModaliteTransaction modalite : paymentRequest.getModaliteTransactionSet()) {
                modaliteTransaction = modaliteTransactionDao.findModaliteTransactionByNumeroAndType(modalite.getNumero(), ETypeModaliteTransaction.CHEQUE);
                if (modaliteTransaction.isPresent()) {
                    return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.existe.cheque", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
                }
                modaliteTransaction = modaliteTransactionDao.findModaliteTransactionByNumeroAndType(modalite.getNumero(), ETypeModaliteTransaction.VIREMENT_BANCAIRE);
                if (modaliteTransaction.isPresent()) {
                    return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.existe.virement", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
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
                                return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.duplicated.cheque", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
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
                                return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.duplicated.virement", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
                            }
                        }
                    }
                }
            }
        }
        for (ModaliteTransaction m : paymentRequest.getModaliteTransactionSet()) {
            if (m.getType().equals(ETypeModaliteTransaction.ESPECES)) {
                m.setDate(LocalDate.now());
                m.setStatus(EStatus.COMPLETE);
            }
            if (m.getType().equals(ETypeModaliteTransaction.VIREMENT_BANCAIRE)) {
                m.setStatus(EStatus.COMPLETE);
            }
        }
        if (montant <= debt) {
            payementService.studentPay(personne, session,
                    ETypeTransaction.CREDIT
                    , employer.get(),
                    paymentRequest.getModaliteTransactionSet());
            enregistrement.get().setEtatFinanciere(1);
            enregistrement.get().getIdInscription().setIdEtatInscription(etatInscriptionDao.findByNom("Valide"));
            enregistrementDao.save(enregistrement.get());
            inscriptionDao.save(enregistrement.get().getIdInscription());
            return ResponseEntity.ok(new MessageResponse(messageSource.getMessage("message.transaction.success", null, Locale.FRENCH), 200));
        } else {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.montant.supdept", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
    }
}
