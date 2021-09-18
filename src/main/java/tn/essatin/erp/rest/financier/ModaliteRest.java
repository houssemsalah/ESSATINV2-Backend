package tn.essatin.erp.rest.financier;

import org.springframework.context.MessageSource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.CompteDao;
import tn.essatin.erp.dao.financier.ModaliteTransactionDao;
import tn.essatin.erp.dao.financier.MotifAnnulationRejetModaliteDao;
import tn.essatin.erp.dao.financier.TransactionDao;
import tn.essatin.erp.dao.scolarite.EnregistrementDao;
import tn.essatin.erp.dao.scolarite.EtudiantsDao;
import tn.essatin.erp.dao.scolarite.InscriptionDao;
import tn.essatin.erp.model.Compte;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Scolarite.Enregistrement;
import tn.essatin.erp.model.Scolarite.Etudiants;
import tn.essatin.erp.model.Scolarite.Inscription;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.model.financier.EStatus;
import tn.essatin.erp.model.financier.ETypeModaliteTransaction;
import tn.essatin.erp.model.financier.ModaliteTransaction;
import tn.essatin.erp.model.financier.MotifAnnulationRejetModalite;
import tn.essatin.erp.payload.request.financier.AnnulerRejeterModaliteRequest;
import tn.essatin.erp.payload.response.MessageResponse;
import tn.essatin.erp.payload.response.TransactionAvecModalite;
import tn.essatin.erp.util.ApiInfo;
import tn.essatin.erp.util.DocumentGenerators.DechargeAnnulation;
import tn.essatin.erp.util.DocumentGenerators.RecuEtudiant;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/modalite/")
public class ModaliteRest {
    final ModaliteTransactionDao modaliteTransactionDao;
    final TransactionDao transactionDao;
    final MotifAnnulationRejetModaliteDao motifAnnulationRejetModaliteDao;
    final CompteDao compteDao;
    final EnregistrementDao enregistrementDao;
    final InscriptionDao inscriptionDao;
    final EtudiantsDao etudiantsDao;
    final MessageSource messageSource;


    public ModaliteRest(ModaliteTransactionDao modaliteTransactionDao, TransactionDao transactionDao,
                        MessageSource messageSource, MotifAnnulationRejetModaliteDao motifAnnulationRejetModaliteDao,
                        CompteDao compteDao,EnregistrementDao enregistrementDao,InscriptionDao inscriptionDao,
                        EtudiantsDao etudiantsDao) {
        this.modaliteTransactionDao = modaliteTransactionDao;
        this.transactionDao = transactionDao;
        this.motifAnnulationRejetModaliteDao = motifAnnulationRejetModaliteDao;
        this.compteDao = compteDao;
        this.enregistrementDao=enregistrementDao;
        this.inscriptionDao=inscriptionDao;
        this.etudiantsDao = etudiantsDao;
        this.messageSource = messageSource;
    }
    @GetMapping("/")
    public ResponseEntity<?> infoApi() {
        List<ApiInfo> infos = new ArrayList<>();
        List<MessageResponse> responses;
        ApiInfo info;
        /////////////////////
        responses = new ArrayList<>();
        info = new ApiInfo("/api/modalite/getallmodalite", "Get",
                "retourne un JSON avec la liste de tout les modalites de transaction dans la base",
                "/api/modalite/getallmodalite",
                "JSON text Message", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.modalite", null, Locale.FRENCH), 403));
        info = new ApiInfo("/api/modalite/getmodalite/{id}", "Get",
                "retourne un JSON avec la liste des modalites par leur id",
                "/api/employer/getemployer/3",
                "JSON text Message", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.modalite", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.dejaannule.modalite", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.notcheck.modalite", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.dejarejete.modalite", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.dejavalide.modalite", null, Locale.FRENCH), 403));

        info = new ApiInfo("/api/modalite/validercheque/{idModalite}", "Get",
                "retourne un JSON avec la liste des modalites par leur id",
                "/api/modalite/validercheque/6",
                "JSON text Message", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.modalite", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.dejaannule.modalite", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.notcheck.modalite", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.dejarejete.modalite", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.dejavalide.modalite", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.compteFinancier", null, Locale.FRENCH), 403));

        AnnulerRejeterModaliteRequest annulerRejeterModaliteRequest = new AnnulerRejeterModaliteRequest(2,2,"Le chéque va etre remplacé par une operation en espéce");
        info = new ApiInfo("/api/modalite/rejetecheque", "Post",
                "un JSON pour rejeter un chéque", annulerRejeterModaliteRequest,
                "JSON text Message", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.modalite", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.dejaannule.modalite", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.notcheck.modalite", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.dejarejete.modalite", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.dejavalide.modalite", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.compteFinancier", null, Locale.FRENCH), 403));

        AnnulerRejeterModaliteRequest annulerRejeterModaliteRequest1 = new AnnulerRejeterModaliteRequest(4,2,"");
                info = new ApiInfo("/api/modalite/annulermodalite", "Post",
                "un JSON pour annuler un modalité", annulerRejeterModaliteRequest1,
                "JSON text Message", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.modalite", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.niannulenirejete.modalite", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.motifannulationrejet", null, Locale.FRENCH), 403));

        info = new ApiInfo("/api/modalite/detailleannulationrejet/{idModalite}", "Get",
                "retourne un JSON avec les details d'annulation d'un modalité et le rejet d'un chéque",
                "/api/modalite/detailleannulationrejet/7",
                "JSON text Message", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.modalite", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.niannulenirejete.modalite", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.motifannulationrejet", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.etudiant", null, Locale.FRENCH), 403));

        info = new ApiInfo("/api/modalite/dechargeannulationetudiant/{idModalite}/{entete}", "Get",
                "retourne un decharge d'annulation d'un modalite pour un etudiant",
                "/api/modalite/dechargeannulationetudiant/26/yes",
                "JSON text Message", responses);
        infos.add(info);
        /////////////////////

        return new ResponseEntity<>(infos, HttpStatus.OK);

    }

    @GetMapping("/getallmodalite")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(modaliteTransactionDao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getmodalite/{id}")
    public ResponseEntity<?> getModaliteById(@PathVariable int id) {
        Optional<ModaliteTransaction> modaliteTransaction = modaliteTransactionDao.findById(id);
        if (modaliteTransaction.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.introuvable.modalite", null, Locale.FRENCH)), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(modaliteTransaction.get(), HttpStatus.OK);
    }

    @GetMapping("/validercheque/{idModalite}")
    public ResponseEntity<?> vliderChequeByIdModalite(@PathVariable int idModalite) {
        Optional<ModaliteTransaction> modaliteTransaction = modaliteTransactionDao.findById(idModalite);
        if (modaliteTransaction.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.introuvable.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        if (modaliteTransaction.get().getStatus().equals(EStatus.CANCELED)) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.dejaannule.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        if (!modaliteTransaction.get().getType().equals(ETypeModaliteTransaction.CHEQUE)) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.notcheck.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        if (modaliteTransaction.get().getStatus().equals(EStatus.REJECTED)) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.dejarejete.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        if (modaliteTransaction.get().getStatus().equals(EStatus.COMPLETE)) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.dejavalide.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        modaliteTransaction.get().setStatus(EStatus.COMPLETE);
        modaliteTransactionDao.save(modaliteTransaction.get());
        return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("message.modalite.complete.success", null, Locale.FRENCH), 200), HttpStatus.OK);
    }

    @PostMapping("/rejetecheque/")
    public ResponseEntity<?> rejeterChequeByIdModalite(@Valid @RequestBody AnnulerRejeterModaliteRequest annulerRejeterModaliteRequest) {
        Optional<ModaliteTransaction> modaliteTransaction = modaliteTransactionDao.findById(annulerRejeterModaliteRequest.getIdModalite());
        if (modaliteTransaction.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.introuvable.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        if (modaliteTransaction.get().getStatus().equals(EStatus.CANCELED)) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.dejaannule.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        if (!modaliteTransaction.get().getType().equals(ETypeModaliteTransaction.CHEQUE)) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.notcheck.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        if (modaliteTransaction.get().getStatus().equals(EStatus.REJECTED)) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.dejarejete.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        if (modaliteTransaction.get().getStatus().equals(EStatus.COMPLETE)) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.dejavalide.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        Optional<Compte> financier = compteDao.findById(annulerRejeterModaliteRequest.getIdCompteFinancier());
        if (financier.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.introuvable.compteFinancier", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        MotifAnnulationRejetModalite motifAnnulationRejetModalite = new MotifAnnulationRejetModalite(financier.get().getIdPersonne(), LocalDate.now(), modaliteTransaction.get(), annulerRejeterModaliteRequest.getMotif());
        modaliteTransaction.get().setStatus(EStatus.REJECTED);
        motifAnnulationRejetModaliteDao.save(motifAnnulationRejetModalite);
        modaliteTransactionDao.save(modaliteTransaction.get());
        return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("message.modalite.rejete.success", null, Locale.FRENCH), 200), HttpStatus.OK);
    }

    @PostMapping("/annulermodalite/")
    public ResponseEntity<?> annulerModaliteById(@Valid @RequestBody AnnulerRejeterModaliteRequest annulerRejeterModaliteRequest) {
        Optional<ModaliteTransaction> modaliteTransaction = modaliteTransactionDao.findById(annulerRejeterModaliteRequest.getIdModalite());
        if (modaliteTransaction.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.introuvable.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        if (modaliteTransaction.get().getStatus().equals(EStatus.CANCELED)) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.dejaannule.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        if (modaliteTransaction.get().getStatus().equals(EStatus.REJECTED)) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.dejarejete.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        Optional<Compte> financier = compteDao.findById(annulerRejeterModaliteRequest.getIdCompteFinancier());
        if (financier.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.introuvable.compteFinancier", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        MotifAnnulationRejetModalite motifAnnulationRejetModalite = new MotifAnnulationRejetModalite(financier.get().getIdPersonne(), LocalDate.now(), modaliteTransaction.get(), annulerRejeterModaliteRequest.getMotif());
        modaliteTransaction.get().setStatus(EStatus.CANCELED);
        motifAnnulationRejetModaliteDao.save(motifAnnulationRejetModalite);
        modaliteTransactionDao.save(modaliteTransaction.get());
        return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("message.modalite.annuler.success", null, Locale.FRENCH), 200), HttpStatus.OK);
    }

    @GetMapping("/detailleannulationrejet/{idModalite}")
    public ResponseEntity<?> detailleAnnulationRejet(@PathVariable int idModalite) {
        Optional<ModaliteTransaction> modaliteTransaction = modaliteTransactionDao.findById(idModalite);
        if (modaliteTransaction.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.introuvable.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        if (!modaliteTransaction.get().getStatus().equals(EStatus.CANCELED) && !modaliteTransaction.get().getStatus().equals(EStatus.REJECTED)) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.niannulenirejete.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        Optional<MotifAnnulationRejetModalite> motifAnnulationRejetModalite = motifAnnulationRejetModaliteDao.findByModaliteTransaction(modaliteTransaction.get());
        if (motifAnnulationRejetModalite.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.introuvable.motifannulationrejet", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(motifAnnulationRejetModalite.get(), HttpStatus.OK);
    }

    @GetMapping("/dechargeannulationetudiant/{idModalite}/{entete}")
    public ResponseEntity<?> DechargeEtudiantAnnulation(@PathVariable int idModalite,@PathVariable String entete) {
        boolean isEntet;
        isEntet = !entete.equals("no") && !entete.equals("0");
        Optional<ModaliteTransaction> modaliteTransaction = modaliteTransactionDao.findById(idModalite);
        if (modaliteTransaction.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.introuvable.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        if (!modaliteTransaction.get().getStatus().equals(EStatus.CANCELED) && !modaliteTransaction.get().getStatus().equals(EStatus.REJECTED)) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.niannulenirejete.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        Optional<MotifAnnulationRejetModalite> motifAnnulationRejetModalite = motifAnnulationRejetModaliteDao.findByModaliteTransaction(modaliteTransaction.get());
        if (motifAnnulationRejetModalite.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.introuvable.motifannulationrejet", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        Personne personneEtudiant = motifAnnulationRejetModalite.get().getModaliteTransaction().getTransaction().getClient();
        Session session = motifAnnulationRejetModalite.get().getModaliteTransaction().getTransaction().getSession();
        Optional<Etudiants> etudiants = etudiantsDao.findByIdPersonne(personneEtudiant);
        if(etudiants.isEmpty())
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.introuvable.etudiant", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        Inscription inscription = inscriptionDao.findTopByIdEtudiantOrderByDateDesc(etudiants.get());
        Enregistrement enregistrementEtudiant = enregistrementDao.findByIdInscriptionAndIdSession(inscription,session);
        ByteArrayOutputStream os = DechargeAnnulation.createDocEtudiant(motifAnnulationRejetModalite.get(),enregistrementEtudiant.getIdNiveau(),isEntet);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
        ByteArrayResource resource = new ByteArrayResource(os.toByteArray());
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @GetMapping("/dechargeannulationfinancier/{idModalite}/{entete}")
    public ResponseEntity<?> DechargeFinancierAnnulation(@PathVariable int idModalite,@PathVariable String entete) {
        boolean isEntet;
        isEntet = !entete.equals("no") && !entete.equals("0");
        Optional<ModaliteTransaction> modaliteTransaction = modaliteTransactionDao.findById(idModalite);
        if (modaliteTransaction.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.introuvable.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        if (!modaliteTransaction.get().getStatus().equals(EStatus.CANCELED) && !modaliteTransaction.get().getStatus().equals(EStatus.REJECTED)) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.niannulenirejete.modalite", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        Optional<MotifAnnulationRejetModalite> motifAnnulationRejetModalite = motifAnnulationRejetModaliteDao.findByModaliteTransaction(modaliteTransaction.get());
        if (motifAnnulationRejetModalite.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.introuvable.motifannulationrejet", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        }
        Personne personneEtudiant = motifAnnulationRejetModalite.get().getModaliteTransaction().getTransaction().getClient();
        Session session = motifAnnulationRejetModalite.get().getModaliteTransaction().getTransaction().getSession();
        Optional<Etudiants> etudiants = etudiantsDao.findByIdPersonne(personneEtudiant);
        if(etudiants.isEmpty())
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.introuvable.etudiant", null, Locale.FRENCH), 403), HttpStatus.FORBIDDEN);
        Inscription inscription = inscriptionDao.findTopByIdEtudiantOrderByDateDesc(etudiants.get());
        Enregistrement enregistrementEtudiant = enregistrementDao.findByIdInscriptionAndIdSession(inscription,session);
        ByteArrayOutputStream os = DechargeAnnulation.createDocFinancier(motifAnnulationRejetModalite.get(),enregistrementEtudiant.getIdNiveau(),isEntet);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
        ByteArrayResource resource = new ByteArrayResource(os.toByteArray());
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
    

}