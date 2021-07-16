package tn.essatin.erp.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.ContactEtudiantDao;
import tn.essatin.erp.dao.EtudiantsDao;
import tn.essatin.erp.model.ContactEtudiant;
import tn.essatin.erp.payload.request.GetByIdRequest;
import tn.essatin.erp.payload.request.ModifierContactEtudiantRequest;
import tn.essatin.erp.payload.response.MessageResponse;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/contactetudiant/")
public class ContacteEtudiantRest {

    @Autowired
    ContactEtudiantDao contactEtudiantDao;
    @Autowired
    EtudiantsDao etudiantsDao;

    @PostMapping("/getbyid")
    public ResponseEntity<?> getById(@Valid @RequestBody GetByIdRequest getByIdRequest) {
        if (contactEtudiantDao.findById(getByIdRequest.getId()).isPresent()) {
            return new ResponseEntity<>(contactEtudiantDao.findById(getByIdRequest.getId()).get(), HttpStatus.OK);
        } else {
            return ResponseEntity.ok(new MessageResponse("Contacte introuvable", 204));
        }
    }

    @PostMapping("/getbyidetudiant")
    public ResponseEntity<?> getByIdEtudiant(@Valid @RequestBody GetByIdRequest getByIdRequest) {
        if (etudiantsDao.findById(getByIdRequest.getId()).isPresent()) {
            List<ContactEtudiant> lc = contactEtudiantDao.findByIdEtudiant(etudiantsDao.findById(getByIdRequest.getId()).get());
            if (lc.size() > 0) {
                return new ResponseEntity<>(lc, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new MessageResponse("l'etudiant n'a pas de contacte", 204), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(new MessageResponse("Etudiant introuvable", 204), HttpStatus.OK);
        }
    }

    @PostMapping("/modifiercontacteetudiant")
        public ResponseEntity<?> ModifierContactEtudiant(@Valid @RequestBody ModifierContactEtudiantRequest modifierContactEtudiantRequest){

        if (contactEtudiantDao.findById(modifierContactEtudiantRequest.getIdContact()).isPresent()){
            ContactEtudiant ce = contactEtudiantDao.findById(modifierContactEtudiantRequest.getIdContact()).get();
            ce.setDesignation(modifierContactEtudiantRequest.getDesignation());
            ce.setNom(modifierContactEtudiantRequest.getNom());
            ce.setNumero(modifierContactEtudiantRequest.getNumero());
            contactEtudiantDao.save(ce);
            return new ResponseEntity<>(new MessageResponse("Modifier avec success", 204), HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(new MessageResponse("Contacte Etudiant introuvable", 403), HttpStatus.FORBIDDEN);
        }

        }


}
