package tn.essatin.erp.rest.financier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.PersonneDao;
import tn.essatin.erp.dao.financier.ContratDao;
import tn.essatin.erp.dao.financier.EmployerDao;
import tn.essatin.erp.model.financier.Contrat;
import tn.essatin.erp.model.financier.ETypeContrat;
import tn.essatin.erp.model.financier.EUniteSalaire;
import tn.essatin.erp.model.financier.Employer;
import tn.essatin.erp.payload.request.financier.ContratRequest;
import tn.essatin.erp.payload.request.financier.ContratUpdateRequest;
import tn.essatin.erp.payload.response.MessageResponse;
import tn.essatin.erp.util.ApiInfo;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/contrat/")
public class ContratRest {
    final ContratDao contratDao;
    final EmployerDao employerDao;
    final PersonneDao personneDao;

    public ContratRest(ContratDao contratDao, EmployerDao employerDao,PersonneDao personneDao) {
        this.contratDao = contratDao;
        this.employerDao = employerDao;
        this.personneDao=personneDao;
    }
    @GetMapping("/")
    public ResponseEntity<?> infoApi() {
        List<ApiInfo> infos = new ArrayList<>();
        List<MessageResponse> responses;
        ApiInfo info;
        /////////////////////
        responses = new ArrayList<>();
        info = new ApiInfo("/api/contrat/getall", "Get",
                "retourne un JSON avec la liste de tout les contrats dans la base",
                "/api/contrat/getall",
                "une texte JSON avec une liste de Contrats", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse("Contrat introuvable!", 403));
        info = new ApiInfo("/api/getbyid/{id}", "Get",
                "retourne un JSON avec la liste de tout les contrat par id",
                "/api/contrat/getbyid/2",
                "une texte JSON avec une liste de Contrat", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse("Employer Introuvable!", 403));
        responses.add(new MessageResponse("ETypeContrat Introuvable!", 403));
        responses.add(new MessageResponse("EUniteSalaire Introuvable!", 403));


        info = new ApiInfo("/api/contrat/addcontrat", "Post",
                "Ajouter un contrat",
                "/api/contrat/addcontrat",
                "une texte JSON aves des champs pour ajouter un contrat", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse("Employer Introuvable!", 403));
        responses.add(new MessageResponse("ETypeContrat Introuvable!", 403));
        responses.add(new MessageResponse("EUniteSalaire Introuvable!", 403));


        info = new ApiInfo("/api/contrat/modifiercontrat", "Post",
                "Modifier un contrat",
                "/api/contrat/modifiercontrat",
                "une texte JSON aves des champs pour modifier un contrat", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse("Employer Introuvable!", 403));
        responses.add(new MessageResponse("cette Employer n'a aucun contrat!", 403));


        info = new ApiInfo("/api/contrat/getbyemployer/{id}", "Get",
                "retourne un JSON avec la liste de tout les contrat par id d'employer",
                "/api/contrat/getbyemployer/2",
                "une texte JSON aves une liste de contart", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse("Employer Introuvable!", 403));
        responses.add(new MessageResponse("cette Employer n'a aucun contrat!", 403));


        info = new ApiInfo("/api/contrat/getlastcontratbyemployer/{id}", "Get",
                "retourne un JSON avec la dernier contrat d'un employer",
                "/api/contrat/getlastcontratbyemployer/2",
                "une texte JSON aves une liste de contart", responses);
        infos.add(info);
        /////////////////////

        return new ResponseEntity<>(infos, HttpStatus.OK);

    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(contratDao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getAll(@PathVariable int id) {
        Optional<Contrat> contrat = contratDao.findById(id);
        if (contrat.isEmpty()){
            return new ResponseEntity<>(new MessageResponse("Contrat introuvable",403),HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(contrat.get(), HttpStatus.OK);
    }

    @PostMapping("/addcontrat")
    public ResponseEntity<?> addContrat(@Valid @RequestBody ContratRequest contratRequest) {

        Optional<Employer> employer = employerDao.findById(contratRequest.getIdEmployer());
        if (employer.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Employer introuvable", 403), HttpStatus.FORBIDDEN);
        }
        ETypeContrat type;
        EUniteSalaire unite;
        try {
             type = ETypeContrat.valueOf(contratRequest.getTypeContrat());
        } catch (Exception E) {
            return new ResponseEntity<>(
                    new MessageResponse("ETypeContrat introuvable", 403), HttpStatus.FORBIDDEN);
        }
        try{
        unite = EUniteSalaire.valueOf(contratRequest.getUniteSalaire());
        } catch (Exception E) {
            return new ResponseEntity<>(
                    new MessageResponse("EUniteSalaire introuvable", 403), HttpStatus.FORBIDDEN);
        }
        Contrat contrat = new Contrat(type, unite,
                contratRequest.getPrixUnite(), contratRequest.getDateDebutContrat(),
                contratRequest.getDateFinContrat(),
                contratRequest.getDateSignatureContrat(),
                contratRequest.getDateResiliationContrat(), contratRequest.getObservation(),
                employer.get()
        );
        contratDao.save(contrat);
        return new ResponseEntity<>(
                new MessageResponse("Contrat de "
                        + employer.get().getPersonne().getPrenom() + " "
                        + employer.get().getPersonne().getNom()
                        + " Ajouter avec Success",
                        200), HttpStatus.OK);
    }
    @PostMapping("/modifiercontrat")
    public ResponseEntity<?> updateContrat(@Valid @RequestBody ContratUpdateRequest contratUpdateRequest) {
        Optional<Contrat> optContrat = contratDao.findById(contratUpdateRequest.getId());
        if (optContrat.isEmpty()){
            return new ResponseEntity<>(
                    new MessageResponse("Contrat introuvable", 403), HttpStatus.FORBIDDEN);
        }
        Contrat contrat = optContrat.get();

        ETypeContrat type;
        EUniteSalaire unite;
        try {
            type = ETypeContrat.valueOf(contratUpdateRequest.getTypeContrat());
        } catch (Exception E) {
            return new ResponseEntity<>(
                    new MessageResponse("ETypeContrat introuvable", 403), HttpStatus.FORBIDDEN);
        }
        try{
            unite = EUniteSalaire.valueOf(contratUpdateRequest.getUniteSalaire());
        } catch (Exception E) {
            return new ResponseEntity<>(
                    new MessageResponse("EUniteSalaire introuvable", 403), HttpStatus.FORBIDDEN);
        }
        contrat.setTypeContrat(type) ;
        contrat.setUniteSalaire(unite) ;
        contrat.setPrixUnite(contratUpdateRequest.getPrixUnite()); ;
        contrat.setDateDebutContrat(contratUpdateRequest.getDateDebutContrat()) ;
        contrat.setDateFinContrat(contratUpdateRequest.getDateFinContrat()) ;
        contrat.setDateSignatureContrat(contratUpdateRequest.getDateSignatureContrat()) ;
        contrat.setDateResiliationContrat(contratUpdateRequest.getDateResiliationContrat()) ;
        contrat.setObservation(contratUpdateRequest.getObservation()) ;
        contratDao.save(contrat);
        return new ResponseEntity<>(
                new MessageResponse("Contrat de "
                        + contrat.getEmployer().getPersonne().getPrenom() + " "
                        + contrat.getEmployer().getPersonne().getNom()
                        + " Mise A jours avec Success",
                        200), HttpStatus.OK);

    }
    @GetMapping("/getbyemployer/{id}")
    public ResponseEntity<?> getByEmployer(@PathVariable int id){
        Optional<Employer> employer = employerDao.findById(id);
        if (employer.isEmpty()){
            return new ResponseEntity<>(new MessageResponse("Employer introuvable", 403 ), HttpStatus.FORBIDDEN);
        }

        List<Contrat> contrats = contratDao.findContratByEmployer(employer.get());
        if (contrats.isEmpty()){
            return new ResponseEntity<>(new MessageResponse("cette Employer n'a aucun contrat", 403), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(contrats, HttpStatus.OK);
    }

    @GetMapping("/getlastcontratbyemployer/{id}")
    public ResponseEntity<?> getLastContratByEmployer(@PathVariable int id){
        Optional<Employer> employer = employerDao.findById(id);
        if (employer.isEmpty()){
            return new ResponseEntity<>(new MessageResponse("Employer introuvable", 403), HttpStatus.FORBIDDEN);
        }
        Optional<Contrat> contrat = contratDao.findTopByEmployerOrderByIdDesc(employer.get());
        if (contrat.isEmpty()){
            return new ResponseEntity<>(new MessageResponse("cette Employer n'a aucun contrat", 403), HttpStatus.FORBIDDEN);

        }
        return new ResponseEntity<>(contrat.get(), HttpStatus.OK);
    }
}


