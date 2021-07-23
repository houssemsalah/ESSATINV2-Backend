package tn.essatin.erp.rest.scolarite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.scolarite.CycleDao;
import tn.essatin.erp.dao.scolarite.SpecialiteDao;
import tn.essatin.erp.payload.request.scolarite.CycleRequest;
import tn.essatin.erp.payload.response.MessageResponse;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/specialite/")
public class SpecialiteRest {
    final SpecialiteDao specialiteDao;
    final CycleDao cycleDao;

    @Autowired
    public SpecialiteRest(SpecialiteDao specialiteDao, CycleDao cycleDao) {
        this.specialiteDao = specialiteDao;
        this.cycleDao = cycleDao;
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(specialiteDao.findAll(), HttpStatus.OK);
    }

    @PostMapping("/getbyid")
    public ResponseEntity<?> getById(@Valid @RequestBody CycleRequest cycleRequest) {
        if (cycleDao.findById(cycleRequest.getIdcycle()).isPresent())
            return new ResponseEntity<>(specialiteDao.findAllByCycle(
                    cycleDao.findById(cycleRequest.getIdcycle()).get())
                    , HttpStatus.OK);
        else
            return new ResponseEntity<>(
                    new MessageResponse("Ressources indisponibles", 403), HttpStatus.FORBIDDEN);
    }
}
