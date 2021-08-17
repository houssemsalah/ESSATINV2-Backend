package tn.essatin.erp.rest.financier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.SessionDao;
import tn.essatin.erp.dao.financier.ModaliteTransactionDao;
import tn.essatin.erp.dao.financier.TransactionDao;
import tn.essatin.erp.dao.scolarite.EnregistrementDao;
import tn.essatin.erp.dao.scolarite.InscriptionDao;
import tn.essatin.erp.model.Scolarite.Enregistrement;
import tn.essatin.erp.model.Scolarite.Inscription;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.model.financier.ModaliteTransaction;
import tn.essatin.erp.model.financier.Transaction;
import tn.essatin.erp.payload.response.MessageResponse;
import tn.essatin.erp.payload.response.PrimitifResponse;
import tn.essatin.erp.util.ApiInfo;
import tn.essatin.erp.util.StudentDebt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/etudiantfinance/")
public class EtudiantFinanceRest {

    final EnregistrementDao enregistrementDao;
    final StudentDebt studentDebt;
    final SessionDao sessionDao;
    final TransactionDao transactionDao;
    final ModaliteTransactionDao modaliteTransactionDao;
    final InscriptionDao inscriptionDao;

    public EtudiantFinanceRest(
            EnregistrementDao enregistrementDao, StudentDebt studentDebt,
            SessionDao sessionDao, TransactionDao transactionDao,
            ModaliteTransactionDao modaliteTransactionDao,
            InscriptionDao inscriptionDao) {
        this.enregistrementDao = enregistrementDao;
        this.studentDebt = studentDebt;
        this.sessionDao = sessionDao;
        this.transactionDao = transactionDao;
        this.modaliteTransactionDao = modaliteTransactionDao;
        this.inscriptionDao = inscriptionDao;
    }

    @GetMapping("/")
    public ResponseEntity<?> infoApi() {
        List<ApiInfo> infos = new ArrayList<>();
        List<MessageResponse> responses;
        ApiInfo info;

        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse("Enregistrement introuvable!", 403));
        info = new ApiInfo("/api/etudiantfinance/restapayer/{idEnregistrement}", "Get",
                "retourne un JSON avec la valeur qui reste a payer en Dinar Tunisien",
                "/api/etudiantfinance/restapayer/1",
                "texte JSON avec le champ valeur qui represente la valeur du reste a payer", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse("Session Introuvable", 403));
        info = new ApiInfo("/api/etudiantfinance/getenregistrementavecresrbyidsession/{idSession}", "Get",
                "retourne un JSON avec la liste des enregistrement(Etudiants) avec reste a payer dans une session",
                "/api/etudiantfinance/getenregistrementavecresrbyidsession/5",
                "une texte JSON avec une liste d'enregistrements", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse("Enregistrement introuvable!", 403));
        responses.add(new MessageResponse("Pas de transactions pour cet etudiant", 403));
        info = new ApiInfo("/api/etudiantfinance/getdetaillepayementbyidenregistrement/{idEnregistrement}", "Get",
                "retourne un JSON avec la liste des modalité de transacton éféctué par un étudiant (y compris les id transaction impliqué)",
                "/api/etudiantfinance/getdetaillepayementbyidenregistrement/1",
                "une texte JSON avec une liste de ModaliteTransactions", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse("Transaction introuvable", 403));

        info = new ApiInfo("/api/etudiantfinance/getdetaillepayementbyidtransaction/{idTransaction}", "Get",
                "retourne un JSON avec la liste des modalité de transacton éféctué pour une transaction ",
                "/api/etudiantfinance/getdetaillepayementbyidtransaction/23",
                "une texte JSON avec une liste de ModaliteTransactions", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse("Enregistrement introuvable!", 403));
        info = new ApiInfo("/api/etudiantfinance/pourcentagepayer/{idEnregistrement}", "Get",
                "retourne un JSON avec la valeur qui a deja été payer en %",
                "/api/etudiantfinance/pourcentagepayer/1",
                "texte JSON avec le champ valeur qui represente la valeur en % de la valeur deja payer", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse("Enregistrement introuvable!", 403));
        info = new ApiInfo("/api/etudiantfinance/adesimpayerdansuneautresession/{idEnregistrement}", "Get",
                "retourne un JSON avec la valeur true ou false pour savoir si l'enregistrement correspond a un etudiant avec des impayer",
                "/api/etudiantfinance/adesimpayerdansuneautresession/1",
                "texte JSON avec le champ valeur qui represente la valeur true si l'etudiant a des impayer", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse("Enregistrement introuvable!", 403));
        info = new ApiInfo("/api/etudiantfinance/listdesenregistrementsavecimpayerdansautresession/{idEnregistrement}", "Get",
                "retourne un JSON avec la liste des enregistrement(differante de l'enregistrement en cour) avec reste a payer dans d'autre session",
                "/api/etudiantfinance/listdesenregistrementsavecimpayerdansautresession/1",
                "une texte JSON avec une liste d'enregistrements", responses);
        infos.add(info);
        /////////////////////

        return new ResponseEntity<>(infos, HttpStatus.OK);
    }

    @GetMapping("/restapayer/{idEnregistrement}")
    public ResponseEntity<?> restAPayer(@PathVariable int idEnregistrement) {
        Optional<Enregistrement> enregistrement = enregistrementDao.findById(idEnregistrement);
        if (enregistrement.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Enregistrement introuvable!", 403), HttpStatus.FORBIDDEN);
        }
        double resteAPayer = studentDebt.debt(enregistrement.get().getIdInscription().getIdEtudiant(),
                enregistrement.get().getIdSession());
        return new ResponseEntity<>(new PrimitifResponse("restAPayer", resteAPayer)
                , HttpStatus.OK);
    }

    @GetMapping("/getenregistrementavecresrbyidsession/{idSession}")
    public ResponseEntity<?> getEnregistrementAvecRestByIdSession(@PathVariable int idSession) {
        Optional<Session> session = sessionDao.findById(idSession);
        if (session.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Session Introuvable", 403), HttpStatus.FORBIDDEN);
        }
        List<Enregistrement> enregistrementList = enregistrementDao.findByIdSession(session.get());
        List<Enregistrement> enregistrementListAvecRest = new ArrayList<>();
        for (Enregistrement enregistrement : enregistrementList) {
            if (studentDebt.debt(enregistrement.getIdInscription().getIdEtudiant(), enregistrement.getIdSession()) > 0) {
                enregistrementListAvecRest.add(enregistrement);
            }
        }
        return new ResponseEntity<>(enregistrementListAvecRest, HttpStatus.OK);
    }

    @GetMapping("/getdetaillepayementbyidenregistrement/{idEnregistrement}")
    public ResponseEntity<?> getDetailleDePayementByIdEnregistrement(@PathVariable int idEnregistrement) {
        Optional<Enregistrement> enregistrement = enregistrementDao.findById(idEnregistrement);
        if (enregistrement.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Enregistrement introuvable!", 403), HttpStatus.FORBIDDEN);
        }
        Collection<Transaction> transactions = transactionDao.findAllByClientAndSession(
                enregistrement.get().getIdInscription().getIdEtudiant().getIdPersonne(),
                enregistrement.get().getIdSession());
        if (transactions.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Pas de transactions pour cet etudiant", 403), HttpStatus.FORBIDDEN);
        }
        List<ModaliteTransaction> modaliteTransactionList = new ArrayList<>();
        for (Transaction transaction : transactions) {
            List<ModaliteTransaction> modaliteTransactionList1 =
                    modaliteTransactionDao.findModaliteTransactionByTransaction(transaction);
            if (!modaliteTransactionList1.isEmpty())
                modaliteTransactionList.addAll(modaliteTransactionList1);
        }
        return new ResponseEntity<>(modaliteTransactionList, HttpStatus.OK);
    }

    @GetMapping("/getdetaillepayementbyidtransaction/{idTransaction}")
    public ResponseEntity<?> getDetailleDePayementByIdTransaction(@PathVariable int idTransaction) {
        Optional<Transaction> transaction = transactionDao.findById(idTransaction);
        if (transaction.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Transaction introuvable", 403), HttpStatus.FORBIDDEN);
        }
        List<ModaliteTransaction> modaliteTransactionList =
                modaliteTransactionDao.findModaliteTransactionByTransaction(transaction.get());
        return new ResponseEntity<>(modaliteTransactionList, HttpStatus.OK);
    }

    @GetMapping("/pourcentagepayer/{idEnregistrement}")
    public ResponseEntity<?> pourcentagePayer(@PathVariable int idEnregistrement) {
        Optional<Enregistrement> enregistrement = enregistrementDao.findById(idEnregistrement);
        if (enregistrement.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Enregistrement introuvable!", 403), HttpStatus.FORBIDDEN);
        }
        double pourcentagePayer = studentDebt.PayerEnPourcent(enregistrement.get());
        return new ResponseEntity<>(new PrimitifResponse("PourcentagePayer", pourcentagePayer)
                , HttpStatus.OK);
    }

    @GetMapping("/adesimpayerdansuneautresession/{idEnregistrement}")
    public ResponseEntity<?> aDesImpayerDansUneAutreSession(@PathVariable int idEnregistrement) {
        Optional<Enregistrement> enregistrement = enregistrementDao.findById(idEnregistrement);
        if (enregistrement.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Enregistrement introuvable!", 403), HttpStatus.FORBIDDEN);
        }
        List<Inscription> inscriptionList = inscriptionDao.findAllByIdEtudiant(
                enregistrement.get().getIdInscription().getIdEtudiant());
        for (Inscription inscription : inscriptionList) {
            List<Enregistrement> enregistrementList1 = enregistrementDao.findByIdInscription(inscription);
            for (Enregistrement enregistrement1 : enregistrementList1) {
                if (!enregistrement1.getIdEnregistrement().equals(enregistrement.get().getIdEnregistrement()))
                    if (studentDebt.debt(enregistrement1.getIdInscription().getIdEtudiant(),
                            enregistrement1.getIdSession()) > 0) {
                        return new ResponseEntity<>(
                                new PrimitifResponse("aDesImpayerDansUneAutreSession", true)
                                , HttpStatus.OK);
                    }
            }
        }
        return new ResponseEntity<>(new PrimitifResponse("aDesImpayerDansUneAutreSession", false)
                , HttpStatus.OK);
    }

    @GetMapping("/listdesenregistrementsavecimpayerdansautresession/{idEnregistrement}")
    public ResponseEntity<?> listeDesEnregistrementsAvecImpayerDansAutreSession(@PathVariable int idEnregistrement) {
        Optional<Enregistrement> enregistrement = enregistrementDao.findById(idEnregistrement);
        if (enregistrement.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Enregistrement introuvable!", 403), HttpStatus.FORBIDDEN);
        }
        List<Inscription> inscriptionList = inscriptionDao.findAllByIdEtudiant(
                enregistrement.get().getIdInscription().getIdEtudiant());
        List<Enregistrement> enregistrementList = new ArrayList<>();
        for (Inscription inscription : inscriptionList) {
            List<Enregistrement> enregistrementList1 = enregistrementDao.findByIdInscription(inscription);
            for (Enregistrement enregistrement1 : enregistrementList1) {
                if (!enregistrement1.getIdEnregistrement().equals(enregistrement.get().getIdEnregistrement()))
                    if (studentDebt.debt(enregistrement1.getIdInscription().getIdEtudiant(),
                            enregistrement1.getIdSession()) > 0) {
                        enregistrementList.add(enregistrement1);
                    }
            }
        }
        return new ResponseEntity<>(enregistrementList, HttpStatus.OK);
    }
}
