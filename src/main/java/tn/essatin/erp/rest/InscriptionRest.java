package tn.essatin.erp.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.*;
import tn.essatin.erp.model.*;
import tn.essatin.erp.payload.request.GetByIdRequest;
import tn.essatin.erp.payload.request.InscriptionRequest;
import tn.essatin.erp.payload.request.InscriptionWithIdPersonneRequest;
import tn.essatin.erp.payload.response.CombinedResponse;
import tn.essatin.erp.payload.response.MessageResponse;
import tn.essatin.erp.util.RandomStringGenerator;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/inscription/")
public class InscriptionRest {

    final SessionDao sessionDao;
    final InscriptionDao inscriptionDao;
    final PersonneDao personneDao;
    final ContactEtudiantDao contactEtudiantDao;
    final DiplomeEtudiantDao diplomeEtudiantDao;
    final EnregistrementDao enregistrementDao;
    final TypeIdentificateurDao typeIdentificateurDao;
    final NationaliteDao nationaliteDao;
    final EtudiantsDao etudiantsDao;
    final EtatInscriptionDao etatInscriptionDao;
    final NiveauDao niveauDao;
    final CycleDao cycleDao;
    final ParcoursDao parcoursDao;
    final CompteDao compteDao;
    final RoleDao roleDao;

    @Autowired
    public InscriptionRest(InscriptionDao inscriptionDao, SessionDao sessionDao, PersonneDao personneDao,
                           ContactEtudiantDao contactEtudiantDao, CompteDao compteDao,
                           DiplomeEtudiantDao diplomeEtudiantDao, EnregistrementDao enregistrementDao,
                           TypeIdentificateurDao typeIdentificateurDao, NationaliteDao nationaliteDao,
                           EtudiantsDao etudiantsDao, EtatInscriptionDao etatInscriptionDao, ParcoursDao parcoursDao,
                           RoleDao roleDao, NiveauDao niveauDao, CycleDao cycleDao) {
        this.inscriptionDao = inscriptionDao;
        this.sessionDao = sessionDao;
        this.personneDao = personneDao;
        this.contactEtudiantDao = contactEtudiantDao;
        this.compteDao = compteDao;
        this.diplomeEtudiantDao = diplomeEtudiantDao;
        this.enregistrementDao = enregistrementDao;
        this.typeIdentificateurDao = typeIdentificateurDao;
        this.nationaliteDao = nationaliteDao;
        this.etudiantsDao = etudiantsDao;
        this.etatInscriptionDao = etatInscriptionDao;
        this.parcoursDao = parcoursDao;
        this.roleDao = roleDao;
        this.niveauDao = niveauDao;
        this.cycleDao = cycleDao;
    }

    @PostMapping("/nouveauetudiant")
    public ResponseEntity<?> nouveauEtudiant(@Valid @RequestBody InscriptionRequest inscriptionRequest) {
        Etudiants e;
        Session session;
        Role studentRole;
        Personne p;
        if (
                typeIdentificateurDao.findById(inscriptionRequest.getIdTypeIdentificateur()).isPresent() &&
                        nationaliteDao.findById(inscriptionRequest.getIdNationalite()).isPresent() &&
                        etatInscriptionDao.findById(2).isPresent() &&
                        niveauDao.findById(inscriptionRequest.getNiveauxInscrit()).isPresent() &&
                        roleDao.findById(5).isPresent()
        ) {
            studentRole = roleDao.findById(5).get();
            session = sessionDao.findTopByOrderByIdSessionDesc();
            String nom = inscriptionRequest.getNom();
            String prenom = inscriptionRequest.getPrenom();
            String mail = inscriptionRequest.getMail();
            String adresse = inscriptionRequest.getAdresse();
            String telephonne = inscriptionRequest.getTelephonne();
            LocalDate dateNaissance = inscriptionRequest.getDateNaissance();
            String lieuNaissance = inscriptionRequest.getLieuNaissance();
            TypeIdentificateur idTypeIdentificateur = typeIdentificateurDao.findById(inscriptionRequest
                    .getIdTypeIdentificateur()).get();
            String ididentif = inscriptionRequest.getIdidentif();
            String sexe = inscriptionRequest.getSexe();
            Nationalite idNationalite = nationaliteDao.findById(inscriptionRequest.getIdNationalite()).get();
            List<ContactEtudiant> contacts = inscriptionRequest.getContactEtudiantList();
            List<DiplomeEtudiant> diplomes = inscriptionRequest.getDiplomeEtudiantList();
            p = new Personne(nom, prenom, mail, adresse, telephonne, dateNaissance,
                    lieuNaissance, idTypeIdentificateur, ididentif, sexe, idNationalite);
            personneDao.save(p);
            e = new Etudiants(p);
            etudiantsDao.save(e);
            for (ContactEtudiant ce : contacts) {
                ce.setIdEtudiant(e);
                contactEtudiantDao.save(ce);
            }
            for (DiplomeEtudiant de : diplomes) {
                de.setIdEtudiant(e);
                diplomeEtudiantDao.save(de);
            }
            inscriptionCompteEtEnregistrement(
                    e, session, niveauDao.findById(inscriptionRequest.getNiveauxInscrit()).get(), p,
                    studentRole, etatInscriptionDao.findById(2).get());
            return new ResponseEntity<>(new MessageResponse("votre compte est crée avec succée!!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    new MessageResponse("Probleme de ressource", 403), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/nouveauetudiantwithidp")
    public ResponseEntity<?> nouveauEtudiantWithIdPersonne(
            @Valid @RequestBody InscriptionWithIdPersonneRequest inscriptionWithIdPersonneRequest) {
        Etudiants e;
        Compte c;
        Session session;
        Role studentRole;
        Personne p;
        Set<Role> rl;
        String numeroInscription;
        int numeroInscriptionInt;
        LocalDate today = LocalDate.now();
        Inscription i;
        Enregistrement enr;
        if (
                etatInscriptionDao.findById(2).isPresent()
                        && niveauDao.findById(inscriptionWithIdPersonneRequest.getNiveauxInscrit()).isPresent()
                        && roleDao.findById(5).isPresent()
                        && personneDao.findById(inscriptionWithIdPersonneRequest.getIdPersonne()).isPresent()
        ) {
            studentRole = roleDao.findById(5).get();
            session = sessionDao.findTopByOrderByIdSessionDesc();
            numeroInscriptionInt = inscriptionDao.countAllByNumeroInscriptionEndsWith(
                    session.getSession().substring(0, 4)) + 1;
            numeroInscription = "" + numeroInscriptionInt + "/" + session.getSession().substring(0, 4);
            p = personneDao.findById(inscriptionWithIdPersonneRequest.getIdPersonne()).get();
            enr = estDejaInscritCetteSession(p);
            if (enr == null) {
                p.setAdresse(inscriptionWithIdPersonneRequest.getAdresse());
                p.setMail(inscriptionWithIdPersonneRequest.getMail());
                p.setTel(inscriptionWithIdPersonneRequest.getTel());
                personneDao.save(p);
                if (etudiantsDao.findByIdPersonne(p).isPresent()) {
                    e = etudiantsDao.findByIdPersonne(p).get();
                    i = new Inscription(e, numeroInscription, today, etatInscriptionDao.findById(2).get());
                    inscriptionDao.save(i);
                    enr = new Enregistrement(i, niveauDao.findById(
                            inscriptionWithIdPersonneRequest.getNiveauxInscrit()).get(), session, today, 0);
                    enregistrementDao.save(enr);
                    if (compteDao.findByIdPersonne(p).isPresent()) {
                        c = compteDao.findByIdPersonne(p).get();
                        addStudentRoleIfNotPresent(c, studentRole);
                    } else {
                        c = new Compte(p.getNumeroIdentificateur(), RandomStringGenerator.generateRandomString(10), p);
                        rl = new HashSet<>();
                        rl.add(studentRole);
                        c.setRoles(rl);
                    }
                    compteDao.save(c);
                } else { //personne mais pas etudiant
                    e = new Etudiants(p);
                    etudiantsDao.save(e);
                    inscriptionCompteEtEnregistrement(
                            e, session, niveauDao.findById(inscriptionWithIdPersonneRequest.getNiveauxInscrit()).get(),
                            p, studentRole, etatInscriptionDao.findById(2).get());
                }
                return new ResponseEntity<>(new MessageResponse("Inscription effectué avec succée!!"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(
                        new CombinedResponse(new MessageResponse("l'etudiant est deja inscrit cet année", 403),
                                "Enregistrement", enr), HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(
                    new MessageResponse("Probleme de ressource", 403), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/estdejainscritcettesessionbyidpersonne")
    public ResponseEntity<?> estDejaIscritCetteSessionByIdPersonne(@Valid @RequestBody GetByIdRequest getByIdRequest) {
        Personne p;
        Enregistrement en;
        if (personneDao.findById(getByIdRequest.getId()).isPresent()) {
            p = personneDao.findById(getByIdRequest.getId()).get();
            en = estDejaInscritCetteSession(p);
            if (en != null) {
                return new ResponseEntity<>(en, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(new MessageResponse("Ressource Indisponible", 403), HttpStatus.FORBIDDEN);
    }

    private Enregistrement estDejaInscritCetteSession(Personne p) {
        Etudiants e;
        Inscription i;
        Enregistrement en = null;
        Session session = sessionDao.findTopByOrderByIdSessionDesc();
        if (etudiantsDao.findByIdPersonne(p).isPresent()) {
            e = etudiantsDao.findByIdPersonne(p).get();
            i = inscriptionDao.findTopByIdEtudiantOrderByDateDesc(e);
            en = enregistrementDao.findByIdInscriptionAndIdSession(i, session);
        }
        return en;
    }

    private void inscriptionCompteEtEnregistrement(Etudiants e, Session session, Niveau niveauxInscrit, Personne p, Role studentRole, EtatInscription etatInscription) {
        int numeroInscriptionInt = inscriptionDao.countAllByNumeroInscriptionEndsWith(
                session.getSession().substring(0, 4)) + 1;
        String numeroInscription = "" + numeroInscriptionInt + "/" + session.getSession().substring(0, 4);
        LocalDate today = LocalDate.now();
        Inscription i = new Inscription(e, numeroInscription, today, etatInscription);
        inscriptionDao.save(i);
        Enregistrement enr = new Enregistrement(i, niveauxInscrit, session, today, 0);
        enregistrementDao.save(enr);
        Compte c;
        Set<Role> rl;
        if (compteDao.findByIdPersonne(p).isPresent()) {
            c = compteDao.findByIdPersonne(p).get();
            addStudentRoleIfNotPresent(c, studentRole);
        } else {
            c = new Compte(p.getNumeroIdentificateur(), RandomStringGenerator.generateRandomString(10), p);
            rl = new HashSet<>();
            rl.add(studentRole);
            c.setRoles(rl);
        }
        compteDao.save(c);
    }

    private void addStudentRoleIfNotPresent(Compte compte, Role studentRole) {
        Set<Role> rl;
        rl = compte.getRoles();
        boolean isStudent = false;
        for (Role r : rl) {
            if (r.getId() == 5) {
                isStudent = true;
                break;
            }
        }
        if (!isStudent) {
            rl.add(studentRole);
            compte.setRoles(rl);
        }
    }
}
