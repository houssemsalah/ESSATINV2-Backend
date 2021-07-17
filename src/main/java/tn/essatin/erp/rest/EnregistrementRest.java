package tn.essatin.erp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.*;
import tn.essatin.erp.model.Enregistrement;
import tn.essatin.erp.model.Inscription;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.payload.request.NivSessRequest;
import tn.essatin.erp.payload.request.NouvelEnregistrementRequest;
import tn.essatin.erp.payload.request.SessionUnivRequest;
import tn.essatin.erp.payload.response.MessageResponse;

import javax.validation.Valid;
import java.time.LocalDate;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/enregistrement/")
public class EnregistrementRest {
    final EnregistrementDao enregistrementDao;
    final SessionDao sessionDao;
    final NiveauDao niveauDao;
    final InscriptionDao inscriptionDao;
    final EtatInscriptionDao etatInscriptionDao;
    final EtudiantsDao etudiantsDao;

    @Autowired
    public EnregistrementRest(EnregistrementDao enregistrementDao, SessionDao sessionDao, NiveauDao niveauDao, InscriptionDao inscriptionDao, EtatInscriptionDao etatInscriptionDao, EtudiantsDao etudiantsDao) {
        this.enregistrementDao = enregistrementDao;
        this.sessionDao = sessionDao;
        this.niveauDao = niveauDao;
        this.inscriptionDao = inscriptionDao;
        this.etatInscriptionDao = etatInscriptionDao;
        this.etudiantsDao = etudiantsDao;
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {

        return new ResponseEntity<>(enregistrementDao.findAll(), HttpStatus.OK);
    }

    @PostMapping("/getbyidns")
    public ResponseEntity<?> getById(@Valid @RequestBody NivSessRequest nivSessRequest) {
        if (
                niveauDao.findById(nivSessRequest.getIdNiveaux()).isPresent()
                        && sessionDao.findById(nivSessRequest.getIdSession()).isPresent()
        )
            return new ResponseEntity<>(enregistrementDao.findByIdNiveauAndAndIdSession(
                    niveauDao.findById(nivSessRequest.getIdNiveaux()).get(),
                    sessionDao.findById(nivSessRequest.getIdSession()).get())
                    , HttpStatus.OK);
        else
            return new ResponseEntity<>(new MessageResponse("Ressources indisponible", 403), HttpStatus.FORBIDDEN);
    }

    @PostMapping("/getenregistrementbysession")
    public ResponseEntity<?> getEnregistrementBySession(@Valid @RequestBody SessionUnivRequest sessionUnivRequest) {
        if (sessionDao.findById(sessionUnivRequest.getIdSession()).isPresent())
            return new ResponseEntity<>(enregistrementDao.findByIdSession(
                    sessionDao.findById(sessionUnivRequest.getIdSession()).get()), HttpStatus.OK
            );
        else
            return new ResponseEntity<>(new MessageResponse("Ressources indisponible", 403), HttpStatus.FORBIDDEN);
    }

    @PostMapping("/enregistrementniveauxsuivant")
    public ResponseEntity<?> enregistrementNiveauxSuivant(@Valid @RequestBody NouvelEnregistrementRequest nouvelEnregistrementRequest) {
        if (
                etudiantsDao.findById(nouvelEnregistrementRequest.getIdEtudiant()).isPresent()
                        && etatInscriptionDao.findById(2).isPresent()
                        && niveauDao.findById(nouvelEnregistrementRequest.getNiveauxInscrit()).isPresent()
        ) {
            LocalDate today = LocalDate.now();
            Session session = sessionDao.findTopByOrderByIdSessionDesc();
            Inscription i = inscriptionDao.findTopByIdEtudiantOrderByDateDesc(etudiantsDao.findById(nouvelEnregistrementRequest.getIdEtudiant()).get());
            i.setIdEtatInscription(etatInscriptionDao.findById(2).get());
            inscriptionDao.save(i);
            Enregistrement enr = new Enregistrement(i, niveauDao.findById(nouvelEnregistrementRequest.getNiveauxInscrit()).get(), session, today, 0);
            enregistrementDao.save(enr);
            return new ResponseEntity<>(new MessageResponse("Enregistrement effectué avec succes pour la session: " + session.getSession(), 200), HttpStatus.OK);
        } else
            return new ResponseEntity<>(new MessageResponse("Ressources indisponible", 403), HttpStatus.FORBIDDEN);

    }
}