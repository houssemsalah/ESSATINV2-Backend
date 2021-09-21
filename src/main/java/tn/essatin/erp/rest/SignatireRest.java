package tn.essatin.erp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.essatin.erp.dao.SignataireDao;
import tn.essatin.erp.model.Signataire;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/signatire/")
public class SignatireRest {
    final SignataireDao signataireDao;

    public SignatireRest(SignataireDao signataireDao) {
        this.signataireDao = signataireDao;
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getall(){
        List<Signataire> signataires = signataireDao.findAll();
        return new ResponseEntity<>(signataires, HttpStatus.OK);
    }
}
