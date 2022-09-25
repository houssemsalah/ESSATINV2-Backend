package tn.essatin.erp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.CompteDao;
import tn.essatin.erp.dao.PersonneDao;

import javax.validation.Valid;

@CrossOrigin(origins ="*", maxAge = 3600)
@RestController
@RequestMapping("/api/compte/")
public class CompteRest {

    final PersonneDao personneDao;
    final CompteDao compteDao;


    @Autowired

    public CompteRest(PersonneDao personneDao, CompteDao compteDao) {
        this.personneDao = personneDao;

        this.compteDao = compteDao;
    }


    @GetMapping("/getall" +
            "")
    public ResponseEntity<?> getAllComptes() {
        return new ResponseEntity<>(compteDao.findAll(), HttpStatus.OK);

    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getCompteNiveau( @Valid @RequestBody int id) {

        return new ResponseEntity<>(compteDao.findById(id), HttpStatus.OK);
    }



}
