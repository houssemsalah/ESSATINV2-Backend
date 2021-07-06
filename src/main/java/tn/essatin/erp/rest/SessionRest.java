package tn.essatin.erp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.essatin.erp.dao.SessionDao;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/session/")
public class SessionRest {
    @Autowired
    SessionDao sessionDao;
    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<List>(sessionDao.findAll(), HttpStatus.OK);
    }
}
