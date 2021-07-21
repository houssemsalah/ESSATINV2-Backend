package tn.essatin.erp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.CompteDao;
import tn.essatin.erp.model.Compte;
import tn.essatin.erp.payload.request.RoleRequest;
import tn.essatin.erp.payload.response.MessageResponse;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/role/")
public class RoleRest {
    final CompteDao compteDao;

    @Autowired
    public RoleRest(CompteDao compteDao) {
        this.compteDao = compteDao;
    }

    @PostMapping("/getrolesbyidcompte")
    public ResponseEntity<?> getrolesByIdCompte(@Valid @RequestBody RoleRequest roleRequest) {
        if (compteDao.findById(roleRequest.getIdCompte()).isPresent()) {
            Compte c = compteDao.findById(roleRequest.getIdCompte()).get();
            return new ResponseEntity<>(c.getRoles(), HttpStatus.OK);
        } else
            return new ResponseEntity<>(
                    new MessageResponse("Ressources indisponibles", 403), HttpStatus.FORBIDDEN);
    }
}
