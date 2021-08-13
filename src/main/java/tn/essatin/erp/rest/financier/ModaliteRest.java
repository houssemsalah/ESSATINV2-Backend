package tn.essatin.erp.rest.financier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.financier.ModaliteTransactionDao;
import tn.essatin.erp.dao.financier.TransactionDao;
import tn.essatin.erp.model.financier.ModaliteTransaction;
import tn.essatin.erp.payload.response.MessageResponse;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/modalite/")
public class ModaliteRest {
    final ModaliteTransactionDao modaliteTransactionDao;
    final TransactionDao transactionDao;

    @Autowired

    public ModaliteRest(ModaliteTransactionDao modaliteTransactionDao, TransactionDao transactionDao) {
        this.modaliteTransactionDao = modaliteTransactionDao;
        this.transactionDao = transactionDao;
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


}
