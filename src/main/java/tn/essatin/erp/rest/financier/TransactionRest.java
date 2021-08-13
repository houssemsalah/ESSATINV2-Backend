package tn.essatin.erp.rest.financier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.financier.TransactionDao;
import tn.essatin.erp.model.financier.ModaliteTransaction;
import tn.essatin.erp.model.financier.Transaction;
import tn.essatin.erp.payload.response.MessageResponse;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/transaction/")
public class TransactionRest {
    final TransactionDao transactionDao;

    @Autowired

    public TransactionRest(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @GetMapping("/getalltransaction")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(transactionDao.findAll(), HttpStatus.OK);
    }
    @GetMapping("/gettransaction/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable int id) {
        Optional<Transaction> transaction = transactionDao.findById(id);
        if (transaction.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("transaction introuvable"), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(transaction.get(), HttpStatus.OK);
    }

}
