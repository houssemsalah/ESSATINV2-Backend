package tn.essatin.erp.rest.financier;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.SessionDao;
import tn.essatin.erp.dao.financier.PrixNiveauParSessionDao;
import tn.essatin.erp.dao.scolarite.NiveauDao;
import tn.essatin.erp.model.Scolarite.Niveau;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.model.financier.PrixNiveauParSession;
import tn.essatin.erp.payload.request.financier.PrixNiveauRequest;
import tn.essatin.erp.payload.request.financier.PrixNiveauUpdateRequest;
import tn.essatin.erp.payload.response.MessageResponse;
import tn.essatin.erp.util.ApiInfo;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/prixniveauparsession/")
public class PrixNiveauParSessionRest {
    final PrixNiveauParSessionDao prixNiveauParSessionDao;
    final SessionDao sessionDao;
    final NiveauDao niveauDao;
    final MessageSource messageSource;


    public PrixNiveauParSessionRest(PrixNiveauParSessionDao prixNiveauParSessionDao, SessionDao sessionDao,
                                    NiveauDao niveauDao, MessageSource messageSource) {
        this.prixNiveauParSessionDao = prixNiveauParSessionDao;
        this.sessionDao = sessionDao;
        this.niveauDao = niveauDao;
        this.messageSource = messageSource;
    }

    @GetMapping("/")
    public ResponseEntity<?> infoApi() {
        List<ApiInfo> infos = new ArrayList<>();
        List<MessageResponse> responses;
        ApiInfo info;

        /////////////////////
        responses = new ArrayList<>();
        info = new ApiInfo("/api/prixniveauparsession/getall", "Get",
                "retourne un JSON avec la liste de tout les PrixNiveauParSession dans la base",
                "/api/prixniveauparsession/getall",
                "JSON text Message", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.session", null, Locale.FRENCH), 403));
        info = new ApiInfo("/api/prixniveauparsession/getbysession/{idSession}", "Get",
                "retourne un JSON avec la liste de tout les PrixNiveauParSession pour une session",
                "/api/prixniveauparsession/getbysession/6",
                "JSON text Message", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.session", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.niveau", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.prixNiveauParSession", null, Locale.FRENCH), 403));

        info = new ApiInfo("/api/prixniveauparsession/getbysessionetniveau/{idSession}/{idNiveaux}", "Get",
                "retourne un JSON avec le PrixNiveauParSession demander",
                "/api/prixniveauparsession/getbysessionetniveau/6/28",
                "JSON text Message", responses);
        infos.add(info);
        /////////////////////

        return new ResponseEntity<>(infos, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        List<PrixNiveauParSession> prixNiveauParSessions = prixNiveauParSessionDao.findAll();
        return new ResponseEntity<>(prixNiveauParSessions, HttpStatus.OK);
    }

    @GetMapping("/getbysession/{idSession}")
    public ResponseEntity<?> getBySession(@PathVariable int idSession) {
        Optional<Session> session = sessionDao.findById(idSession);
        if (session.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Session Introuvable!", 403), HttpStatus.FORBIDDEN);
        }
        Collection<PrixNiveauParSession> prixNiveauParSessions = prixNiveauParSessionDao.findAllBySession(session.get());
        return new ResponseEntity<>(prixNiveauParSessions, HttpStatus.OK);
    }

    @GetMapping("/getbysessionetniveau/{idSession}/{idNiveaux}")
    public ResponseEntity<?> getBySessionAndNiveau(@PathVariable int idSession, @PathVariable int idNiveaux) {
        Optional<Session> session = sessionDao.findById(idSession);
        if (session.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Session Introuvable!", 403), HttpStatus.FORBIDDEN);
        }
        Optional<Niveau> niveau = niveauDao.findById(idNiveaux);
        if (niveau.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Niveau Introuvable!", 403), HttpStatus.FORBIDDEN);
        }
        Optional<PrixNiveauParSession> prixNiveauParSessions = prixNiveauParSessionDao.findBySessionAndNiveau(session.get(), niveau.get());
        if (prixNiveauParSessions.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("prixNiveauParSessions Introuvable!", 403), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(prixNiveauParSessions, HttpStatus.OK);
    }
    @PostMapping("/addprixniveauxparsession")
    public ResponseEntity<?> addContrat(@Valid @RequestBody PrixNiveauRequest prixNiveauRequest ){
        Optional<Niveau> niveau =niveauDao.findById(prixNiveauRequest.getIdNiveau());
        if (niveau.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Niveau Introuvable!", 403), HttpStatus.FORBIDDEN);
        }
        Optional<Session> session = sessionDao.findById(prixNiveauRequest.getIdSession());
        if (session.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Session Introuvable!", 403), HttpStatus.FORBIDDEN);
        }
        PrixNiveauParSession prixNiveauParSession = new PrixNiveauParSession(niveau.get(), session.get(),
                prixNiveauRequest.getMontant(), prixNiveauRequest.getDate());
        prixNiveauParSessionDao.save(prixNiveauParSession);
        return new ResponseEntity<>(
                new MessageResponse("Le Prix de "
                        + niveau.get()+ session.get()
                        + " Ajouter avec Success",
                        200), HttpStatus.OK);

    }
    @PostMapping("/modifierprixniveauxparsession")
    public ResponseEntity<?> updateContrat(@Valid @RequestBody PrixNiveauUpdateRequest updatePrixNiveauRequest ){
        Optional<PrixNiveauParSession> optPrixNiveauParSession = prixNiveauParSessionDao.findById(updatePrixNiveauRequest.getId());
        if (optPrixNiveauParSession.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Niveau Introuvable!", 403), HttpStatus.FORBIDDEN);
        }
        Optional<Niveau> niveau =niveauDao.findById(updatePrixNiveauRequest.getIdNiveau());
        if (niveau.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Niveau Introuvable!", 403), HttpStatus.FORBIDDEN);
        }
        Optional<Session> session = sessionDao.findById(updatePrixNiveauRequest.getIdSession());
        if (session.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Session Introuvable!", 403), HttpStatus.FORBIDDEN);
        }
        PrixNiveauParSession prixNiveauParSession = new PrixNiveauParSession(niveau.get(), session.get(),
                updatePrixNiveauRequest.getMontant(), updatePrixNiveauRequest.getDate());
        prixNiveauParSessionDao.save(prixNiveauParSession);
        return new ResponseEntity<>(
                new MessageResponse("Le Prix de "
                        + niveau.get()+ session.get()
                        + " Ajouter avec Success",
                        200), HttpStatus.OK);

    }

}
