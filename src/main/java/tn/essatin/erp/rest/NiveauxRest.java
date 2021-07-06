package tn.essatin.erp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.payload.request.ParcoursRequest;
import tn.essatin.erp.dao.NiveauDao;
import tn.essatin.erp.dao.ParcoursDao;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/niveaux/")
public class NiveauxRest {
    @Autowired
    NiveauDao niveauDao;
    @Autowired
    ParcoursDao parcoursDao;
    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<List>(niveauDao.findAll(), HttpStatus.OK);
    }
    @PostMapping("/getbyid")
    public ResponseEntity<?> getById(@Valid @RequestBody ParcoursRequest parcoursRequest){
        return new ResponseEntity<List>(niveauDao.findByParcours(
                parcoursDao.findById(parcoursRequest.getIdparcours()).get())
                , HttpStatus.OK);
    }
}
