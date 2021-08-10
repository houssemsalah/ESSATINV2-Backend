package tn.essatin.erp.rest.scolarite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.PersonneDao;
import tn.essatin.erp.dao.SalleDao;
import tn.essatin.erp.dao.SessionDao;
import tn.essatin.erp.dao.TypeIdentificateurDao;
import tn.essatin.erp.dao.scolarite.*;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Salle;
import tn.essatin.erp.model.Scolarite.*;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.payload.request.FeuilleEmergementPersonnaliserRequest;
import tn.essatin.erp.payload.request.IdentificateurRequest;
import tn.essatin.erp.payload.request.scolarite.CertificatRequest;
import tn.essatin.erp.payload.request.scolarite.FeuilleDEmargementRequest;
import tn.essatin.erp.payload.request.scolarite.FeuilleDeNote;
import tn.essatin.erp.payload.request.scolarite.InfoRequest;
import tn.essatin.erp.payload.response.MessageResponse;
import tn.essatin.erp.util.DocumentGenerators.*;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/etudiants/")
public class EtudiantRest {
    final EtudiantsDao etudiantsDao;
    final PersonneDao personneDao;
    final SessionDao sessionDao;
    final TypeIdentificateurDao typeIdentificateurDAO;
    final InscriptionDao inscriptionDao;
    final EnregistrementDao enregistrementDao;
    final NiveauDao niveauDao;
    final ParcoursDao parcoursDao;
    final SpecialiteDao specialiteDao;
    final CycleDao cycleDao;
    final DiplomeEtudiantDao diplomeEtudiantDao;
    final ContactEtudiantDao contactEtudiantDao;
    final SalleDao salleDao;


    @Autowired
    public EtudiantRest(PersonneDao personneDao, EtudiantsDao etudiantsDao,
                        SessionDao sessionDao, TypeIdentificateurDao typeIdentificateurDAO,
                        ContactEtudiantDao contactEtudiantDao, InscriptionDao inscriptionDao,
                        EnregistrementDao enregistrementDao, NiveauDao niveauDao, ParcoursDao parcoursDao,
                        SpecialiteDao specialiteDao, CycleDao cycleDao, DiplomeEtudiantDao diplomeEtudiantDao, SalleDao salleDao) {
        this.personneDao = personneDao;
        this.etudiantsDao = etudiantsDao;
        this.sessionDao = sessionDao;
        this.typeIdentificateurDAO = typeIdentificateurDAO;
        this.contactEtudiantDao = contactEtudiantDao;
        this.inscriptionDao = inscriptionDao;
        this.enregistrementDao = enregistrementDao;
        this.niveauDao = niveauDao;
        this.parcoursDao = parcoursDao;
        this.specialiteDao = specialiteDao;
        this.cycleDao = cycleDao;
        this.diplomeEtudiantDao = diplomeEtudiantDao;
        this.salleDao = salleDao;
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {

        return new ResponseEntity<>(etudiantsDao.findAll(), HttpStatus.OK);
    }

    @PostMapping("/getbynumid")
    public ResponseEntity<?> getByNumIdentificateur(@Valid @RequestBody IdentificateurRequest identificateurRequest) {
        List<Etudiants> cinE = new ArrayList<>();
        for (Etudiants E : etudiantsDao.findAll()) {
            int idp = E.getIdPersonne().getIdPersonne();
            if (personneDao.findById(idp).isPresent()) {
                Personne p = personneDao.findById(idp).get();
                String Identificateur = p.getNumeroIdentificateur();
                if (Identificateur.equalsIgnoreCase(identificateurRequest.getNumidentificateur()))
                    cinE.add(E);
            }
        }
        if (cinE.size() > 0) {
            return new ResponseEntity<>(cinE, HttpStatus.OK);
        } else
            return new ResponseEntity<>(new MessageResponse("Personne introuvable", 204),
                    HttpStatus.NO_CONTENT);
    }

    @PostMapping("/getcertifpresence")
    public ResponseEntity<?> getCertifPresence(@Valid @RequestBody CertificatRequest certificatRequest) {
        if (
                enregistrementDao.findById(certificatRequest.getIdEnregistrement()).isPresent()
        ) {
            ByteArrayOutputStream os = CertificateDePresence.createDoc(
                    enregistrementDao.findById(certificatRequest.getIdEnregistrement()).get(),
                    certificatRequest.isDirecteur());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
            ByteArrayResource resource = new ByteArrayResource(os.toByteArray());
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } else
            return new ResponseEntity<>(
                    new MessageResponse("Ressource indisponible", 403), HttpStatus.FORBIDDEN);
    }

    @PostMapping("/getcertifinscription")
    public ResponseEntity<?> getCertifInscription(@Valid @RequestBody CertificatRequest certificatRequest) {
        if (
                enregistrementDao.findById(certificatRequest.getIdEnregistrement()).isPresent()
        ) {
            ByteArrayOutputStream os = CertificatDInscription.createDoc(
                    enregistrementDao.findById(certificatRequest.getIdEnregistrement()).get(),
                    certificatRequest.isDirecteur());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
            ByteArrayResource resource = new ByteArrayResource(os.toByteArray());
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } else
            return new ResponseEntity<>(
                    new MessageResponse("Ressource indisponible", 403), HttpStatus.FORBIDDEN);
    }

    @PostMapping("/getficheinformation")
    public ResponseEntity<?> getFicheRenseignement(@Valid @RequestBody InfoRequest infoRequest) {
        if (
                enregistrementDao.findById(infoRequest.getIdEnregistrement()).isPresent()
        ) {
            Enregistrement e = enregistrementDao.findById(infoRequest.getIdEnregistrement()).get();
            List<DiplomeEtudiant> de = diplomeEtudiantDao.findByIdEtudiant(e.getIdInscription().getIdEtudiant());
            List<ContactEtudiant> ce = contactEtudiantDao.findByIdEtudiant(e.getIdInscription().getIdEtudiant());
            ByteArrayOutputStream os = FicheRenseignement.createDoc(e.getIdInscription().getIdEtudiant(), ce, de);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
            ByteArrayResource resource = new ByteArrayResource(os.toByteArray());
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } else
            return new ResponseEntity<>(
                    new MessageResponse("Ressource indisponible", 403), HttpStatus.FORBIDDEN);
    }


    @GetMapping("/getfichepresencebyniveau/{id}")
    public ResponseEntity<?> getFichePresenceByNiveauEtSession(@PathVariable int id) {
        Optional<Niveau> niveau = niveauDao.findById(id);
        if (niveau.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Niveaux introuvable", 403), HttpStatus.FORBIDDEN);
        }
        Niveau n = niveau.get();
        Session s = sessionDao.findTopByOrderByIdSessionDesc();
        List<Enregistrement> enregistrementList = enregistrementDao.findByIdNiveauAndIdSession(n, s);
        if (enregistrementList.size() > 0) {
            ByteArrayOutputStream os = ListePresence.createDoc(enregistrementList);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
            ByteArrayResource resource = new ByteArrayResource(os.toByteArray());
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    new MessageResponse("Classe vide!!!", 403), HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping("/getfeuilledenotebyniveauetsession")
    public ResponseEntity<?> getFeuilleDeNoteByNiveauEtSession(@Valid @RequestBody FeuilleDeNote feuilleDeNote) {
        Optional<Niveau> ni = niveauDao.findById(feuilleDeNote.getIdNiveau());
        if (ni.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Niveau est introvable", 403), HttpStatus.FORBIDDEN);
        }
        Optional<Session> se = sessionDao.findById(feuilleDeNote.getIdSession());
        if (se.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Session est introvable", 403), HttpStatus.FORBIDDEN);
        }
        Niveau n = ni.get();
        Session s = se.get();
        List<Enregistrement> enregistrementList = enregistrementDao.findByIdNiveauAndIdSession(n, s);
        if (!enregistrementList.isEmpty()) {
            ByteArrayOutputStream os = FicheDeNote.createDoc(enregistrementList, feuilleDeNote.getColones());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
            ByteArrayResource resource = new ByteArrayResource(os.toByteArray());
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    new MessageResponse("Classe vide!!!", 403), HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping("/getfeuilledemargement")
    public ResponseEntity<?> getFeuilleDEmargement(@Valid @RequestBody FeuilleDEmargementRequest feuilleDEmargementRequest) {
        Optional<Niveau> ni = niveauDao.findById(feuilleDEmargementRequest.getIdNiveau());
        if (ni.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Niveau est introvable", 403), HttpStatus.FORBIDDEN);
        }
        Niveau n = ni.get();

        Optional<Session> se = sessionDao.findById(feuilleDEmargementRequest.getIdSession());
        if (se.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Session est introvable", 403), HttpStatus.FORBIDDEN);
        }
        Session s = se.get();

        Optional<Salle> sa = salleDao.findById(feuilleDEmargementRequest.getIdSalle());
        if (sa.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Salle est introvable", 403), HttpStatus.FORBIDDEN);
        }
        Salle salle = sa.get();

        List<Enregistrement> enregistrementList = enregistrementDao.findByIdNiveauAndIdSession(n, s);
        if (!enregistrementList.isEmpty()) {
            ByteArrayOutputStream os = FeuilleDEmargement.createDoc(enregistrementList,
                    salle.getNombreDeRangee(), salle.getNombreDePlaceExamen());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
            ByteArrayResource resource = new ByteArrayResource(os.toByteArray());
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    new MessageResponse("liste d'etudiants vide", 403), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/getfeuilledemargementpersonnalise")
    public ResponseEntity<?> getFeuilleDEmargementPersonnalise
            (@Valid @RequestBody FeuilleEmergementPersonnaliserRequest feuilleEmergementPersonnaliserRequest) {
        List<Integer[]> listIdInt = feuilleEmergementPersonnaliserRequest.getIdEnregistrements();
        if (listIdInt.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("liste d'etudiants vide", 403), HttpStatus.FORBIDDEN);
        }
        Optional<Salle> sa = salleDao.findById(feuilleEmergementPersonnaliserRequest.getIdSalle());
        if (sa.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Salle est introvable", 403), HttpStatus.FORBIDDEN);
        }
        Salle salle = sa.get();
        List<List<Enregistrement>> listListEnregistrements = new ArrayList<>();
        int nbEtudiant = 0;
        for (Integer[] listIdEnregistrement : listIdInt) {
            List<Enregistrement> le = new ArrayList<>();
            for (Integer idEnrtegistrement : listIdEnregistrement) {
                Optional<Enregistrement> oe = enregistrementDao.findById(idEnrtegistrement);
                if (oe.isPresent()) {
                    le.add(oe.get());
                    nbEtudiant++;
                }
            }
            if (!le.isEmpty())
                listListEnregistrements.add(le);
        }
        if (nbEtudiant > salle.getNombreDePlaceExamen()) {
            return new ResponseEntity<>(
                    new MessageResponse("nombre de place insuffisant sans cette salle", 403), HttpStatus.FORBIDDEN);
        }
        List<String> places = DocumentFunction.listeAleatoire(salle.getNombreDeRangee(),
                salle.getNombreDePlaceExamen(), nbEtudiant);
        if (listListEnregistrements.size() > 0) {
            ByteArrayOutputStream os = FeuilleDEmargementPersonnalise.createDoc(listListEnregistrements, places);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
            ByteArrayResource resource = new ByteArrayResource(os.toByteArray());
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    new MessageResponse("listes vide!!!", 403), HttpStatus.FORBIDDEN);
        }
    }


}
