package tn.essatin.erp.rest.financier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import java.util.Locale;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/enseignant/")
public class EnseignantRest {
    final EnseignantDao enseignantDao;
    final PersonneDao personneDao;
    final MessageSource messageSource;

    @Autowired
    public EnseignantRest(EnseignantDao enseignantDao, PersonneDao personneDao,MessageSource messageSource) {
        this.enseignantDao = enseignantDao;
        this.personneDao = personneDao;
        this.messageSource=messageSource;
    }
    @GetMapping("/getallenseignant")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(enseignantDao.findAll(), HttpStatus.OK);
    }
    @PostMapping("getenseingant/{id}")
    public  ResponseEntity<?> getEnseingantById(@PathVariable int id){
        Optional<Enseignant> enseignant = enseignantDao.findById(id);
        if (enseignant.isEmpty()){
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.introuvable.enseignant", null, Locale.FRENCH)), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(enseignant.get(), HttpStatus.OK);
    }


}
