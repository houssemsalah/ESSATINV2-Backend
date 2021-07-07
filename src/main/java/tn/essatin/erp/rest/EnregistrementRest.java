package tn.essatin.erp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.model.Enregistrement;
import tn.essatin.erp.model.Etudiants;
import tn.essatin.erp.payload.request.NivSessRequest;
import tn.essatin.erp.dao.EnregistrementDao;
import tn.essatin.erp.dao.NiveauDao;
import tn.essatin.erp.dao.SessionDao;
import tn.essatin.erp.payload.request.SessionUnivRequest;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/enregistrement/")
public class EnregistrementRest {
    @Autowired
    EnregistrementDao enregistrementDao;
    @Autowired
    SessionDao sessionDao;
    @Autowired
    NiveauDao niveauDao;

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){

        return new ResponseEntity<List>(enregistrementDao.findAll(), HttpStatus.OK);
    }
    @PostMapping("/getbyidns")
    public ResponseEntity<?> getById(@Valid @RequestBody NivSessRequest nivSessRequest){
        return new ResponseEntity<List>(enregistrementDao.findByIdNiveauAndAndIdSession(
                niveauDao.findById(nivSessRequest.getIdNiveaux()).get(),
                sessionDao.findById(nivSessRequest.getIdSession()).get())
                , HttpStatus.OK);
    }

    @PostMapping("/getenregistrementbysession")
    public ResponseEntity<?> getEnregistrementBySession(@Valid @RequestBody SessionUnivRequest sessionUnivRequest){
        List<Enregistrement> listeEnregistrement = new ArrayList<Enregistrement>();
        return new ResponseEntity<List>(enregistrementDao.findByIdSession(
                sessionDao.findById(sessionUnivRequest.getIdSession()).get()),HttpStatus.OK
        );
    }
}