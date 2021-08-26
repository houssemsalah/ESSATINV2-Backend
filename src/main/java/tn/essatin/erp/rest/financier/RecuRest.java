package tn.essatin.erp.rest.financier;


import org.springframework.context.MessageSource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.PersonneDao;
import tn.essatin.erp.dao.SessionDao;
import tn.essatin.erp.dao.financier.ModaliteTransactionDao;
import tn.essatin.erp.dao.financier.TransactionDao;
import tn.essatin.erp.dao.scolarite.EnregistrementDao;
import tn.essatin.erp.dao.scolarite.EtudiantsDao;
import tn.essatin.erp.dao.scolarite.InscriptionDao;
import tn.essatin.erp.dao.scolarite.NiveauDao;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Scolarite.Enregistrement;
import tn.essatin.erp.model.Scolarite.Etudiants;
import tn.essatin.erp.model.Scolarite.Inscription;
import tn.essatin.erp.model.Scolarite.Niveau;
import tn.essatin.erp.model.financier.ModaliteTransaction;
import tn.essatin.erp.model.financier.Transaction;
import tn.essatin.erp.payload.response.MessageResponse;
import tn.essatin.erp.payload.response.TransactionAvecModalite;
import tn.essatin.erp.util.ApiInfo;
import tn.essatin.erp.util.DocumentGenerators.FeuilleDeDemandeDeStage;
import tn.essatin.erp.util.DocumentGenerators.RecuEtudiant;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/recu/")
public class RecuRest {

    final TransactionDao transactionDao;
    final PersonneDao personneDao;
    final EtudiantsDao etudiantsDao;
    final InscriptionDao inscriptionDao;
    final EnregistrementDao enregistrementDao;
    final NiveauDao niveauDao;
    final SessionDao sessionDao;
    final ModaliteTransactionDao modaliteTransactionDao;
    final MessageSource messageSource;

    public RecuRest(PersonneDao personneDao, EtudiantsDao etudiantsDao, InscriptionDao inscriptionDao,
                    EnregistrementDao enregistrementDao, NiveauDao niveauDao, SessionDao sessionDao,
                    TransactionDao transactionDao,ModaliteTransactionDao modaliteTransactionDa, MessageSource messageSource) {
        this.personneDao = personneDao;
        this.etudiantsDao = etudiantsDao;
        this.inscriptionDao = inscriptionDao;
        this.enregistrementDao = enregistrementDao;
        this.niveauDao = niveauDao;
        this.sessionDao = sessionDao;
        this.transactionDao = transactionDao;
        this.modaliteTransactionDao = modaliteTransactionDa;
        this.messageSource = messageSource;
    }

    @GetMapping("/")
    public ResponseEntity<?> infoApi() {
        List<ApiInfo> infos = new ArrayList<>();
        List<MessageResponse> responses;
        ApiInfo info;

        /////////////////////

        responses = new ArrayList<>();
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.transaction", null, Locale.FRENCH), 403));
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.etudiant", null, Locale.FRENCH), 403));

        info = new ApiInfo("/api/recu/pdfbyidtransaction/{id}", "Get",
                "retourne un JSON avec la liste de tout les PrixNiveauParSession dans la base",
                "/api/recu/pdfbyidtransaction/5}",
                "JSON text Message", responses);
        infos.add(info);
        /////////////////////
        return new ResponseEntity<>(infos, HttpStatus.OK);
    }

        @GetMapping("/pdfbyidtransaction/{id}")
    public ResponseEntity<?>pdfByIdTransaction(@PathVariable int id){
        Optional<Transaction> transaction = transactionDao.findById(id);
        if(transaction.isEmpty())
            return  new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.introuvable.transaction",null, Locale.FRENCH),403), HttpStatus.FORBIDDEN);
        Personne personne = transaction.get().getClient();
        Optional<Etudiants> etudiants = etudiantsDao.findByIdPersonne(personne);
        if(etudiants.isEmpty())
            return  new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.introuvable.etudiant",null, Locale.FRENCH),403), HttpStatus.FORBIDDEN);
        Inscription inscription = inscriptionDao.findTopByIdEtudiantOrderByDateDesc(etudiants.get());
        Enregistrement enregistrement = enregistrementDao.findTopByIdInscriptionOrderByIdEnregistrementDesc(inscription);
        Niveau niveau = enregistrement.getIdNiveau();
        List<ModaliteTransaction> modaliteTransactionList = modaliteTransactionDao.findModaliteTransactionByTransaction(transaction.get());
        ByteArrayOutputStream os = RecuEtudiant.createDoc(new TransactionAvecModalite(transaction.get(),modaliteTransactionList),niveau);
        os = RecuEtudiant.createDoc(os);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
        ByteArrayResource resource = new ByteArrayResource(os.toByteArray());
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
