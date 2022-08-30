package tn.essatin.erp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.essatin.erp.dao.SessionDao;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/session/")
public class SessionRest {
    final SessionDao sessionDao;

    @Autowired
    public SessionRest(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(sessionDao.findAll(), HttpStatus.OK);
    }
}