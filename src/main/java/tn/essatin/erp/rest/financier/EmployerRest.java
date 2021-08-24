package tn.essatin.erp.rest.financier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.NationaliteDao;
import tn.essatin.erp.dao.PersonneDao;
import tn.essatin.erp.dao.TypeIdentificateurDao;
import tn.essatin.erp.dao.financier.ContratDao;
import tn.essatin.erp.dao.financier.EmployerDao;
import tn.essatin.erp.model.Nationalite;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.TypeIdentificateur;
import tn.essatin.erp.model.financier.*;
import tn.essatin.erp.payload.request.financier.AjouterEmployerRequest;
import tn.essatin.erp.payload.request.financier.InscriptionEmployerByIdPersonneRequest;
import tn.essatin.erp.payload.request.financier.InscriptionEmployerRequest;
import tn.essatin.erp.payload.request.financier.ModifierEmployerRequest;
import tn.essatin.erp.payload.response.MessageResponse;
import tn.essatin.erp.util.ApiInfo;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/employer/")
public class EmployerRest {
    final EmployerDao employerDao;
    final PersonneDao personneDao;
    final TypeIdentificateurDao typeIdentificateurDao;
    final NationaliteDao nationaliteDao;
    final ContratDao contratDao;
    final MessageSource messageSource;


    @Autowired
    public EmployerRest(EmployerDao employerDao, PersonneDao personneDao,
                        TypeIdentificateurDao typeIdentificateurDao, NationaliteDao nationaliteDao,
                        ContratDao contratDao, MessageSource messageSource) {
        this.employerDao = employerDao;
        this.personneDao = personneDao;
        this.typeIdentificateurDao = typeIdentificateurDao;
        this.nationaliteDao = nationaliteDao;
        this.contratDao = contratDao;
        this.messageSource = messageSource;
    }
    @GetMapping("/")
    public ResponseEntity<?> infoApi() {
        List<ApiInfo> infos = new ArrayList<>();
        List<MessageResponse> responses;
        ApiInfo info;
        /////////////////////
        responses = new ArrayList<>();
        info = new ApiInfo("/api/employer/getall", "Get",
                "retourne un JSON avec la liste de tout les employer dans la base",
                "/api/employer/getall",
                "une texte JSON avec une liste des employer", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.employer", null, Locale.FRENCH), 403));
        info = new ApiInfo("/api/employer/getemployer/{id}", "Get",
                "retourne un JSON avec la liste des employer par leur id",
                "/api/employer/getemployer/3",
                "une texte JSON avec une liste des employer", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.personne", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.incorrect.situationMarietal", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.incorrect.typeEmployer", null, Locale.FRENCH), 403));

        AjouterEmployerRequest ajouterEmployerRequest = new AjouterEmployerRequest("126748596",
                "y3adi ro7ah", ESituationMaritale.DIVORCEE, 3, "", LocalDate.now(),
                "785412054785", "ossteth ad edonya", 401,  ETypeEmployer.ENSEIGNANT);
        info = new ApiInfo("/api/employer/ajouteremployer", "Post","ajouter un employer", ajouterEmployerRequest,
                "une texte JSON aves des champs pour ajouter un employer", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.employer", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.personne", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.incorrect.typeEmployer", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.uniteSalaire", null, Locale.FRENCH), 403));

        ModifierEmployerRequest modifierEmployerRequest=new ModifierEmployerRequest(
                1,"123456789",
                "Fallou7",ESituationMaritale.CELIBATAIRE,
                5,"", LocalDate.now(),"12345678912345",
                "Service Juridique",555,ETypeEmployer.ADMINISTRATIF);

        info = new ApiInfo("/api/employer/modifieremployer", "Post",
                "Modifier un employer",
                modifierEmployerRequest,
                "une texte JSON aves des champs pour modifier un employer", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();

         responses.add(new MessageResponse("TypeIdentificateur invalide", 403));
        responses.add(new MessageResponse("Nationnalité invalide", 403));
         responses.add(new MessageResponse("Cette personne existe deja dans la base de donnee", 403));
        responses.add(new MessageResponse("Cet employer est deja présent dans la base", 403));

        InscriptionEmployerRequest inscriptionEmployerRequest = new InscriptionEmployerRequest("Eladel", "Nahla",
                "nahla@essat-gabes.tn",
                "Kebili","96747848", LocalDate.now().minusYears(20),
                "Kebili", 1 ,"0365498", "femme",1,
                "8754123698","5adem jdid", ESituationMaritale.CELIBATAIRE,
                0,"", LocalDate.now(), "7854789632145014","bch t9ari", ETypeEmployer.ENSEIGNANT,
                ETypeContrat.CDI, EUniteSalaire.MOIS, 900.0, LocalDate.now(), null, LocalDate.now(),
                null, "7ata chay");

        info = new ApiInfo("/api/employer/inscription", "Post",
                "inscription d'un employer et un personne meme temps",
                inscriptionEmployerRequest,
                "une texte JSON pour insecrire un employer", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();

        responses.add(new MessageResponse("Personne introuvable", 403));
        responses.add(new MessageResponse("Cet employer est deja présent dans la base", 403));


        InscriptionEmployerByIdPersonneRequest inscriptionEmployerByIdPersonneRequest = new InscriptionEmployerByIdPersonneRequest(80,
                "7854698745", "bch yabda ye5dem",ESituationMaritale.CELIBATAIRE,
                0,"",LocalDate.now(),"785478547895","yched blaset amal",
                ETypeEmployer.ADMINISTRATIF,ETypeContrat.CDI, EUniteSalaire.MOIS,500.0, LocalDate.now(),
                null,LocalDate.now(),null ,"je sais pas");

        info = new ApiInfo("/api/employer/inscriptionbyidpersonne", "Post",
                "inscription d'un employer par id personne",
                inscriptionEmployerByIdPersonneRequest,
                "une texte JSON pour insecrire un employer", responses);
        infos.add(info);
        /////////////////////

        return new ResponseEntity<>(infos, HttpStatus.OK);

    }

    @GetMapping("/getallEmployer")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(employerDao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getemployer/{id}")
    public ResponseEntity<?> getEmployerById(@PathVariable int id) {
        Optional<Employer> employer = employerDao.findById(id);
        if (employer.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Employer introuvable"), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(employer.get(), HttpStatus.OK);
    }

    @PostMapping("/ajouteremployer")
    public ResponseEntity<?> ajouterEmployer(@Valid @RequestBody AjouterEmployerRequest ajouterEmployerRequest) {

        Optional<Personne> personne = personneDao.findById(ajouterEmployerRequest.getIdpersonne());
        if (personne.isEmpty())
            return new ResponseEntity<>(new MessageResponse("Personne introuvable", 403), HttpStatus.FORBIDDEN);


        Employer employer = new Employer(ajouterEmployerRequest.getNumeroCNSS(),
                ajouterEmployerRequest.getObservation(),
                ajouterEmployerRequest.getSituationMaritale(),
                ajouterEmployerRequest.getNbEnfant(),
                ajouterEmployerRequest.getImage(),
                ajouterEmployerRequest.getDateEntree(),
                ajouterEmployerRequest.getRipIBAN(),
                ajouterEmployerRequest.getPoste(),
                personne.get(),
                ajouterEmployerRequest.getTypeEmployer());
        employerDao.save(employer);
        return new ResponseEntity<>(new MessageResponse(
                "L'employer " + employer.getPersonne().getIdPersonne()
                        + " " + employer.getPersonne().getNom()
                        + "a été ajouter avec succes",
                200), HttpStatus.OK);
    }

    @PostMapping("/modifieremployer")
    public ResponseEntity<?> modifierEmployer(@Valid @RequestBody ModifierEmployerRequest modifierEmployerRequest) {

        Optional<Employer> oemployer = employerDao.findById(modifierEmployerRequest.getId());
        if (oemployer.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Employer Introuvable", 403), HttpStatus.FORBIDDEN);
        }
        Optional<Personne> personne = personneDao.findById(modifierEmployerRequest.getIdPersonne());
        if (personne.isEmpty())
            return new ResponseEntity<>(new MessageResponse("Personne introuvable", 403), HttpStatus.FORBIDDEN);


        Employer employer = oemployer.get();
        employer.setNumeroCNSS(modifierEmployerRequest.getNumeroCNSS());
        employer.setObservation(modifierEmployerRequest.getObservation());
        employer.setSituationMaritale(modifierEmployerRequest.getSituationMaritale());
        employer.setNbEnfant(modifierEmployerRequest.getNbEnfant());
        employer.setImage(modifierEmployerRequest.getImage());
        employer.setDateEntree(modifierEmployerRequest.getDateEntree());
        employer.setRipIBAN(modifierEmployerRequest.getRipIBAN());
        employer.setPoste(modifierEmployerRequest.getPoste());
        employer.setPersonne(personne.get());
        employer.setTypeEmployer(modifierEmployerRequest.getTypeEmployer());
        employerDao.save(employer);
        return new ResponseEntity<>(new MessageResponse(
                "L'employer " + employer.getPersonne().getIdPersonne()
                        + " " + employer.getPersonne().getNom()
                        + "a été Modifier avec succes",
                200), HttpStatus.OK);
    }

    @PostMapping("/inscription")
    public ResponseEntity<?> inscriptionEmployer(@Valid @RequestBody InscriptionEmployerRequest inscriptionEmployerRequest) {

        Optional<TypeIdentificateur> typeIdentificateur = typeIdentificateurDao.findById(inscriptionEmployerRequest.getIdTypeIdentificateur());
        if (typeIdentificateur.isEmpty())
            return new ResponseEntity<>(new MessageResponse("TypeIdentificateur invalide", 403), HttpStatus.FORBIDDEN);
        Optional<Nationalite> nationalite = nationaliteDao.findById(inscriptionEmployerRequest.getIdNationalite());
        if (nationalite.isEmpty())
            return new ResponseEntity<>(new MessageResponse("Nationnalité invalide", 403), HttpStatus.FORBIDDEN);


        Optional<Personne> p = personneDao.findByNumeroIdentificateur(inscriptionEmployerRequest.getIdidentif());
        if (p.isPresent()) {
            return new ResponseEntity<>(new MessageResponse("Cette personne existe deja dans la base de donnee", 403), HttpStatus.FORBIDDEN);
        }
        Optional<Employer> e = employerDao.findByNumeroCNSS(inscriptionEmployerRequest.getNumeroCNSS());
        if (e.isPresent()) {
            return new ResponseEntity<>(new MessageResponse("Cet employer est deja présent dans la base", 403), HttpStatus.FORBIDDEN);
        }

        Personne personne = new Personne(inscriptionEmployerRequest.getNom(), inscriptionEmployerRequest.getPrenom(),
                inscriptionEmployerRequest.getMail(), inscriptionEmployerRequest.getAdresse(),
                inscriptionEmployerRequest.getTelephonne(), inscriptionEmployerRequest.getDateNaissance(),
                inscriptionEmployerRequest.getLieuNaissance(), typeIdentificateur.get(),
                inscriptionEmployerRequest.getIdidentif(), inscriptionEmployerRequest.getSexe(),
                nationalite.get()
        );
        personneDao.save(personne);
        Employer employer = new Employer(inscriptionEmployerRequest.getNumeroCNSS(),
                inscriptionEmployerRequest.getObservationEmployer(),
                inscriptionEmployerRequest.getSituationMaritale(),
                inscriptionEmployerRequest.getNbEnfant(),
                inscriptionEmployerRequest.getImage(),
                inscriptionEmployerRequest.getDateEntree(),
                inscriptionEmployerRequest.getRipIBAN(),
                inscriptionEmployerRequest.getPoste(),
                personne,
                inscriptionEmployerRequest.getTypeEmployer());
        employerDao.save(employer);
        Contrat contrat = new Contrat(inscriptionEmployerRequest.getTypeContrat(), inscriptionEmployerRequest.getUniteSalaire(),
                inscriptionEmployerRequest.getPrixUnite(), inscriptionEmployerRequest.getDateDebutContrat(),
                inscriptionEmployerRequest.getDateFinContrat(),
                inscriptionEmployerRequest.getDateSignatureContrat(),
                inscriptionEmployerRequest.getDateResiliationContrat(), inscriptionEmployerRequest.getObservationContrat(),
                employer
        );
        contratDao.save(contrat);
        return new ResponseEntity<>(new MessageResponse(
                "L'employer " + employer.getPersonne().getPrenom()
                        + " " + employer.getPersonne().getNom()
                        + " a été Inscrit avec succes",
                200), HttpStatus.OK);
    }


    @PostMapping("/inscriptionbyidpersonne")
    public ResponseEntity<?> inscriptionEmployerByIdPersonne(
            @Valid @RequestBody InscriptionEmployerByIdPersonneRequest inscriptionEmployerByIdPersonneRequest) {

        Optional<Personne> p = personneDao.findById(inscriptionEmployerByIdPersonneRequest.getIdPersonne());
        if (p.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Personne introuvable", 403), HttpStatus.FORBIDDEN);
        }
        Optional<Employer> e = employerDao.findByNumeroCNSS(inscriptionEmployerByIdPersonneRequest.getNumeroCNSS());
        if (e.isPresent()) {
            return new ResponseEntity<>(new MessageResponse("Cet employer est deja présent dans la base", 403), HttpStatus.FORBIDDEN);
        }


        Employer employer = new Employer(inscriptionEmployerByIdPersonneRequest.getNumeroCNSS(),
                inscriptionEmployerByIdPersonneRequest.getObservationEmployer(),
                inscriptionEmployerByIdPersonneRequest.getSituationMaritale(),
                inscriptionEmployerByIdPersonneRequest.getNbEnfant(),
                inscriptionEmployerByIdPersonneRequest.getImage(),
                inscriptionEmployerByIdPersonneRequest.getDateEntree(),
                inscriptionEmployerByIdPersonneRequest.getRipIBAN(),
                inscriptionEmployerByIdPersonneRequest.getPoste(),
                p.get(),
                inscriptionEmployerByIdPersonneRequest.getTypeEmployer());
        employerDao.save(employer);
        Contrat contrat = new Contrat(inscriptionEmployerByIdPersonneRequest.getTypeContrat(), inscriptionEmployerByIdPersonneRequest.getUniteSalaire(),
                inscriptionEmployerByIdPersonneRequest.getPrixUnite(), inscriptionEmployerByIdPersonneRequest.getDateDebutContrat(),
                inscriptionEmployerByIdPersonneRequest.getDateFinContrat(),
                inscriptionEmployerByIdPersonneRequest.getDateSignatureContrat(),
                inscriptionEmployerByIdPersonneRequest.getDateResiliationContrat(), inscriptionEmployerByIdPersonneRequest.getObservationContrat(),
                employer
        );
        contratDao.save(contrat);
        return new ResponseEntity<>(new MessageResponse(
                "L'employer " + employer.getPersonne().getPrenom()
                        + " " + employer.getPersonne().getNom()
                        + " a été Inscrit avec succes",
                200), HttpStatus.OK);
    }


}
