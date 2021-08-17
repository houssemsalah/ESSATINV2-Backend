package tn.essatin.erp.rest.examen;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.SalleDao;
import tn.essatin.erp.dao.SessionDao;
import tn.essatin.erp.dao.scolarite.EnregistrementDao;
import tn.essatin.erp.dao.scolarite.NiveauDao;
import tn.essatin.erp.model.Salle;
import tn.essatin.erp.model.Scolarite.Enregistrement;
import tn.essatin.erp.model.Scolarite.Niveau;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.payload.request.FeuilleEmergementPersonnaliserRequest;
import tn.essatin.erp.payload.request.scolarite.FeuilleDEmargementRequest;
import tn.essatin.erp.payload.request.scolarite.FeuilleDeNote;
import tn.essatin.erp.payload.response.MessageResponse;
import tn.essatin.erp.util.ApiInfo;
import tn.essatin.erp.util.DocumentGenerators.DocumentFunction;
import tn.essatin.erp.util.DocumentGenerators.FeuilleDEmargement;
import tn.essatin.erp.util.DocumentGenerators.FeuilleDEmargementPersonnalise;
import tn.essatin.erp.util.DocumentGenerators.FicheDeNote;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/examdocs/")
public class ExamenDocs {

    final EnregistrementDao enregistrementDao;
    final NiveauDao niveauDao;
    final SessionDao sessionDao;
    final SalleDao salleDao;

    public ExamenDocs(EnregistrementDao enregistrementDao,
                      NiveauDao niveauDao, SessionDao sessionDao, SalleDao salleDao) {
        this.enregistrementDao = enregistrementDao;
        this.niveauDao = niveauDao;
        this.salleDao = salleDao;
        this.sessionDao = sessionDao;
    }

    @GetMapping("/")
    public ResponseEntity<?> infoApi(){
        /////
        List<ApiInfo> infos = new ArrayList<>();
        List<MessageResponse> responses = new ArrayList<>();
        responses.add(new MessageResponse("Niveau est introvable", 403));
        responses.add(new MessageResponse("Session est introvable", 403));
        responses.add(new MessageResponse("Classe vide!!!", 403));
        ApiInfo feulleDeNote = new ApiInfo("/api/examdocs/getfeuilledenotebyniveauetsession",
                "Post",
                "Retourne une feille de notes en PDF",
                new FeuilleDeNote(28,6,15),
                "Document PDF",responses);
        infos.add(feulleDeNote);
        return new ResponseEntity<>(infos,HttpStatus.OK);
        /////////////////////

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
        if (enregistrementList.isEmpty())
            return new ResponseEntity<>(
                    new MessageResponse("liste d'etudiants vide", 403), HttpStatus.FORBIDDEN);
        ByteArrayOutputStream os = FeuilleDEmargement.createDoc(enregistrementList,
                salle.getNombreDeRangee(), salle.getNombreDePlaceExamen());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
        ByteArrayResource resource = new ByteArrayResource(os.toByteArray());
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);

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
