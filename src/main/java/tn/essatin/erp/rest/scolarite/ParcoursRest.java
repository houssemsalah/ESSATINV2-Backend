package tn.essatin.erp.rest.scolarite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.scolarite.ParcoursDao;
import tn.essatin.erp.dao.scolarite.SpecialiteDao;
import tn.essatin.erp.payload.request.scolarite.SpecialiteRequest;
import tn.essatin.erp.payload.response.MessageResponse;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/parcours/")
public class ParcoursRest {
    final ParcoursDao parcoursDao;
    final SpecialiteDao specialiteDao;

    @Autowired
    public ParcoursRest(ParcoursDao parcoursDao, SpecialiteDao specialiteDao) {
        this.parcoursDao = parcoursDao;
        this.specialiteDao = specialiteDao;
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(parcoursDao.findAll(), HttpStatus.OK);
    }

    @PostMapping("/getbyid")
    public ResponseEntity<?> getById(@Valid @RequestBody SpecialiteRequest specialiteRequest) {
        if (specialiteDao.findById(specialiteRequest.getIdspecialite()).isPresent())
            return new ResponseEntity<>(parcoursDao.findBySpecialite(
                    specialiteDao.findById(specialiteRequest.getIdspecialite()).get())
                    , HttpStatus.OK);
        else
            return new ResponseEntity<>(new MessageResponse("Ressources indisponibles", 403)
                    , HttpStatus.FORBIDDEN);
    }
}
