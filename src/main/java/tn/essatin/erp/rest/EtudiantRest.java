package tn.essatin.erp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.*;
import tn.essatin.erp.model.ContactEtudiant;
import tn.essatin.erp.model.DiplomeEtudiant;
import tn.essatin.erp.model.Etudiants;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.payload.request.CertificatRequest;
import tn.essatin.erp.payload.request.IdentificateurRequest;
import tn.essatin.erp.payload.request.InfoRequest;
import tn.essatin.erp.payload.response.MessageResponse;
import tn.essatin.erp.util.DocumentGenerators.CertificatDInscription;
import tn.essatin.erp.util.DocumentGenerators.CertificateDePresence;
import tn.essatin.erp.util.DocumentGenerators.FicheRenseignement;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/etudiants/")
public class EtudiantRest {
    @Autowired
    EtudiantsDao etudiantsDao;
    @Autowired
    PersonneDao personneDao;
    @Autowired
    SessionDao sessionDao;
    @Autowired
    TypeIdentificateurDao typeIdentificateurDAO;
    @Autowired
    InscriptionDao inscriptionDao;
    @Autowired
    EnregistrementDao enregistrementDao;
    @Autowired
    NiveauDao niveauDao;
    @Autowired
    ParcoursDao parcoursDao;
    @Autowired
    SpecialiteDao specialiteDao;
    @Autowired
    CycleDao cycleDao;
    @Autowired
    DiplomeEtudiantDao diplomeEtudiantDao;
    @Autowired
    ContactEtudiantDao contactEtudiantDao;

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<List>(etudiantsDao.findAll(), HttpStatus.OK);
    }

    @PostMapping("/getbynumid")
    public ResponseEntity<?> getByNumIdentificateur(@Valid @RequestBody IdentificateurRequest identificateurRequest) {
        List<Etudiants> cinE = new ArrayList<Etudiants>();
        for (Etudiants E : etudiantsDao.findAll()) {
            int idp = E.getIdPersonne().getIdPersonne();
            Personne p = personneDao.findById(idp).get();
            String Identificateur = p.getNumeroIdentificateur();
            if (Identificateur.equalsIgnoreCase(identificateurRequest.getNumidentificateur()))
                cinE.add(E);
        }
        if (cinE.size()>0) {
            return new ResponseEntity<List>(cinE, HttpStatus.OK);
        }else
            return new ResponseEntity<>(new MessageResponse("Personne introuvable",204),HttpStatus.NO_CONTENT);
    }

    @PostMapping("/getcertifpresence")
    public ResponseEntity<?> getCertifPresence(@Valid @RequestBody CertificatRequest certificatRequest) {
        ByteArrayOutputStream os = CertificateDePresence.createDoc(enregistrementDao.findById(certificatRequest.getIdEnregistrement()).get(), certificatRequest.isDirecteur());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
        ByteArrayResource resource = new ByteArrayResource(os.toByteArray());
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
    @PostMapping("/getcertifinscription")
    public ResponseEntity<?> getCertifInscription(@Valid @RequestBody CertificatRequest certificatRequest) {
        ByteArrayOutputStream os = CertificatDInscription.createDoc(enregistrementDao.findById(certificatRequest.getIdEnregistrement()).get(), certificatRequest.isDirecteur());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
        ByteArrayResource resource = new ByteArrayResource(os.toByteArray());
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @PostMapping("/getficheinformation")
    public ResponseEntity<?> getFicheRenseignement(@Valid @RequestBody InfoRequest infoRequest) {
        List<DiplomeEtudiant> de = diplomeEtudiantDao.findByIdEtudiant(enregistrementDao.findById(infoRequest.getIdEnregistrement()).get().getIdInscription().getIdEtudiant());
        List<ContactEtudiant> ce = contactEtudiantDao.findByIdEtudiant(enregistrementDao.findById(infoRequest.getIdEnregistrement()).get().getIdInscription().getIdEtudiant());
        ByteArrayOutputStream os = FicheRenseignement.createDoc(enregistrementDao.findById(infoRequest.getIdEnregistrement()).get().getIdInscription().getIdEtudiant(), ce,de);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
        ByteArrayResource resource = new ByteArrayResource(os.toByteArray());
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

}
