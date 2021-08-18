package tn.essatin.erp.rest.financier;

import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    public EmployerRest(EmployerDao employerDao, PersonneDao personneDao,
                        TypeIdentificateurDao typeIdentificateurDao, NationaliteDao nationaliteDao, ContratDao contratDao) {
        this.employerDao = employerDao;
        this.personneDao = personneDao;
        this.typeIdentificateurDao = typeIdentificateurDao;
        this.nationaliteDao = nationaliteDao;
        this.contratDao = contratDao;
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
        responses.add(new MessageResponse("Employer introuvable!", 403));
        info = new ApiInfo("/api/employer/getemployer/{id}", "Get",
                "retourne un JSON avec la liste des employer par leur id",
                "/api/employer/getemployer/3",
                "une texte JSON avec une liste des employer", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse("Personne Introuvable!", 403));
        responses.add(new MessageResponse("Situation marietal incorecte", 403));
        responses.add(new MessageResponse("Type Employer incorecte", 403));


        info = new ApiInfo("/api/employer/ajouteremployer", "Post",
                "Ajouter un empoyer",
                "/api/employer/ajouteremployer",
                "une texte JSON aves des champs pour ajouter un employer", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse("Employer Introuvable!", 403));
        responses.add(new MessageResponse("Personne Introuvable!", 403));
        responses.add(new MessageResponse("ETypeContrat Introuvable!", 403));
        responses.add(new MessageResponse("EUniteSalaire Introuvable!", 403));


        info = new ApiInfo("/api/employer/modifieremployer", "Post",
                "Modifier un employer",
                "/api/employer/modifieremployer",
                "une texte JSON aves des champs pour modifier un employer", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse("Employer Introuvable!", 403));
        responses.add(new MessageResponse("cette Employer n'a aucun contrat!", 403));


        info = new ApiInfo("/api/employer/inscription", "Get",
                "retourne un JSON avec la liste de tout les contrat par id d'employer",
                "/api/contrat/getbyemployer/2",
                "une texte JSON aves une liste de contart", responses);
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
        ESituationMaritale situationMaritale;
        ETypeEmployer typeEmployer;
        Optional<Personne> personne = personneDao.findById(ajouterEmployerRequest.getIdpersonne());
        if (personne.isEmpty())
            return new ResponseEntity<>(new MessageResponse("Personne introuvable", 403), HttpStatus.FORBIDDEN);
        try {
            situationMaritale = ESituationMaritale.valueOf(ajouterEmployerRequest.getSituationMaritale());
        } catch (Exception E) {
            return new ResponseEntity<>(new MessageResponse("Situation marietal incorecte", 403), HttpStatus.FORBIDDEN);
        }
        try {
            typeEmployer = ETypeEmployer.valueOf(ajouterEmployerRequest.getTypeEmployer());
        } catch (Exception E) {
            return new ResponseEntity<>(new MessageResponse("Type Employer incorecte",403), HttpStatus.FORBIDDEN);
        }
        Employer employer = new Employer(ajouterEmployerRequest.getNumeroCNSS(),
                ajouterEmployerRequest.getObservation(),
                situationMaritale,
                ajouterEmployerRequest.getNbEnfant(),
                ajouterEmployerRequest.getImage(),
                ajouterEmployerRequest.getDateEntree(),
                ajouterEmployerRequest.getRipIBAN(),
                ajouterEmployerRequest.getPoste(),
                personne.get(),
                typeEmployer);
        employerDao.save(employer);
        return new ResponseEntity<>(new MessageResponse(
                "L'employer " + employer.getPersonne().getIdPersonne()
                        + " " + employer.getPersonne().getNom()
                        + "a été ajouter avec succes",
                200), HttpStatus.OK);
    }

    @PostMapping("/modifieremployer")
    public ResponseEntity<?> modifierEmployer(@Valid @RequestBody ModifierEmployerRequest modifierEmployerRequest) {
        ESituationMaritale situationMaritale;
        ETypeEmployer typeEmployer;
        Optional<Employer> oemployer = employerDao.findById(modifierEmployerRequest.getId());
        if (oemployer.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Employer Introuvable", 403), HttpStatus.FORBIDDEN);
        }
        Optional<Personne> personne = personneDao.findById(modifierEmployerRequest.getIdPersonne());
        if (personne.isEmpty())
            return new ResponseEntity<>(new MessageResponse("Personne introuvable", 403), HttpStatus.FORBIDDEN);
        try {
            situationMaritale = ESituationMaritale.valueOf(modifierEmployerRequest.getSituationMaritale());
        } catch (Exception E) {
            return new ResponseEntity<>(new MessageResponse("Situation marietal incorecte", 403), HttpStatus.FORBIDDEN);
        }
        try {
            typeEmployer = ETypeEmployer.valueOf(modifierEmployerRequest.getTypeEmployer());
        } catch (Exception E) {
            return new ResponseEntity<>(new MessageResponse("Type Employer incorecte", 403), HttpStatus.FORBIDDEN);
        }
        Employer employer = oemployer.get();
        employer.setNumeroCNSS(modifierEmployerRequest.getNumeroCNSS());
        employer.setObservation(modifierEmployerRequest.getObservation());
        employer.setSituationMaritale(situationMaritale);
        employer.setNbEnfant(modifierEmployerRequest.getNbEnfant());
        employer.setImage(modifierEmployerRequest.getImage());
        employer.setDateEntree(modifierEmployerRequest.getDateEntree());
        employer.setRipIBAN(modifierEmployerRequest.getRipIBAN());
        employer.setPoste(modifierEmployerRequest.getPoste());
        employer.setPersonne(personne.get());
        employer.setTypeEmployer(typeEmployer);
        employerDao.save(employer);
        return new ResponseEntity<>(new MessageResponse(
                "L'employer " + employer.getPersonne().getIdPersonne()
                        + " " + employer.getPersonne().getNom()
                        + "a été Modifier avec succes",
                200), HttpStatus.OK);
    }

    @PostMapping("/inscription")
    public ResponseEntity<?> inscriptionEmployer(@Valid @RequestBody InscriptionEmployerRequest inscriptionEmployerRequest) {
        ESituationMaritale situationMaritale;
        ETypeEmployer typeEmployer;
        try {
            situationMaritale = ESituationMaritale.valueOf(inscriptionEmployerRequest.getSituationMaritale());
        } catch (Exception E) {
            return new ResponseEntity<>(new MessageResponse("Situation marietal incorecte", 403), HttpStatus.FORBIDDEN);
        }
        try {
            typeEmployer = ETypeEmployer.valueOf(inscriptionEmployerRequest.getTypeEmployer());
        } catch (Exception E) {
            return new ResponseEntity<>(new MessageResponse("Type Employer incorecte", 403), HttpStatus.FORBIDDEN);
        }
        Optional<TypeIdentificateur> typeIdentificateur = typeIdentificateurDao.findById(inscriptionEmployerRequest.getIdTypeIdentificateur());
        if (typeIdentificateur.isEmpty())
            return new ResponseEntity<>(new MessageResponse("TypeIdentificateur invalide", 403), HttpStatus.FORBIDDEN);
        Optional<Nationalite> nationalite = nationaliteDao.findById(inscriptionEmployerRequest.getIdNationalite());
        if (nationalite.isEmpty())
            return new ResponseEntity<>(new MessageResponse("Nationnalité invalide", 403), HttpStatus.FORBIDDEN);
        ETypeContrat type;
        EUniteSalaire unite;
        try {
            type = ETypeContrat.valueOf(inscriptionEmployerRequest.getTypeContrat());
        } catch (Exception E) {
            return new ResponseEntity<>(
                    new MessageResponse("ETypeContrat introuvable", 403), HttpStatus.FORBIDDEN);
        }
        try {
            unite = EUniteSalaire.valueOf(inscriptionEmployerRequest.getUniteSalaire());
        } catch (Exception E) {
            return new ResponseEntity<>(
                    new MessageResponse("EUniteSalaire introuvable", 403), HttpStatus.FORBIDDEN);
        }
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
                situationMaritale,
                inscriptionEmployerRequest.getNbEnfant(),
                inscriptionEmployerRequest.getImage(),
                inscriptionEmployerRequest.getDateEntree(),
                inscriptionEmployerRequest.getRipIBAN(),
                inscriptionEmployerRequest.getPoste(),
                personne,
                typeEmployer);
        employerDao.save(employer);
        Contrat contrat = new Contrat(type, unite,
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
        ESituationMaritale situationMaritale;
        ETypeEmployer typeEmployer;
        try {
            situationMaritale = ESituationMaritale.valueOf(inscriptionEmployerByIdPersonneRequest.getSituationMaritale());
        } catch (Exception E) {
            return new ResponseEntity<>(new MessageResponse("Situation marietal incorecte"), HttpStatus.FORBIDDEN);
        }
        try {
            typeEmployer = ETypeEmployer.valueOf(inscriptionEmployerByIdPersonneRequest.getTypeEmployer());
        } catch (Exception E) {
            return new ResponseEntity<>(new MessageResponse("Type Employer incorecte"), HttpStatus.FORBIDDEN);
        }

        ETypeContrat type;
        EUniteSalaire unite;
        try {
            type = ETypeContrat.valueOf(inscriptionEmployerByIdPersonneRequest.getTypeContrat());
        } catch (Exception E) {
            return new ResponseEntity<>(
                    new MessageResponse("ETypeContrat introuvable", 403), HttpStatus.FORBIDDEN);
        }
        try {
            unite = EUniteSalaire.valueOf(inscriptionEmployerByIdPersonneRequest.getUniteSalaire());
        } catch (Exception E) {
            return new ResponseEntity<>(
                    new MessageResponse("EUniteSalaire introuvable", 403), HttpStatus.FORBIDDEN);
        }
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
                situationMaritale,
                inscriptionEmployerByIdPersonneRequest.getNbEnfant(),
                inscriptionEmployerByIdPersonneRequest.getImage(),
                inscriptionEmployerByIdPersonneRequest.getDateEntree(),
                inscriptionEmployerByIdPersonneRequest.getRipIBAN(),
                inscriptionEmployerByIdPersonneRequest.getPoste(),
                p.get(),
                typeEmployer);
        employerDao.save(employer);
        Contrat contrat = new Contrat(type, unite,
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
