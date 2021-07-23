package tn.essatin.erp.rest.scolarite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.scolarite.ContactEtudiantDao;
import tn.essatin.erp.dao.scolarite.EtudiantsDao;
import tn.essatin.erp.model.Scolarite.ContactEtudiant;
import tn.essatin.erp.model.Scolarite.Etudiants;
import tn.essatin.erp.payload.request.scolarite.AjouterContactEtudiantRequest;
import tn.essatin.erp.payload.request.GetByIdRequest;
import tn.essatin.erp.payload.request.scolarite.ModifierContactEtudiantRequest;
import tn.essatin.erp.payload.response.CombinedResponse;
import tn.essatin.erp.payload.response.MessageResponse;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/contactetudiant/")
public class ContacteEtudiantRest {

    final ContactEtudiantDao contactEtudiantDao;
    final EtudiantsDao etudiantsDao;

    @Autowired
    public ContacteEtudiantRest(ContactEtudiantDao contactEtudiantDao, EtudiantsDao etudiantsDao) {
        this.contactEtudiantDao = contactEtudiantDao;
        this.etudiantsDao = etudiantsDao;
    }

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
            List<ContactEtudiant> lc = contactEtudiantDao.findByIdEtudiant(
                    etudiantsDao.findById(getByIdRequest.getId()).get());
            if (lc.size() > 0) {
                return new ResponseEntity<>(lc, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(
                        new MessageResponse("l'etudiant n'a pas de contacte", 204), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(
                    new MessageResponse("Etudiant introuvable", 204), HttpStatus.OK);
        }
    }

    @PostMapping("/modifiercontacteetudiant")
    public ResponseEntity<?> ModifierContactEtudiant(
            @Valid @RequestBody ModifierContactEtudiantRequest modifierContactEtudiantRequest) {
        if (contactEtudiantDao.findById(modifierContactEtudiantRequest.getIdContact()).isPresent()) {
            ContactEtudiant ce = contactEtudiantDao.findById(modifierContactEtudiantRequest.getIdContact()).get();
            ce.setDesignation(modifierContactEtudiantRequest.getDesignation());
            ce.setNom(modifierContactEtudiantRequest.getNom());
            ce.setNumero(modifierContactEtudiantRequest.getNumero());
            contactEtudiantDao.save(ce);
            return new ResponseEntity<>(
                    new MessageResponse("Modifier avec success", 204), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(
                    new MessageResponse("Contacte Etudiant introuvable", 403), HttpStatus.FORBIDDEN);
        }
    }

        @PostMapping("/ajoutercontactetudiant")
    public ResponseEntity<?> ajouterContactEtudiant(
            @Valid @RequestBody AjouterContactEtudiantRequest ajouterContactEtudiantRequest) {
        if (etudiantsDao.findById(ajouterContactEtudiantRequest.getIdEtudiant()).isPresent()) {
            Etudiants e = etudiantsDao.findById(ajouterContactEtudiantRequest.getIdEtudiant()).get();
            ContactEtudiant ce = new ContactEtudiant(e, ajouterContactEtudiantRequest.getNumero(),
                    ajouterContactEtudiantRequest.getNom(), ajouterContactEtudiantRequest.getDesignation());
            contactEtudiantDao.save(ce);
            return new ResponseEntity<>(
                    new CombinedResponse(
                        new MessageResponse("Ajouter avec success", 200),
                            "ContacteEtudiant",
                            ce)
                    ,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    new MessageResponse("Etudiant introuvable", 403), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("supprimerbyid")
    public ResponseEntity<?> supprimerById(@Valid @RequestBody GetByIdRequest getByIdRequest) {
        if (contactEtudiantDao.findById(getByIdRequest.getId()).isPresent()) {
            contactEtudiantDao.delete(contactEtudiantDao.findById(getByIdRequest.getId()).get());
            return new ResponseEntity<>(
                    new MessageResponse("Supprimer avec success", 204), HttpStatus.NO_CONTENT);
        } else
            return new ResponseEntity<>(
                    new MessageResponse("Contacte Etudiant introuvable", 403), HttpStatus.FORBIDDEN);
    }

}
