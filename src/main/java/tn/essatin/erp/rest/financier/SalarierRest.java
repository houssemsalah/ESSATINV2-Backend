package tn.essatin.erp.rest.financier;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.financier.EmployerDao;
import tn.essatin.erp.dao.financier.EnseignantDao;
import tn.essatin.erp.dao.financier.FormateurDao;
import tn.essatin.erp.dao.financier.VacataireDao;
import tn.essatin.erp.model.financier.Employer;
import tn.essatin.erp.model.financier.Enseignant;
import tn.essatin.erp.model.financier.Formateur;
import tn.essatin.erp.model.financier.Vacataire;
import tn.essatin.erp.payload.request.financier.SalarierRequest;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/salarier/")
public class SalarierRest {

    @Autowired
    EmployerDao employerDao;
    @Autowired
    EnseignantDao enseignantDao;
    @Autowired
    VacataireDao vacataireDao;
    @Autowired
    FormateurDao formateurDao;

    @GetMapping("/getallEmployer")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(employerDao.findAll(), HttpStatus.OK);
    }

    @PostMapping("getemployerbyid")
    public  ResponseEntity<?> getEmployerById(@Valid @RequestBody SalarierRequest salarierRequest){
        return new ResponseEntity<>(
                employerDao.findById(salarierRequest.getIdSallarier()).get(),
                HttpStatus.OK);
    }
    @PostMapping("getenseingantbyid")
    public  ResponseEntity<?> getEnseingantById(@Valid @RequestBody SalarierRequest salarierRequest){
        return new ResponseEntity<>(
                enseignantDao.findById(salarierRequest.getIdSallarier()).get(),
                HttpStatus.OK);
    }
    @PostMapping("getvacatairebyid")
    public  ResponseEntity<?> getVacataireById(@Valid @RequestBody SalarierRequest salarierRequest){
        return new ResponseEntity<>(
                vacataireDao.findById(salarierRequest.getIdSallarier()).get(),
                HttpStatus.OK);
    }
    @PostMapping("getformateurbyid")
    public  ResponseEntity<?> getFormateurById(@Valid @RequestBody SalarierRequest salarierRequest){
        return new ResponseEntity<>(
                formateurDao.findById(salarierRequest.getIdSallarier()).get(),
                HttpStatus.OK);
    }
}
