package tn.essatin.erp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.CompteDao;
import tn.essatin.erp.model.Compte;
import tn.essatin.erp.payload.request.RoleRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/role/")
public class RoleRest {
    @Autowired
    CompteDao compteDao;

    @PostMapping("/getrolesbyidcompte")
    public ResponseEntity<?> getrolesByIdCompte(@Valid @RequestBody RoleRequest roleRequest){
        Compte c = compteDao.findById(roleRequest.getIdCompte()).get();
        return new ResponseEntity<Set>(c.getRoles(), HttpStatus.OK);
    }
}
