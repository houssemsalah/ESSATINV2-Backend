package tn.essatin.erp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.DiplomeDao;
import tn.essatin.erp.dao.NationaliteDao;
import tn.essatin.erp.dao.PersonneDao;
import tn.essatin.erp.dao.TypeIdentificateurDao;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.payload.request.ModifierPersonne;
import tn.essatin.erp.payload.response.MessageResponse;

import javax.validation.Valid;
import java.time.LocalDate;
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

    @PostMapping("/modifierpersonne")
    public ResponseEntity<?> modifierPersonne(@Valid @RequestBody ModifierPersonne modifierPersonne){
        Personne p = personneDao.findById(modifierPersonne.getIdPersonne()).get();
        p.setNom(modifierPersonne.getNom());
        p.setPrenom(modifierPersonne.getPrenom());
        p.setMail(modifierPersonne.getMail());
        p.setAdresse(modifierPersonne.getAdresse());
        p.setTel(modifierPersonne.getTel());
        p.setDateDeNaissance(modifierPersonne.getDateDeNaissance());
        p.setLieuDeNaissance(modifierPersonne.getLieuDeNaissance());
        p.setIdIdentificateur(typeIdentificateurDao.findById(modifierPersonne.getIdTypeIdentificateur()).get());
        p.setNumeroIdentificateur(modifierPersonne.getNumeroIdentificateur());
        p.setSexe(modifierPersonne.getSexe());
        p.setIdNationalite(nationaliteDao.findById(modifierPersonne.getIdNationalite()).get());
        personneDao.save(p);
        return ResponseEntity.ok(new MessageResponse("Les informations on été modifier avec succée!!"));

    }

}
