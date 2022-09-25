package tn.essatin.erp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.CompteDao;
import tn.essatin.erp.dao.RoleDao;
import tn.essatin.erp.model.Compte;
import tn.essatin.erp.model.Role;
import tn.essatin.erp.payload.request.GetByIdCompteRoleRequest;
import tn.essatin.erp.payload.request.GetByIdRequest;
import tn.essatin.erp.payload.request.RoleRequest;
import tn.essatin.erp.payload.response.MessageResponse;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/role/")
public class RoleRest {
    final CompteDao compteDao;
    final RoleDao roleDao;
    @Autowired
    public RoleRest(CompteDao compteDao, RoleDao roleDao) {
        this.compteDao = compteDao;
        this.roleDao = roleDao;
    }

    @GetMapping("/getallroles" +
            "")
    public ResponseEntity<?> getAllRoles() {
        return new ResponseEntity<>(roleDao.findAll(), HttpStatus.OK);

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


    @PostMapping("/supprimerole")
    public ResponseEntity<?> supprimerole(@Valid @RequestBody GetByIdCompteRoleRequest getByIdCompteRoleRequest) {
        if (compteDao.findById(getByIdCompteRoleRequest.getIdCompte()).isPresent()) {
            Compte c = compteDao.findById(getByIdCompteRoleRequest.getIdCompte()).get();
            Set<Role> rl;
            rl = c.getRoles();
            System.out.println(rl);


            for (Role r : rl) {


                System.out.println(r.getId());
                System.out.println(getByIdCompteRoleRequest.getIdRole());
                if (r.getId() == getByIdCompteRoleRequest.getIdRole()) {
                    rl.remove(r);
                    System.out.println(rl.remove(r.getId()));
                    c.setRoles(rl);
                    System.out.println(c.getRoles());
                    compteDao.save(c);
                   break;

                }
            }


            return new ResponseEntity<>(
                    new MessageResponse("Role supprimé avec succée", 403), HttpStatus.FORBIDDEN);
        }
        else
            return new ResponseEntity<>(
                    new MessageResponse("Ressources indisponibles", 403), HttpStatus.FORBIDDEN);
    }
}
