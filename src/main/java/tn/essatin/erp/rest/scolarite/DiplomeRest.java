package tn.essatin.erp.rest.scolarite;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.essatin.erp.dao.scolarite.DiplomeDao;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/diplome/")
public class DiplomeRest {
    final DiplomeDao diplomeDao;

    @Autowired
    public DiplomeRest(DiplomeDao diplomeDao) {
        this.diplomeDao = diplomeDao;
    }

    @GetMapping("getall")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(diplomeDao.findAll(), HttpStatus.OK);
    }
}
