package tn.essatin.erp.rest.scolarite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.*;
import tn.essatin.erp.dao.scolarite.EnregistrementDao;
import tn.essatin.erp.dao.scolarite.InscriptionDao;
import tn.essatin.erp.dao.scolarite.NiveauDao;
import tn.essatin.erp.dao.scolarite.NiveauSuivantDao;
import tn.essatin.erp.model.Scolarite.Enregistrement;
import tn.essatin.erp.model.Scolarite.Inscription;
import tn.essatin.erp.model.Scolarite.Niveau;
import tn.essatin.erp.model.Scolarite.NiveauSuivant;
import tn.essatin.erp.payload.request.GetByIdRequest;
import tn.essatin.erp.payload.response.MessageResponse;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/niveausuivant/")
public class NiveauSuivantRest {
    final InscriptionDao inscriptionDao;
    final EnregistrementDao enregistrementDao;
    final NiveauDao niveauDao;
    final NiveauSuivantDao niveauSuivantDao;
    final SessionDao sessionDao;

    @Autowired
    public NiveauSuivantRest(InscriptionDao inscriptionDao, EnregistrementDao enregistrementDao,
                             NiveauDao niveauDao, NiveauSuivantDao niveauSuivantDao, SessionDao sessionDao) {
        this.inscriptionDao = inscriptionDao;
        this.enregistrementDao = enregistrementDao;
        this.niveauDao = niveauDao;
        this.niveauSuivantDao = niveauSuivantDao;
        this.sessionDao = sessionDao;
    }

    @PostMapping("/getniveauxsuivantbyidinscription")
    public ResponseEntity<?> getNiveauxSuivantByIdInscription(@Valid @RequestBody GetByIdRequest getByIdRequest) {
        if (inscriptionDao.findById(getByIdRequest.getId()).isPresent()) {
            Inscription i = inscriptionDao.findById(getByIdRequest.getId()).get();
            Enregistrement e = enregistrementDao.findTopByIdInscriptionOrderByIdEnregistrementDesc(i);
            List<Niveau> ln = new ArrayList<>();
            ln.add(e.getIdNiveau());
            List<NiveauSuivant> lns = niveauSuivantDao.findByIdNiveau(e.getIdNiveau());
            for (NiveauSuivant ns : lns) {
                ln.add(ns.getIdNiveauSuivant());
            }
            return new ResponseEntity<>(ln, HttpStatus.OK);
        } else
            return new ResponseEntity<>(
                    new MessageResponse("Ressource indisponible", 403), HttpStatus.FORBIDDEN);

    }


    @PostMapping("/getautreniveauxpossiblebyidinscription")
    public ResponseEntity<?> getAutreNiveauxPossibleByIdInscription(@Valid @RequestBody GetByIdRequest getByIdRequest) {
        if (inscriptionDao.findById(getByIdRequest.getId()).isPresent()) {
            Inscription i = inscriptionDao.findById(getByIdRequest.getId()).get();
            List<Enregistrement> le = enregistrementDao.findByIdInscription(i);
            Enregistrement e = le.get(le.size()-2);
            List<Niveau> ln = new ArrayList<>();
            ln.add(e.getIdNiveau());
            List<NiveauSuivant> lns = niveauSuivantDao.findByIdNiveau(e.getIdNiveau());
            for (NiveauSuivant ns : lns) {
                ln.add(ns.getIdNiveauSuivant());
            }
            return new ResponseEntity<>(ln, HttpStatus.OK);
        } else
            return new ResponseEntity<>(
                    new MessageResponse("Ressource indisponible", 403), HttpStatus.FORBIDDEN);

    }
}
