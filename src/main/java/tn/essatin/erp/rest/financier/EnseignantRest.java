package tn.essatin.erp.rest.financier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.PersonneDao;
import tn.essatin.erp.dao.financier.EnseignantDao;
import tn.essatin.erp.model.financier.Enseignant;
import tn.essatin.erp.payload.request.financier.SalarierRequest;
import tn.essatin.erp.payload.response.MessageResponse;

import javax.management.openmbean.OpenType;
import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/enseignant/")
public class EnseignantRest {
    final EnseignantDao enseignantDao;
    final PersonneDao personneDao;
    @Autowired
    public EnseignantRest(EnseignantDao enseignantDao, PersonneDao personneDao) {
        this.enseignantDao = enseignantDao;
        this.personneDao = personneDao;
    }
    @GetMapping("/getallenseignant")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(enseignantDao.findAll(), HttpStatus.OK);
    }
    @PostMapping("getenseingant/{id}")
    public  ResponseEntity<?> getEnseingantById(@PathVariable int id){
        Optional<Enseignant> enseignant = enseignantDao.findById(id);
        if (enseignant.isEmpty()){
            return new ResponseEntity<>(new MessageResponse("Enseignant introuvable"), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(enseignant.get(), HttpStatus.OK);
    }


}
