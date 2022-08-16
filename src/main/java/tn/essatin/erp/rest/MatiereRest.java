package tn.essatin.erp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.MatiereDao;
import tn.essatin.erp.dao.scolarite.EnregistrementDao;
import tn.essatin.erp.model.Matiere;
import tn.essatin.erp.model.Scolarite.Niveau;
import tn.essatin.erp.payload.response.MessageResponse;




import javax.validation.Valid;
import java.util.Optional;
 @CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/matiere/")
public class MatiereRest {

    final MatiereDao matiereDao;
    final EnregistrementDao enregistrementDao;
    @Autowired
    public MatiereRest(MatiereDao matiereDao, EnregistrementDao enregistrementDao) {
        this.matiereDao = matiereDao;
        this.enregistrementDao = enregistrementDao;
    }


    @GetMapping("/getallmatieres")
    public ResponseEntity<?> getAllMatieres() {
        return new ResponseEntity<>(matiereDao.findAll(), HttpStatus.OK);

    }

    @GetMapping("/getmatieresbyniveau")
    public ResponseEntity<?> getMatieresByNiveau(Niveau idNiveau) {
        return new ResponseEntity<>(matiereDao.findMatieresByNiveau( idNiveau), HttpStatus.OK);
    }
   @PostMapping("/addmatiere")
   public ResponseEntity<?> addMatiere(@Valid @RequestBody Matiere matiere){
      /// if (!matiere.getNomMatiere().isEmpty()) {
       //Matiere m = new Matiere(matiere)
           matiereDao.save(matiere) ;

           return new ResponseEntity<>(
                   new MessageResponse("Matiere ajouter avec succée!!"), HttpStatus.OK);
   //    } else
    //       return new ResponseEntity<>(
     //              new MessageResponse("Ressources indisponibles", 403), HttpStatus.FORBIDDEN);

   }

    @PutMapping("/modifiermatiere")
    public ResponseEntity<?> modifierMatiere(@Valid Matiere matiere){
        if (!matiere.getNomMatiere().isEmpty()) {
            matiereDao.saveAndFlush(matiere) ;

            return new ResponseEntity<>(
                    new MessageResponse("Matiere modifier avec succée!!"), HttpStatus.OK);
        } else
            return new ResponseEntity<>(
                    new MessageResponse("Ressources indisponibles", 403), HttpStatus.FORBIDDEN);

    }

    @DeleteMapping ("/supprimermatiere")
    public ResponseEntity<?> supprimerMatiere(@Valid int idMatiere){
     Optional<Matiere> matiere =matiereDao.findById(idMatiere);
        if (!matiere.isEmpty()) {
            matiereDao.deleteById(idMatiere);

            return new ResponseEntity<>(
                    new MessageResponse("Matiere supprimée avec succée!!"), HttpStatus.OK);
        } else
            return new ResponseEntity<>(
                    new MessageResponse("Ressources indisponibles", 403), HttpStatus.FORBIDDEN);

    }

}
