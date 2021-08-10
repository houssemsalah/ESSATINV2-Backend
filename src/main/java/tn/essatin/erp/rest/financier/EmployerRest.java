package tn.essatin.erp.rest.financier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.PersonneDao;
import tn.essatin.erp.dao.financier.EmployerDao;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.financier.ESituationMaritale;
import tn.essatin.erp.model.financier.ETypeEmployer;
import tn.essatin.erp.model.financier.Employer;
import tn.essatin.erp.payload.request.financier.AjouterEmployerRequest;
import tn.essatin.erp.payload.response.MessageResponse;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/employer/")
public class EmployerRest {

    final EmployerDao employerDao;
    final PersonneDao personneDao;

    @Autowired
    public EmployerRest(EmployerDao employerDao, PersonneDao personneDao) {
        this.employerDao = employerDao;
        this.personneDao = personneDao;
    }


    @GetMapping("/getallEmployer")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(employerDao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getemployer/{id}")
    public ResponseEntity<?> getEmployerById(@PathVariable int id) {
        Optional<Employer> employer = employerDao.findById(id);
        if (employer.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Employer introuvable"), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(employer.get(), HttpStatus.OK);
    }

    @PostMapping("/ajouteremployer")
    public ResponseEntity<?> ajouterEmployer(@Valid @RequestBody AjouterEmployerRequest ajouterEmployerRequest) {
        ESituationMaritale situationMaritale;
        ETypeEmployer typeEmployer;
        Optional<Personne> personne = personneDao.findById(ajouterEmployerRequest.getIdpersonne());
        if (personne.isEmpty())
            return new ResponseEntity<>(new MessageResponse("Personne introuvable"), HttpStatus.FORBIDDEN);
        try {
            situationMaritale = ESituationMaritale.valueOf(ajouterEmployerRequest.getSituationMaritale());
        } catch (Exception E) {
            return new ResponseEntity<>(new MessageResponse("Situation marietal incorecte"), HttpStatus.FORBIDDEN);
        }
        try {
            typeEmployer = ETypeEmployer.valueOf(ajouterEmployerRequest.getTypeEmployer());
        } catch (Exception E) {
            return new ResponseEntity<>(new MessageResponse("Type Employer incorecte"), HttpStatus.FORBIDDEN);
        }



    }

}
