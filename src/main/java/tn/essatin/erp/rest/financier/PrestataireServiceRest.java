package tn.essatin.erp.rest.financier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.financier.PrestataireServiceDao;
import tn.essatin.erp.model.financier.PrestataireService;
import tn.essatin.erp.payload.response.MessageResponse;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/prestataireservice/")
public class PrestataireServiceRest {
    final PrestataireServiceDao prestataireServiceDao;
    @Autowired
    public PrestataireServiceRest(PrestataireServiceDao prestataireServiceDao) {
        this.prestataireServiceDao = prestataireServiceDao;
    }
    @GetMapping("/getallprestataireservice")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(prestataireServiceDao.findAll(), HttpStatus.OK);
    }
    @PostMapping("getprestataireservice/{id}")
    public  ResponseEntity<?> getPrestataireById(@PathVariable int id){
        Optional<PrestataireService> prestataireService = prestataireServiceDao.findById(id);
        if (prestataireService.isEmpty()){
            return new ResponseEntity<>(new MessageResponse("prestataire de service introuvable"), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(prestataireService.get(), HttpStatus.OK);
    }



}
