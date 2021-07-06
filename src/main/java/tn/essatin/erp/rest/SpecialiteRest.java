package tn.essatin.erp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.payload.request.CycleRequest;
import tn.essatin.erp.dao.CycleDao;
import tn.essatin.erp.dao.SpecialiteDao;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/specialite/")
public class SpecialiteRest {
    @Autowired
    SpecialiteDao specialiteDao;
    @Autowired
    CycleDao cycleDao;

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<List>(specialiteDao.findAll(), HttpStatus.OK);
    }
    @PostMapping("/getbyid")
    public ResponseEntity<?> getById(@Valid @RequestBody CycleRequest cycleRequest){
        return new ResponseEntity<List>(specialiteDao.findAllByCycle(
                cycleDao.findById(cycleRequest.getIdcycle()).get())
                , HttpStatus.OK);
    }
}
