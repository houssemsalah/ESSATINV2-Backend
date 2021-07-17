package tn.essatin.erp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.*;
import tn.essatin.erp.model.Enregistrement;
import tn.essatin.erp.model.Inscription;
import tn.essatin.erp.model.Niveau;
import tn.essatin.erp.model.NiveauSuivant;
import tn.essatin.erp.payload.request.GetByIdRequest;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/niveausuivant/")
public class NiveauSuivantRest {
    @Autowired
    InscriptionDao inscriptionDao;
    @Autowired
    EnregistrementDao enregistrementDao;
    @Autowired
    NiveauDao niveauDao;
    @Autowired
    NiveauSuivantDao niveauSuivantDao;
    @Autowired
    SessionDao sessionDao;

    @PostMapping("/getniveauxsuivantbyidinscription")
    public ResponseEntity<?> getNiveauxSuivantByIdInscription(@Valid @RequestBody GetByIdRequest getByIdRequest){
        Inscription i = inscriptionDao.findById(getByIdRequest.getId()).get();
        Enregistrement e = enregistrementDao.findTopByIdInscriptionOrderByIdEnregistrementDesc(i);
        List<Niveau> ln = new ArrayList<>();
        ln.add(e.getIdNiveau());
        List<NiveauSuivant> lns= niveauSuivantDao.findByIdNiveau(e.getIdNiveau());
        for (NiveauSuivant ns: lns) {
            ln.add(ns.getIdNiveauSuivant());
        }
        return new ResponseEntity<>(ln, HttpStatus.OK);
    }
}
