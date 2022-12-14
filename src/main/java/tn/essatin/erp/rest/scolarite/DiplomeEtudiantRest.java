package tn.essatin.erp.rest.scolarite;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.scolarite.DiplomeDao;
import tn.essatin.erp.dao.scolarite.DiplomeEtudiantDao;
import tn.essatin.erp.dao.scolarite.EtudiantsDao;
import tn.essatin.erp.model.Scolarite.DiplomeEtudiant;
import tn.essatin.erp.model.Scolarite.Etudiants;
import tn.essatin.erp.payload.request.scolarite.AjouterDiplomeEtudiantRequest;
import tn.essatin.erp.payload.request.scolarite.DiplomeEtudiantByIdEtudiantRequest;
import tn.essatin.erp.payload.request.scolarite.DiplomeEtudiantByIdRequest;
import tn.essatin.erp.payload.request.scolarite.ModifierDiplomeEtudiantRequest;
import tn.essatin.erp.payload.response.CombinedResponse;
import tn.essatin.erp.payload.response.MessageResponse;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/diplomeetudiant/")
public class DiplomeEtudiantRest {

    final DiplomeEtudiantDao diplomeEtudiantDao;
    final EtudiantsDao etudiantsDao;
    final DiplomeDao diplomeDao;

    @Autowired
    public DiplomeEtudiantRest(DiplomeEtudiantDao diplomeEtudiantDao, EtudiantsDao etudiantsDao, DiplomeDao diplomeDao) {
        this.diplomeEtudiantDao = diplomeEtudiantDao;
        this.etudiantsDao = etudiantsDao;
        this.diplomeDao = diplomeDao;
    }

    @PostMapping("/getbyid")
    public ResponseEntity<?> getById(@Valid @RequestBody DiplomeEtudiantByIdRequest diplomeEtudiantByIdRequest) {
        if (diplomeEtudiantDao.findById(diplomeEtudiantByIdRequest.getIdDiplomeEtudiant()).isPresent())
            return new ResponseEntity<>(
                    diplomeEtudiantDao.findById(diplomeEtudiantByIdRequest.getIdDiplomeEtudiant()).get(), HttpStatus.OK);
        else
            return ResponseEntity.ok(new MessageResponse("diplome introuvable!", 204));
    }

    @PostMapping("/getbyidetudiant")
    public ResponseEntity<?> getByIdEtudiant(
            @Valid @RequestBody DiplomeEtudiantByIdEtudiantRequest diplomeEtudiantByIdEtudiantRequest) {
        Etudiants e;
        if (etudiantsDao.findById(diplomeEtudiantByIdEtudiantRequest.getIdEtudiant()).isPresent()) {
            e = etudiantsDao.findById(diplomeEtudiantByIdEtudiantRequest.getIdEtudiant()).get();
            List<DiplomeEtudiant> l = diplomeEtudiantDao.findByIdEtudiant(e);
            if (l.size() > 0)
                return new ResponseEntity<>(l, HttpStatus.OK);
            else
                return ResponseEntity.ok(new MessageResponse("Pas de diplome pour cet etudiant!", 204));
        } else
            return ResponseEntity.ok(new MessageResponse("Etudiant introuvable!", 204));
    }

    @PostMapping("/supprimerbyid")
    public ResponseEntity<?> supprimerById(@Valid @RequestBody DiplomeEtudiantByIdRequest diplomeEtudiantByIdRequest) {
        if (diplomeEtudiantDao.findById(diplomeEtudiantByIdRequest.getIdDiplomeEtudiant()).isPresent()) {
            diplomeEtudiantDao.delete(
                    diplomeEtudiantDao.findById(diplomeEtudiantByIdRequest.getIdDiplomeEtudiant()).get());
            return new ResponseEntity<>(
                    new MessageResponse("Diplome Etudiant Supprimer avec succ??e!!"), HttpStatus.OK);
        } else
            return new ResponseEntity<>(
                    new MessageResponse("Diplome introuvable", 403), HttpStatus.FORBIDDEN);
    }

    @PostMapping("/modifer")
    public ResponseEntity<?> modiferById(
            @Valid @RequestBody ModifierDiplomeEtudiantRequest modifierDiplomeEtudiantRequest) {
        if (
                diplomeEtudiantDao.findById(modifierDiplomeEtudiantRequest.getIdDiplomeEtudiant()).isPresent()
                        && diplomeDao.findById(modifierDiplomeEtudiantRequest.getIdDiplome()).isPresent()
        ) {
            DiplomeEtudiant de = diplomeEtudiantDao.findById(modifierDiplomeEtudiantRequest.getIdDiplomeEtudiant()).get();
            de.setIdDiplome(diplomeDao.findById(modifierDiplomeEtudiantRequest.getIdDiplome()).get());
            de.setAnnee(modifierDiplomeEtudiantRequest.getAnnee());
            de.setEtablissement(modifierDiplomeEtudiantRequest.getEtablissement());
            de.setNiveau(modifierDiplomeEtudiantRequest.getNiveau());
            de.setSpecialite(modifierDiplomeEtudiantRequest.getSpecialite());
            de.setStatus(modifierDiplomeEtudiantRequest.getStatus());
            diplomeEtudiantDao.save(de);
            return new ResponseEntity<>(new MessageResponse("Diplome Etudiant modifier avec succ??e!!"), HttpStatus.OK);
        } else
            return new ResponseEntity<>(
                    new MessageResponse("Ressources introuvable", 403), HttpStatus.FORBIDDEN);
    }

    @PostMapping("/ajouter")
    public ResponseEntity<?> Ajouter(@Valid @RequestBody AjouterDiplomeEtudiantRequest ajouterDiplomeEtudiantRequest) {
        if (
                etudiantsDao.findById(ajouterDiplomeEtudiantRequest.getIdEtudiant()).isPresent()
                        && diplomeDao.findById(ajouterDiplomeEtudiantRequest.getIdDiplome()).isPresent()
        ) {
            DiplomeEtudiant de = new DiplomeEtudiant();
            de.setIdDiplome(diplomeDao.findById(ajouterDiplomeEtudiantRequest.getIdDiplome()).get());
            de.setIdEtudiant(etudiantsDao.findById(ajouterDiplomeEtudiantRequest.getIdEtudiant()).get());
            de.setAnnee(ajouterDiplomeEtudiantRequest.getAnnee());
            de.setSpecialite(ajouterDiplomeEtudiantRequest.getSpecialite());
            de.setNiveau(ajouterDiplomeEtudiantRequest.getNiveau());
            de.setStatus(ajouterDiplomeEtudiantRequest.getStatus());
            de.setEtablissement(ajouterDiplomeEtudiantRequest.getEtablissement());
            diplomeEtudiantDao.save(de);
            return new ResponseEntity<>(
                    new CombinedResponse(
                    new MessageResponse("Diplome Etudiant Ajouter avec succ??e!!", 200),
                            "DiplomeEtudiant",
                            de)
                    , HttpStatus.OK);
        } else
            return new ResponseEntity<>(
                    new MessageResponse("Ressources introuvable", 403), HttpStatus.FORBIDDEN);
    }
}
