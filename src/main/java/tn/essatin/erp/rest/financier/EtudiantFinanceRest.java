package tn.essatin.erp.rest.financier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.SessionDao;
import tn.essatin.erp.dao.financier.DateDesactivationEtudiantsDao;
import tn.essatin.erp.dao.financier.ModaliteTransactionDao;
import tn.essatin.erp.dao.financier.TransactionDao;
import tn.essatin.erp.dao.scolarite.EnregistrementDao;
import tn.essatin.erp.dao.scolarite.EtatInscriptionDao;
import tn.essatin.erp.dao.scolarite.InscriptionDao;
import tn.essatin.erp.model.Scolarite.Enregistrement;
import tn.essatin.erp.model.Scolarite.Inscription;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.model.financier.DateDesactivationEtudiants;
import tn.essatin.erp.model.financier.ModaliteTransaction;
import tn.essatin.erp.model.financier.Transaction;
import tn.essatin.erp.payload.response.MessageResponse;
import tn.essatin.erp.payload.response.PrimitifResponse;
import tn.essatin.erp.payload.response.TransactionAvecModalite;
import tn.essatin.erp.util.ApiInfo;
import tn.essatin.erp.util.StudentDebt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    final DateDesactivationEtudiantsDao dateDesactivationEtudiantsDao;
    final EtatInscriptionDao etatInscriptionDao;

    public EtudiantFinanceRest(
            EnregistrementDao enregistrementDao, StudentDebt studentDebt,
            SessionDao sessionDao, TransactionDao transactionDao,
            ModaliteTransactionDao modaliteTransactionDao,
            InscriptionDao inscriptionDao, DateDesactivationEtudiantsDao dateDesactivationEtudiantsDao,
            EtatInscriptionDao etatInscriptionDao) {
        this.enregistrementDao = enregistrementDao;
        this.studentDebt = studentDebt;
        this.sessionDao = sessionDao;
        this.transactionDao = transactionDao;
        this.modaliteTransactionDao = modaliteTransactionDao;
        this.inscriptionDao = inscriptionDao;
        this.dateDesactivationEtudiantsDao = dateDesactivationEtudiantsDao;
        this.etatInscriptionDao = etatInscriptionDao;
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
        responses = new ArrayList<>();
        responses.add(new MessageResponse("Enregistrement introuvable!", 403));
        responses.add(new MessageResponse("Pas de transactions pour cet etudiant", 403));
        info = new ApiInfo("/api/etudiantfinance/getdetaillepayementbyidenregistrementnew/{idEnregistrement}", "Get",
                "retourne un JSON avec la liste des transactions éféctué par un étudiant (y compris les modalite de transaction impliqué; REMARQUE: les Modalité ont un champ transaction null pour evité d'affiché la transaction 2 fois)",
                "/api/etudiantfinance/getdetaillepayementbyidenregistrementnew/1",
                "une texte JSON avec une liste de transaction", responses);
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
        double resteAPayer = studentDebt.debt(enregistrement.get());
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
            if (studentDebt.debt(enregistrement) > 0) {
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
                    if (studentDebt.debt(enregistrement1) > 0) {
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
                    if (studentDebt.debt(enregistrement1) > 0) {
                        enregistrementList.add(enregistrement1);
                    }
            }
        }
        return new ResponseEntity<>(enregistrementList, HttpStatus.OK);
    }

    @GetMapping("/desactiveretudiantsparpourcentage")
    public ResponseEntity<?> desactiverEtudiantsParPourcentage() {
        LocalDate now = LocalDate.now();
        Session session = sessionDao.findTopByOrderByIdSessionDesc();
        LocalDate dateDesactivation;
        DateTimeFormatter dyf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String firstYear = session.getSession().substring(0, session.getSession().indexOf('-'));
        String lastYear = session.getSession().substring(session.getSession().indexOf('-') + 1);
        List<Enregistrement> enregistrementList = enregistrementDao.findAll();
        List<DateDesactivationEtudiants> dateDesactivationEtudiantsList = dateDesactivationEtudiantsDao.findAll();
        List<Enregistrement> etudiantDesactive = new ArrayList<>();

        for (Enregistrement enregistrement : enregistrementList) {
            if (enregistrement.getIdSession().getIdSession().equals(session.getIdSession())) {
                for (DateDesactivationEtudiants dateDesactivationEtudiants : dateDesactivationEtudiantsList) {
                    if (Integer.parseInt(dateDesactivationEtudiants.getMoisDesactivation()) <= 8) {
                        dateDesactivation = LocalDate.parse(dateDesactivationEtudiants.getJourDesactivation()
                                + "/" + dateDesactivationEtudiants.getMoisDesactivation() + "/" + firstYear, dyf);
                    } else {
                        dateDesactivation = LocalDate.parse(dateDesactivationEtudiants.getJourDesactivation() +
                                "/" + dateDesactivationEtudiants.getMoisDesactivation() + "/" + lastYear, dyf);
                    }
                    if (dateDesactivation.isBefore(now)) {
                        if (studentDebt.PayerEnPourcent(enregistrement) < dateDesactivationEtudiants.getPourcentagePayement()) {
                            if (enregistrement.getEtatFinanciere() == 1) {
                                enregistrement.setEtatFinanciere(0);
                                enregistrementDao.save(enregistrement);
                                etudiantDesactive.add(enregistrement);
                                if (etatInscriptionDao.findById(3).isPresent()) {
                                    enregistrement.getIdInscription().setIdEtatInscription(etatInscriptionDao.findById(3).get());
                                    inscriptionDao.save(enregistrement.getIdInscription());
                                }
                            }
                        }
                    }
                }
            } else {
                if (studentDebt.PayerEnPourcent(enregistrement) < 100) {
                    if (enregistrement.getEtatFinanciere() == 1) {
                        enregistrement.setEtatFinanciere(0);
                        enregistrementDao.save(enregistrement);
                        etudiantDesactive.add(enregistrement);
                        if (etatInscriptionDao.findById(3).isPresent()) {
                            enregistrement.getIdInscription().setIdEtatInscription(etatInscriptionDao.findById(3).get());
                            inscriptionDao.save(enregistrement.getIdInscription());
                        }
                    }
                }
            }
        }

        return new ResponseEntity<>(etudiantDesactive, HttpStatus.OK);
    }

    @GetMapping("/getdetaillepayementbyidenregistrementnew/{idEnregistrement}")
    public ResponseEntity<?> getDetailleDePayementByIdEnregistrementNew(@PathVariable int idEnregistrement) {
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
        List<TransactionAvecModalite> modaliteTransactionList = new ArrayList<>();
        for (Transaction transaction : transactions) {
            List<ModaliteTransaction> modaliteTransactionList1 =
                    modaliteTransactionDao.findModaliteTransactionByTransaction(transaction);
            modaliteTransactionList.add(new TransactionAvecModalite(transaction, modaliteTransactionList1));
        }
        return new ResponseEntity<>(modaliteTransactionList, HttpStatus.OK);
    }
}
