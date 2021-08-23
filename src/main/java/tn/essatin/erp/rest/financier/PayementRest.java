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
import tn.essatin.erp.dao.scolarite.EnregistrementDao;
import tn.essatin.erp.dao.scolarite.EtatInscriptionDao;
import tn.essatin.erp.dao.scolarite.EtudiantsDao;
import tn.essatin.erp.dao.scolarite.InscriptionDao;
import tn.essatin.erp.model.*;
import tn.essatin.erp.model.Scolarite.Enregistrement;
import tn.essatin.erp.model.financier.*;
import tn.essatin.erp.payload.request.financier.PaymentRequest;
import tn.essatin.erp.payload.response.CombinedResponse;
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


    @Autowired
    public PayementRest(PayementService payementService, PersonneDao personneDao, StudentDebt studentDebt, ModaliteTransactionDao modaliteTransactionDao,
                        SessionDao sessionDao, EmployerDao employerDao, CompteDao compteDao, EtudiantsDao etudiantsDao,
                        EnregistrementDao enregistrementDao,EtatInscriptionDao etatInscriptionDao,
                        InscriptionDao inscriptionDao) {
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
        /////
        List<ApiInfo> infos = new ArrayList<>();
        List<MessageResponse> responses = new ArrayList<>();
        responses.add(new MessageResponse("Transaction effectuer avec succée",200));
        responses.add(new MessageResponse("Enregistrement introvable", 403));
        responses.add(new MessageResponse("TypeTransaction est introvable"));
        responses.add(new MessageResponse("Compte Financier introvable", 403));
        responses.add(new MessageResponse("employer introvable", 403));
        responses.add(new MessageResponse("financier introvable", 403));
        responses.add(new MessageResponse("une transaction doit avoir au moin une modalité de transaction", 403));
        responses.add(new MessageResponse("ce numero de cheque existe deja", 403));
        responses.add(new MessageResponse("ce code virement existe deja", 403));
        responses.add(new MessageResponse("un numero de cheque est dupliqué", 403));
        responses.add(new MessageResponse("un code de virement est dupliqué", 403));
        responses.add(new MessageResponse("le monant est superieur a ce qui reste a payer", 403));
        Set<ModaliteTransaction> modaliteTransactionSet = new HashSet<>();
            modaliteTransactionSet.add(new ModaliteTransaction("", 300.0, ETypeModaliteTransaction.ESPECES,LocalDate.now(),EStatus.COMPLETE, null));
        modaliteTransactionSet.add(new ModaliteTransaction("123456789", 300, ETypeModaliteTransaction.CHEQUE, LocalDate.now(),EStatus.INCOMPLETE,null));

        ApiInfo payementEtudiant = new ApiInfo("/api/pay/etudiant", "Post",
                "effectue une operation de payment pour un étudiant avec plusieur modalité différantes (espèces, cheque...)",
                new PaymentRequest(589, modaliteTransactionSet, 1 ), "JSON text Message", responses);
        infos.add(payementEtudiant);
        return new ResponseEntity<>(infos, HttpStatus.OK);
        /////////////////////

    }

    @PostMapping("/etudiant")
    public ResponseEntity<?> payEtudiant(@Valid @RequestBody PaymentRequest paymentRequest) {
        Optional<Enregistrement> enregistrement = enregistrementDao.findById(paymentRequest.getIdEnregistrement());
        if (enregistrement.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Enregistrement introvable", 403), HttpStatus.FORBIDDEN);
        }
        Personne personne = enregistrement.get().getIdInscription().getIdEtudiant().getIdPersonne();
        Session session = enregistrement.get().getIdSession();

        Optional<Compte> compteEmployer = compteDao.findById(paymentRequest.getIdCompteFinancier());
        if (compteEmployer.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Compte Financier introvable", 403), HttpStatus.FORBIDDEN);
        }
        Personne personneEmployer = compteEmployer.get().getIdPersonne();
        Optional<Employer> employer = employerDao.findByPersonne(personneEmployer);
        if (employer.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("employer introvable", 403), HttpStatus.FORBIDDEN);
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
            return new ResponseEntity<>(new MessageResponse("financier introvable", 403), HttpStatus.FORBIDDEN);
        double debt = studentDebt.debt(enregistrement.get());
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
        for (ModaliteTransaction m:paymentRequest.getModaliteTransactionSet()){
            if(m.getType().equals(ETypeModaliteTransaction.ESPECES)){
                m.setDate(LocalDate.now());
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
            return ResponseEntity.ok(new MessageResponse("Transaction effectuer avec succée"));
        } else {
            return new ResponseEntity<>(new MessageResponse("le monant est superieur a ce qui reste a payer", 403), HttpStatus.FORBIDDEN);
        }
    }
}
