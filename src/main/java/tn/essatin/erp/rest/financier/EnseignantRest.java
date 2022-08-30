package tn.essatin.erp.rest.financier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.PersonneDao;
import tn.essatin.erp.dao.financier.EnseignantDao;
import tn.essatin.erp.payload.request.scolarite.EnseignantRequest;
import tn.essatin.erp.payload.response.MessageResponse;
import tn.essatin.erp.util.ApiInfo;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/enseignant/")
public class EnseignantRest {
    final EnseignantDao enseignantDao;
    final PersonneDao personneDao;
    final MessageSource messageSource;

    @Autowired
    public EnseignantRest(EnseignantDao enseignantDao, PersonneDao personneDao, MessageSource messageSource) {
        this.enseignantDao = enseignantDao;
        this.personneDao = personneDao;
        this.messageSource = messageSource;
    }

    @GetMapping("/")
    public ResponseEntity<?> infoApi() {
        List<ApiInfo> infos = new ArrayList<>();
        List<MessageResponse> responses;
        ApiInfo info;
        /////////////////////
        responses = new ArrayList<>();
        info = new ApiInfo("/api/enseignant/getallenseignant", "Get",
                "retourne un JSON avec la liste de tout les enseignant dans la base",
                "/api/enseignant/getall",
                "JSON text Message", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.employer", null, Locale.FRENCH), 403));
        info = new ApiInfo("/api/enseignant/getenseingant/{id}", "Get",
                "retourne un JSON avec la liste des enseignant par leur id",
                "/api/enseignant/getenseingant/7",
                "JSON text Message", responses);
        infos.add(info);
        /////////////////////
        return new ResponseEntity<>(infos, HttpStatus.OK);

    }

    @GetMapping("/getallenseignant")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(enseignantDao.findAll(), HttpStatus.OK);
    }

    @PostMapping("/getenseingant")
    public ResponseEntity<?> getEnseingantById(@Valid @RequestBody EnseignantRequest enseignantRequest) {
        if (enseignantDao.findById(enseignantRequest.getId()).isPresent()) {
            return new ResponseEntity<>(
                    enseignantDao.findById(enseignantRequest.getId()).get()
                    , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Ressource Indisponible", 403)
                    , HttpStatus.FORBIDDEN);
        }
    }


}
