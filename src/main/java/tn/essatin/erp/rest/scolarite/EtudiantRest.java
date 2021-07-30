package tn.essatin.erp.rest.scolarite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.*;
import tn.essatin.erp.dao.scolarite.*;
import tn.essatin.erp.model.*;
import tn.essatin.erp.model.Scolarite.*;
import tn.essatin.erp.payload.request.scolarite.*;
import tn.essatin.erp.payload.request.IdentificateurRequest;
import tn.essatin.erp.payload.response.MessageResponse;
import tn.essatin.erp.util.DocumentGenerators.*;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    public EtudiantRest(PersonneDao personneDao, EtudiantsDao etudiantsDao,
                        SessionDao sessionDao, TypeIdentificateurDao typeIdentificateurDAO,
                        ContactEtudiantDao contactEtudiantDao, InscriptionDao inscriptionDao,
                        EnregistrementDao enregistrementDao, NiveauDao niveauDao, ParcoursDao parcoursDao,
                        SpecialiteDao specialiteDao, CycleDao cycleDao, DiplomeEtudiantDao diplomeEtudiantDao) {
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



    @PostMapping("/getfichepresencebyniveauetsession")
    public ResponseEntity<?> getFichePresenceByNiveauEtSession(@Valid @RequestBody PresenceNiveauSession presenceNiveauSession) {
        if (
               niveauDao.findById(presenceNiveauSession.getIdNiveau()).isPresent()
                && sessionDao.findById(presenceNiveauSession.getIdSession()).isPresent()
        ) {
            Niveau n=niveauDao.findById(presenceNiveauSession.getIdNiveau()).get();
            Session s=sessionDao.findById(presenceNiveauSession.getIdSession()).get();
            List<Enregistrement>  enregistrementList = enregistrementDao.findByIdNiveauAndIdSession(n,s);
            if (enregistrementList.size()>0) {
                ByteArrayOutputStream os = ListePresence.createDoc(enregistrementList);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
                ByteArrayResource resource = new ByteArrayResource(os.toByteArray());
                return new ResponseEntity<>(resource, headers, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(
                        new MessageResponse("Classe vide!!!", 403), HttpStatus.FORBIDDEN);
            }
        } else
            return new ResponseEntity<>(
                    new MessageResponse("Ressource indisponible", 403), HttpStatus.FORBIDDEN);
    }

    @PostMapping("/getfeuilledenotebyniveauetsession")
    public ResponseEntity<?> getFeuilleDeNoteByNiveauEtSession(@Valid @RequestBody FeuilleDeNote feuilleDeNote) {
        if (
                niveauDao.findById(feuilleDeNote.getIdNiveau()).isPresent()
                        && sessionDao.findById(feuilleDeNote.getIdSession()).isPresent()
        ) {
            Niveau n=niveauDao.findById(feuilleDeNote.getIdNiveau()).get();
            Session s=sessionDao.findById(feuilleDeNote.getIdSession()).get();
            List<Enregistrement>  enregistrementList = enregistrementDao.findByIdNiveauAndIdSession(n,s);
            if (enregistrementList.size()>0) {
                ByteArrayOutputStream os = FicheDeNote.createDoc(enregistrementList,feuilleDeNote.getColones());
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
                ByteArrayResource resource = new ByteArrayResource(os.toByteArray());
                return new ResponseEntity<>(resource, headers, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(
                        new MessageResponse("Classe vide!!!", 403), HttpStatus.FORBIDDEN);
            }
        } else
            return new ResponseEntity<>(
                    new MessageResponse("Ressource indisponible", 403), HttpStatus.FORBIDDEN);
    }

    @PostMapping("/getfeuilledemargement")
    public ResponseEntity<?> getFeuilleDEmargement(@Valid @RequestBody FeuilleDEmargement feuilleDEmargement) {
        if (
                niveauDao.findById(feuilleDEmargement.getIdNiveau()).isPresent()
                        && sessionDao.findById(feuilleDEmargement.getIdSession()).isPresent()
        ) {
            Niveau n=niveauDao.findById(feuilleDEmargement.getIdNiveau()).get();
            Session s=sessionDao.findById(feuilleDEmargement.getIdSession()).get();
            List<Enregistrement>  enregistrementList = enregistrementDao.findByIdNiveauAndIdSession(n,s);
            if (enregistrementList.size()>0) {
                ByteArrayOutputStream os = FicheDeNote.createDoc(enregistrementList,feuilleDEmargement.getColones());
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
                ByteArrayResource resource = new ByteArrayResource(os.toByteArray());
                return new ResponseEntity<>(resource, headers, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(
                        new MessageResponse("Classe vide!!!", 403), HttpStatus.FORBIDDEN);
            }
        } else
            return new ResponseEntity<>(
                    new MessageResponse("Ressource indisponible", 403), HttpStatus.FORBIDDEN);
    }


}
