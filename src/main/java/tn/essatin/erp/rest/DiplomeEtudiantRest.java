package tn.essatin.erp.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.DiplomeDao;
import tn.essatin.erp.dao.DiplomeEtudiantDao;
import tn.essatin.erp.dao.EtudiantsDao;
import tn.essatin.erp.model.DiplomeEtudiant;
import tn.essatin.erp.payload.request.DiplomeEtudiantByIdEtudiantRequest;
import tn.essatin.erp.payload.request.DiplomeEtudiantByIdRequest;
import tn.essatin.erp.payload.request.ModifierDiplomeEtudiantRequest;
import tn.essatin.erp.payload.response.MessageResponse;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/diplomeetudiant/")
public class DiplomeEtudiantRest {

    @Autowired
    DiplomeEtudiantDao diplomeEtudiantDao;
    @Autowired
    EtudiantsDao etudiantsDao;
    @Autowired
    DiplomeDao diplomeDao;

    @PostMapping("/getbyid")
    public ResponseEntity<?> getById(@Valid @RequestBody DiplomeEtudiantByIdRequest diplomeEtudiantByIdRequest) {
        return new ResponseEntity<DiplomeEtudiant>(diplomeEtudiantDao.findById(diplomeEtudiantByIdRequest.getIdDiplomeEtudiant()).get(), HttpStatus.OK);
    }

    @PostMapping("/getbyidetudiant")
    public ResponseEntity<?> getByIdEtudiant(@Valid @RequestBody DiplomeEtudiantByIdEtudiantRequest diplomeEtudiantByIdEtudiantRequest) {
        return new ResponseEntity<List>(diplomeEtudiantDao.findByIdEtudiant(etudiantsDao.findById(diplomeEtudiantByIdEtudiantRequest.getIdEtudiant()).get()), HttpStatus.OK);
    }

    @PostMapping("/supprimerbyid")
    public ResponseEntity<?> supprimerById(@Valid @RequestBody DiplomeEtudiantByIdRequest diplomeEtudiantByIdRequest) {
        diplomeEtudiantDao.delete(diplomeEtudiantDao.findById(diplomeEtudiantByIdRequest.getIdDiplomeEtudiant()).get());
        return ResponseEntity.ok(new MessageResponse("Diplome Etudiant Supprimer avec succée!!"));
    }

    @PostMapping("/modifer")
    public ResponseEntity<?> modiferById(@Valid @RequestBody ModifierDiplomeEtudiantRequest modifierDiplomeEtudiantRequest) {
        DiplomeEtudiant de = diplomeEtudiantDao.findById(modifierDiplomeEtudiantRequest.getIdDiplome()).get();
        de.setIdDiplome(diplomeDao.findById(modifierDiplomeEtudiantRequest.getIdDiplome()).get());
        de.setAnnee(modifierDiplomeEtudiantRequest.getAnnee());
        de.setEtablissement(modifierDiplomeEtudiantRequest.getEtablissement());
        de.setNiveau(modifierDiplomeEtudiantRequest.getNiveau());
        de.setSpecialite(modifierDiplomeEtudiantRequest.getSpecialite());
        de.setStatus(modifierDiplomeEtudiantRequest.getStatus());


        return ResponseEntity.ok(new MessageResponse("Diplome Etudiant modifier avec succée!!"));
    }

}
