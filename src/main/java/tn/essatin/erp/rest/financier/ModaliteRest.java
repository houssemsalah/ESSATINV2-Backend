package tn.essatin.erp.rest.financier;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.financier.ModaliteTransactionDao;
import tn.essatin.erp.dao.financier.TransactionDao;
import tn.essatin.erp.model.financier.EStatus;
import tn.essatin.erp.model.financier.ETypeModaliteTransaction;
import tn.essatin.erp.model.financier.ModaliteTransaction;
import tn.essatin.erp.payload.response.MessageResponse;

import java.util.Locale;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/modalite/")
public class ModaliteRest {
    final ModaliteTransactionDao modaliteTransactionDao;
    final TransactionDao transactionDao;
    final MessageSource messageSource;


    public ModaliteRest(ModaliteTransactionDao modaliteTransactionDao, TransactionDao transactionDao, MessageSource messageSource) {
        this.modaliteTransactionDao = modaliteTransactionDao;
        this.transactionDao = transactionDao;
        this.messageSource = messageSource;
    }

    @GetMapping("/getallmodalite")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(modaliteTransactionDao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getmodalite/{id}")
    public ResponseEntity<?> getModaliteById(@PathVariable int id) {
        Optional<ModaliteTransaction> modaliteTransaction = modaliteTransactionDao.findById(id);
        if (modaliteTransaction.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("modalité introuvable"), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(modaliteTransaction.get(), HttpStatus.OK);
    }

    @GetMapping("/validercheque/{idModalite}")
    public ResponseEntity<?> vliderChequeByIdModalite(@PathVariable int idModalite) {
        Optional<ModaliteTransaction> modaliteTransaction = modaliteTransactionDao.findById(idModalite);
        if (modaliteTransaction.isEmpty()) {

            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.introuvable.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        if (modaliteTransaction.get().getStatus().equals(EStatus.CANCELED)) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.dejaannule.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        if (!modaliteTransaction.get().getType().equals(ETypeModaliteTransaction.CHEQUE)) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.notcheck.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        if (modaliteTransaction.get().getStatus().equals(EStatus.REJECTED)) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.dejarejete.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        if (modaliteTransaction.get().getStatus().equals(EStatus.COMPLETE)) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.dejavalide.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        modaliteTransaction.get().setStatus(EStatus.COMPLETE);
        modaliteTransactionDao.save(modaliteTransaction.get());
        return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("message.modalite.complete.success", null, Locale.FRENCH), 200), HttpStatus.OK);
    }

    @GetMapping("/rejetecheque/{idModalite}")
    public ResponseEntity<?> rejeterChequeByIdModalite(@PathVariable int idModalite) {
        Optional<ModaliteTransaction> modaliteTransaction = modaliteTransactionDao.findById(idModalite);
        if (modaliteTransaction.isEmpty()) {

            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.introuvable.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        if (modaliteTransaction.get().getStatus().equals(EStatus.CANCELED)) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.dejaannule.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        if (!modaliteTransaction.get().getType().equals(ETypeModaliteTransaction.CHEQUE)) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.notcheck.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        if (modaliteTransaction.get().getStatus().equals(EStatus.REJECTED)) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.dejarejete.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        if (modaliteTransaction.get().getStatus().equals(EStatus.COMPLETE)) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.dejavalide.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        modaliteTransaction.get().setStatus(EStatus.REJECTED);
        modaliteTransactionDao.save(modaliteTransaction.get());
        return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("message.modalite.rejete.success", null, Locale.FRENCH), 200), HttpStatus.OK);
    }

    @GetMapping("/annulermodalite/{idModalite}")
    public ResponseEntity<?> annulerModaliteById(@PathVariable int idModalite) {
        Optional<ModaliteTransaction> modaliteTransaction = modaliteTransactionDao.findById(idModalite);
        if (modaliteTransaction.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.introuvable.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        if (modaliteTransaction.get().getStatus().equals(EStatus.CANCELED)) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.dejaannule.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        modaliteTransaction.get().setStatus(EStatus.CANCELED);
        modaliteTransactionDao.save(modaliteTransaction.get());
        return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("message.modalite.annuler.success", null, Locale.FRENCH), 200), HttpStatus.OK);
    }

}
