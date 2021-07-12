package tn.essatin.erp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.essatin.erp.dao.DiplomeDao;
import tn.essatin.erp.dao.NationaliteDao;
import tn.essatin.erp.dao.PersonneDao;
import tn.essatin.erp.dao.TypeIdentificateurDao;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/personne/")
public class PersonneRest {
    @Autowired
    PersonneDao personneDao;
    @Autowired
    TypeIdentificateurDao typeIdentificateurDao;
    @Autowired
    NationaliteDao nationaliteDao;
    @Autowired
    DiplomeDao diplomeDao;

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<List>(personneDao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/gettypeidentificateurs")
    public ResponseEntity<?> getTypeIdentificateurs(){
        return new ResponseEntity<List>(typeIdentificateurDao.findAll(),HttpStatus.OK);
    }
    @GetMapping("/getlistnationalites")
    public ResponseEntity<?> getListNationalites(){
        return new ResponseEntity<List>(nationaliteDao.findAll(),HttpStatus.OK);
    }
    @GetMapping("/getlistdiplomes")
    public ResponseEntity<?> getListDiplomes(){
        return new ResponseEntity<List>(diplomeDao.findAll(),HttpStatus.OK);
    }

}
