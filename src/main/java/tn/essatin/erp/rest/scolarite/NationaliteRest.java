package tn.essatin.erp.rest.scolarite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.essatin.erp.dao.NationaliteDao;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/nationalite/")

public class NationaliteRest {
    final NationaliteDao nationaliteDao;

    @Autowired
    public NationaliteRest(NationaliteDao nationaliteDao) {
        this.nationaliteDao = nationaliteDao;
    }


    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {

        return new ResponseEntity<>(nationaliteDao.findAll(), HttpStatus.OK);
    }
}
