package tn.essatin.erp.rest.scolarite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.PersonneDao;
import tn.essatin.erp.dao.SessionDao;
import tn.essatin.erp.dao.scolarite.*;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Scolarite.*;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.payload.request.GetByIdRequest;
import tn.essatin.erp.payload.request.IdentificateurRequest;
import tn.essatin.erp.payload.request.SessionUnivRequest;
import tn.essatin.erp.payload.request.scolarite.ModifierEnregistrementRequest;
import tn.essatin.erp.payload.request.scolarite.NivSessRequest;
import tn.essatin.erp.payload.request.scolarite.NouvelEnregistrementRequest;
import tn.essatin.erp.payload.request.scolarite.NumeroInscriptionRequest;
import tn.essatin.erp.payload.response.CombinedResponse;
import tn.essatin.erp.payload.response.MessageResponse;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    final NiveauSuivantDao niveauSuivantDao;
    final PersonneDao personneDao;

    @Autowired
    public EnregistrementRest(EnregistrementDao enregistrementDao, SessionDao sessionDao,
                              NiveauDao niveauDao, InscriptionDao inscriptionDao,
                              EtatInscriptionDao etatInscriptionDao, EtudiantsDao etudiantsDao,
                              NiveauSuivantDao niveauSuivantDao, PersonneDao personneDao) {
        this.enregistrementDao = enregistrementDao;
        this.sessionDao = sessionDao;
        this.niveauDao = niveauDao;
        this.inscriptionDao = inscriptionDao;
        this.etatInscriptionDao = etatInscriptionDao;
        this.etudiantsDao = etudiantsDao;
        this.niveauSuivantDao = niveauSuivantDao;
        this.personneDao = personneDao;
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(enregistrementDao.findAll(), HttpStatus.OK);
    }

    @PostMapping("/getbyidnss")
    public ResponseEntity<?> getById(@Valid @RequestBody NivSessRequest nivSessRequest) {
        if (
                niveauDao.findById(nivSessRequest.getIdNiveaux()).isPresent()
                        && sessionDao.findById(nivSessRequest.getIdSession()).isPresent()
        )
            return new ResponseEntity<>(enregistrementDao.findByIdNiveauAndIdSession(
                    niveauDao.findById(nivSessRequest.getIdNiveaux()).get(),
                    sessionDao.findById(nivSessRequest.getIdSession()).get())
                    , HttpStatus.OK);
        else
            return new ResponseEntity<>(
                    new MessageResponse("Ressources indisponible", 403), HttpStatus.FORBIDDEN);
    }

    @PostMapping("/getbyidns")
    public ResponseEntity<?> getByIds(int idNiveau, int idSession) {
        if (
                niveauDao.findById(idNiveau).isPresent()
                        && sessionDao.findById(idSession).isPresent()
        )
            return new ResponseEntity<>(enregistrementDao.findByIdNiveauAndIdSession(
                    niveauDao.findById(idNiveau).get(),
                    sessionDao.findById(idSession).get())
                    , HttpStatus.OK);
        else
            return new ResponseEntity<>(
                    new MessageResponse("Ressources indisponible", 403), HttpStatus.FORBIDDEN);
    }

    @PostMapping("/getenregistrementbysession")
    public ResponseEntity<?> getEnregistrementBySession(@Valid @RequestBody SessionUnivRequest sessionUnivRequest) {
        if (sessionDao.findById(sessionUnivRequest.getIdSession()).isPresent())
            return new ResponseEntity<>(enregistrementDao.findByIdSession(
                    sessionDao.findById(sessionUnivRequest.getIdSession()).get()), HttpStatus.OK
            );
        else
            return new ResponseEntity<>(
                    new MessageResponse("Ressources indisponible", 403), HttpStatus.FORBIDDEN);
    }

    @PostMapping("/getenregistrementbyidinscription")
    public ResponseEntity<?> getEnregistrementByIdInscription(@Valid @RequestBody GetByIdRequest getByIdRequest) {
        if (inscriptionDao.findById(getByIdRequest.getId()).isPresent()) {
            List<Enregistrement> le = enregistrementDao.findByIdInscription(inscriptionDao.findById(getByIdRequest.getId()).get());
            return new ResponseEntity<>(le, HttpStatus.OK);
        } else
            return new ResponseEntity<>(
                    new MessageResponse("Ressources indisponible", 403), HttpStatus.FORBIDDEN);
    }

    @PostMapping("/enregistrementniveauxsuivant")
    public ResponseEntity<?> enregistrementNiveauxSuivant(
            @Valid @RequestBody NouvelEnregistrementRequest nouvelEnregistrementRequest) {
        if (
                etudiantsDao.findById(nouvelEnregistrementRequest.getIdEtudiant()).isPresent()
                        && etatInscriptionDao.findById(2).isPresent()
                        && niveauDao.findById(nouvelEnregistrementRequest.getNiveauxInscrit()).isPresent()
        ) {
            Etudiants etudiants = etudiantsDao.findById(nouvelEnregistrementRequest.getIdEtudiant()).get();
            Niveau nouveauNiveau = niveauDao.findById(nouvelEnregistrementRequest.getNiveauxInscrit()).get();
            LocalDate today = LocalDate.now();
            Session session = sessionDao.findTopByOrderByIdSessionDesc();
            Inscription i = inscriptionDao.findTopByIdEtudiantOrderByDateDesc(etudiants);
            Enregistrement enregistrement = enregistrementDao.findTopByIdInscriptionOrderByIdEnregistrementDesc(i);
            Session ancienneSession = enregistrement.getIdSession();
            List<Niveau> ln = new ArrayList<>();
            ln.add(enregistrement.getIdNiveau());
            List<NiveauSuivant> lns = niveauSuivantDao.findByIdNiveau(enregistrement.getIdNiveau());
            for (NiveauSuivant ns : lns) {
                ln.add(ns.getIdNiveauSuivant());
            }
            if (ln.contains(nouveauNiveau)) {
                if (session.getIdSession() > ancienneSession.getIdSession()) {
                    Enregistrement enr = new Enregistrement(i, nouveauNiveau, session, today, 0);
                    enregistrementDao.save(enr);
                    i.setIdEtatInscription(etatInscriptionDao.findById(2).get());
                    inscriptionDao.save(i);
                    return new ResponseEntity<>(
                            new MessageResponse("Enregistrement effectu?? avec succes pour la session: "
                                    + session.getSession(), 200), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(
                            new MessageResponse("L'??tudiant " + etudiants.getIdPersonne().getPrenom()
                                    + " " + etudiants.getIdPersonne().getNom() + " est deja inscrit a la session "
                                    + session.getSession() + " Les inscrit a la session suivantes ne sont pas encor " +
                                    "ouvertes", 403), HttpStatus.FORBIDDEN);
                }
            } else {
                return new ResponseEntity<>(new CombinedResponse(
                        new MessageResponse("Le niveaux choisis ne correspond pas au niveaux possible " +
                                "pour cet etudiants ", 403), "Niveau[]", ln),
                        HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(
                    new MessageResponse("Ressources indisponible", 403), HttpStatus.FORBIDDEN);
        }
    }


    @PostMapping("/modiferenregistrement")
    public ResponseEntity<?> modifierEnregistrement(
            @Valid @RequestBody ModifierEnregistrementRequest modifierEnregistrementRequest) {
        Enregistrement enregistrement;
        Session session = sessionDao.findTopByOrderByIdSessionDesc();
        Niveau nouveauNiveau;
        if (
                enregistrementDao.findById(modifierEnregistrementRequest.getIdEnregistrement()).isPresent()
                        && niveauDao.findById(modifierEnregistrementRequest.getIdNiveaux()).isPresent()
        ) {
            nouveauNiveau = niveauDao.findById(modifierEnregistrementRequest.getIdNiveaux()).get();
            enregistrement = enregistrementDao.findById(modifierEnregistrementRequest.getIdEnregistrement()).get();
            List<Enregistrement> len = enregistrementDao.findByIdInscription(enregistrement.getIdInscription());
            if (len.size() < 2) {
                return new ResponseEntity<>(
                        new MessageResponse("cet etudiant est nouveaux, vous devez modifier son " +
                                "inscription pas son enregistrement!", 403), HttpStatus.FORBIDDEN);
            } else {
                if (!len.get(len.size() - 1).getIdEnregistrement().equals(enregistrement.getIdEnregistrement())) {
                    return new ResponseEntity<>(new MessageResponse("impossible de modifier un enregistrment " +
                            "si ce n'est pas le dernier pour cet inscription", 403), HttpStatus.FORBIDDEN);
                } else if (!enregistrement.getIdSession().getIdSession().equals(session.getIdSession())) {
                    return new ResponseEntity<>(new MessageResponse("impossible de modifier un enregistrment " +
                            "si il n'appartien pas a la session en cour", 403), HttpStatus.FORBIDDEN);
                } else {
                    Enregistrement gdima = len.get(len.size() - 2);
                    List<Niveau> ln = new ArrayList<>();
                    ln.add(gdima.getIdNiveau());
                    List<NiveauSuivant> lns = niveauSuivantDao.findByIdNiveau(gdima.getIdNiveau());
                    for (NiveauSuivant ns : lns) {
                        ln.add(ns.getIdNiveauSuivant());
                    }
                    if (ln.contains(nouveauNiveau)) {
                        enregistrement.setIdNiveau(nouveauNiveau);
                        enregistrementDao.save(enregistrement);
                        return new ResponseEntity<>(
                                new MessageResponse("Modification effectu?? avec succes", 200), HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(new CombinedResponse(
                                new MessageResponse("Le niveaux choisis ne correspond pas au niveaux possible " +
                                        "pour cet etudiants ", 403), "Niveau[]", ln),
                                HttpStatus.FORBIDDEN);
                    }
                }
            }
        } else {
            return new ResponseEntity<>(
                    new MessageResponse("Ressources indisponible", 403), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/getenregistrementbynumeroinscription")
    public ResponseEntity<?> getEnregistrementByNumeroInscription(@Valid @RequestBody NumeroInscriptionRequest numeroInscriptionRequest) {
        Optional<Inscription> oi = inscriptionDao.findByNumeroInscription(numeroInscriptionRequest.getNumeroInscription());
        if (oi.isPresent()) {
            Enregistrement en = enregistrementDao.findTopByIdInscriptionOrderByIdEnregistrementDesc(oi.get());
            return new ResponseEntity<>(en, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    new MessageResponse("Ressources indisponible", 403), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/getenregistrementbynumeroidentifiant")
    public ResponseEntity<?> getEnregistrementByNumeroIdentifiant(@Valid @RequestBody IdentificateurRequest identificateurRequest) {
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
        if (cinE.isEmpty())
            return new ResponseEntity<>(new MessageResponse("Personne introuvable", 403),
                    HttpStatus.FORBIDDEN);
        Inscription inscri = inscriptionDao.findTopByIdEtudiantOrderByDateDesc(cinE.get(0));
        Enregistrement en = enregistrementDao.findTopByIdInscriptionOrderByIdEnregistrementDesc(inscri);
        return new ResponseEntity<>(en, HttpStatus.OK);
    }


    /*@PostMapping("/getcertificatreussite")
    public ResponseEntity<?> getCertificatReussite(@Valid @RequestBody GetByIdRequest getByIdEnregistrementRequest) {
        Optional<Enregistrement> oe = enregistrementDao.findById(getByIdEnregistrementRequest.getId());
        if (oe.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Enregistrement Etudiant introvable", 403), HttpStatus.FORBIDDEN);
        }
        Enregistrement enregistrement = oe.get();


        ByteArrayOutputStream os = AttestationDeReussite.createDoc(enregistrement,"Principale",1,new Date());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
        ByteArrayResource resource = new ByteArrayResource(os.toByteArray());
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }*/
}
