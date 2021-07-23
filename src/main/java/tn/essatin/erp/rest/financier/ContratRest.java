package tn.essatin.erp.rest.financier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.financier.ContratDao;
import tn.essatin.erp.payload.request.financier.ContratRequest;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/contrat/")
public class ContratRest {
    @Autowired
    ContratDao contratDao;
    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(contratDao.findAll(), HttpStatus.OK);
    }
    @PostMapping("/addcontrat")
    public void addContrat(@Valid @RequestBody ContratRequest contratRequest) {


    }
    }


