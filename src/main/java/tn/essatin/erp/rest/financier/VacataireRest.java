package tn.essatin.erp.rest.financier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.PersonneDao;
import tn.essatin.erp.dao.financier.VacataireDao;
import tn.essatin.erp.model.financier.Vacataire;
import tn.essatin.erp.payload.response.MessageResponse;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/vacataire/")
public class VacataireRest {
    final VacataireDao vacataireDao;
    final PersonneDao personneDao;
    @Autowired
    public VacataireRest(VacataireDao vacataireDao, PersonneDao personneDao) {
        this.vacataireDao = vacataireDao;
        this.personneDao = personneDao;
    }
    @GetMapping("/getallvacataire")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(vacataireDao.findAll(), HttpStatus.OK);
    }
    @PostMapping("getvacataire/{id}")
    public  ResponseEntity<?> getVacataireById(@PathVariable int id){
        Optional<Vacataire> vacataire = vacataireDao.findById(id);
        if (vacataire.isEmpty()){
            return new ResponseEntity<>(new MessageResponse("Vacataire introuvable"), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(vacataire.get(), HttpStatus.OK);
    }

}
