package tn.essatin.erp.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.EmployerDao;
import tn.essatin.erp.dao.EnseignantDao;
import tn.essatin.erp.dao.FormateurDao;
import tn.essatin.erp.dao.VacataireDao;
import tn.essatin.erp.model.Employer;
import tn.essatin.erp.model.Enseignant;
import tn.essatin.erp.model.Formateur;
import tn.essatin.erp.model.Vacataire;
import tn.essatin.erp.payload.request.SalarierRequest;

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
        return new ResponseEntity<List>(employerDao.findAll(), HttpStatus.OK);
    }

    @PostMapping("getemployerbyid")
    public  ResponseEntity<?> getEmployerById(@Valid @RequestBody SalarierRequest salarierRequest){
        return new ResponseEntity<Employer>(
                employerDao.findById(salarierRequest.getIdSallarier()).get(),
                HttpStatus.OK);
    }
    @PostMapping("getenseingantbyid")
    public  ResponseEntity<?> getEnseingantById(@Valid @RequestBody SalarierRequest salarierRequest){
        return new ResponseEntity<Enseignant>(
                enseignantDao.findById(salarierRequest.getIdSallarier()).get(),
                HttpStatus.OK);
    }
    @PostMapping("getvacatairebyid")
    public  ResponseEntity<?> getVacataireById(@Valid @RequestBody SalarierRequest salarierRequest){
        return new ResponseEntity<Vacataire>(
                vacataireDao.findById(salarierRequest.getIdSallarier()).get(),
                HttpStatus.OK);
    }
    @PostMapping("getformateurbyid")
    public  ResponseEntity<?> getFormateurById(@Valid @RequestBody SalarierRequest salarierRequest){
        return new ResponseEntity<Formateur>(
                formateurDao.findById(salarierRequest.getIdSallarier()).get(),
                HttpStatus.OK);
    }
}
