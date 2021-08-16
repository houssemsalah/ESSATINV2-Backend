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
import tn.essatin.erp.model.Scolarite.*;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.payload.request.IdentificateurRequest;
import tn.essatin.erp.payload.request.examen.DemandeDeStageRequest;
import tn.essatin.erp.payload.request.scolarite.CertificatRequest;
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
        Optional<Enregistrement> enregistrement = enregistrementDao.findById(certificatRequest.getIdEnregistrement());
        if (enregistrement.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Enregistrement introuvable", 403), HttpStatus.FORBIDDEN);
        }
        ByteArrayOutputStream os = CertificateDePresence.createDoc(enregistrement.get(),
                certificatRequest.isDirecteur());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
        ByteArrayResource resource = new ByteArrayResource(os.toByteArray());
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @PostMapping("/getcertifinscription")
    public ResponseEntity<?> getCertifInscription(@Valid @RequestBody CertificatRequest certificatRequest) {
        Optional<Enregistrement> enregistrement = enregistrementDao.findById(certificatRequest.getIdEnregistrement());
        if (enregistrement.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Enregistrement introuvable", 403), HttpStatus.FORBIDDEN);
        }
        ByteArrayOutputStream os = CertificatDInscription.createDoc(
                enregistrement.get(),
                certificatRequest.isDirecteur());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
        ByteArrayResource resource = new ByteArrayResource(os.toByteArray());
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);

    }

    @PostMapping("/getficheinformation")
    public ResponseEntity<?> getFicheRenseignement(@Valid @RequestBody InfoRequest infoRequest) {
        Optional<Enregistrement> enregistrement = enregistrementDao.findById(infoRequest.getIdEnregistrement());
        if (enregistrement.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Enregistrement introuvable", 403), HttpStatus.FORBIDDEN);
        }
        List<DiplomeEtudiant> de = diplomeEtudiantDao.findByIdEtudiant(enregistrement.get().getIdInscription().getIdEtudiant());
        List<ContactEtudiant> ce = contactEtudiantDao.findByIdEtudiant(enregistrement.get().getIdInscription().getIdEtudiant());
        ByteArrayOutputStream os = FicheRenseignement.createDoc(enregistrement.get().getIdInscription().getIdEtudiant(), ce, de);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
        ByteArrayResource resource = new ByteArrayResource(os.toByteArray());
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
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

    @PostMapping("/getdemandedestage")
    public ResponseEntity<?> getFeuilleDeDemandeDeStage
            (@Valid @RequestBody DemandeDeStageRequest demandeDeStageRequest) {
        Optional<Enregistrement> etudiant = enregistrementDao.findById(demandeDeStageRequest.getIdenregistrement());
        if (etudiant.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Enregistrement introuvable", 403), HttpStatus.FORBIDDEN);
        }
        ByteArrayOutputStream os = FeuilleDeDemandeDeStage.createDoc(etudiant.get(), demandeDeStageRequest.getNomSociete(), demandeDeStageRequest.getNumCase());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
        ByteArrayResource resource = new ByteArrayResource(os.toByteArray());
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

}
