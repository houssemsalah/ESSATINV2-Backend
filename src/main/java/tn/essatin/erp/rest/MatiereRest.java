package tn.essatin.erp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.MatiereDao;
import tn.essatin.erp.dao.financier.EnseignantDao;
import tn.essatin.erp.dao.scolarite.EnregistrementDao;
import tn.essatin.erp.dao.scolarite.NiveauDao;
import tn.essatin.erp.model.Matiere;
import tn.essatin.erp.model.Scolarite.Niveau;
import tn.essatin.erp.model.financier.Enseignant;
import tn.essatin.erp.payload.response.MessageResponse;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/matiere/")
public class MatiereRest {

    final MatiereDao matiereDao;
    final EnregistrementDao enregistrementDao;
    final EnseignantDao enseignantDao;
    final NiveauDao niveauDao;

    @Autowired
    public MatiereRest(MatiereDao matiereDao, EnregistrementDao enregistrementDao, EnseignantDao enseignantDao, NiveauDao niveauDao) {
        this.matiereDao = matiereDao;
        this.enregistrementDao = enregistrementDao;
        this.enseignantDao = enseignantDao;
        this.niveauDao = niveauDao;
    }


    @GetMapping("/getallmatieres" +
            "")
    public ResponseEntity<?> getAllMatieres() {
        return new ResponseEntity<>(matiereDao.findAll(), HttpStatus.OK);

    }

    @GetMapping("/getmatieresbyniveau")
    public ResponseEntity<?> getMatieresByNiveau(Niveau idNiveau) {
        return new ResponseEntity<>(matiereDao.findMatieresByNiveau(idNiveau), HttpStatus.OK);
    }

    @PostMapping("/addmatiere")
    public ResponseEntity<?> addMatiere(@Valid @RequestBody Matiere matiere) {
        Collection<Enseignant> enseignants = new ArrayList<>();
        for (Enseignant e : matiere.getEnseignants()) {
            Enseignant enseignant = enseignantDao.getOne(e.getId());
            if (enseignant != null)
                enseignants.add(enseignant);
            else
                return new ResponseEntity<>(new MessageResponse("Enseignant Introuvable", 403), HttpStatus.FORBIDDEN);
        }
        matiere.setEnseignants(enseignants);

        Niveau niveau = niveauDao.getOne(matiere.getNiveau().getIdNiveau());
        if (niveau != null)
            matiere.setNiveau(niveau);
        else
            return new ResponseEntity<>(new MessageResponse("Niveaux Introuvable", 403), HttpStatus.FORBIDDEN);

        matiereDao.save(matiere);

        return new ResponseEntity<>(
                new MessageResponse("Matiere ajouter avec succée!!"), HttpStatus.OK);

    }

    @PutMapping("/modifiermatiere")
    public ResponseEntity<?> modifierMatiere(@Valid Matiere matiere) {
        if (!matiere.getNomMatiere().isEmpty()) {
            matiereDao.saveAndFlush(matiere);

            return new ResponseEntity<>(
                    new MessageResponse("Matiere modifier avec succée!!"), HttpStatus.OK);
        } else
            return new ResponseEntity<>(
                    new MessageResponse("Ressources indisponibles", 403), HttpStatus.FORBIDDEN);

    }

    @DeleteMapping("/supprimermatiere")
    public ResponseEntity<?> supprimerMatiere(@Valid int idMatiere) {
        Optional<Matiere> matiere = matiereDao.findById(idMatiere);
        if (!matiere.isEmpty()) {
            matiereDao.deleteById(idMatiere);

            return new ResponseEntity<>(
                    new MessageResponse("Matiere supprimée avec succée!!"), HttpStatus.OK);
        } else
            return new ResponseEntity<>(
                    new MessageResponse("Ressources indisponibles", 403), HttpStatus.FORBIDDEN);

    }

}
