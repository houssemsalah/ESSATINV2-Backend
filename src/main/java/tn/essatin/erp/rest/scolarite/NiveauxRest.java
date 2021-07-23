package tn.essatin.erp.rest.scolarite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.scolarite.NiveauDao;
import tn.essatin.erp.dao.scolarite.ParcoursDao;
import tn.essatin.erp.payload.request.scolarite.ParcoursRequest;
import tn.essatin.erp.payload.response.MessageResponse;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/niveaux/")
public class NiveauxRest {
    final NiveauDao niveauDao;
    final ParcoursDao parcoursDao;

    @Autowired
    public NiveauxRest(NiveauDao niveauDao, ParcoursDao parcoursDao) {
        this.niveauDao = niveauDao;
        this.parcoursDao = parcoursDao;
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {

        return new ResponseEntity<>(niveauDao.findAll(), HttpStatus.OK);
    }

    @PostMapping("/getbyid")
    public ResponseEntity<?> getById(@Valid @RequestBody ParcoursRequest parcoursRequest) {
        if (parcoursDao.findById(parcoursRequest.getIdparcours()).isPresent()) {
            return new ResponseEntity<>(niveauDao.findByParcours(
                    parcoursDao.findById(parcoursRequest.getIdparcours()).get())
                    , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Ressource Indisponible", 403)
                    , HttpStatus.FORBIDDEN);
        }
    }
}
