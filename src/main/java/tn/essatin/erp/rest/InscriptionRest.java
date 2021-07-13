package tn.essatin.erp.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.*;
import tn.essatin.erp.model.*;
import tn.essatin.erp.payload.request.InscriptionRequest;
import tn.essatin.erp.payload.request.InscriptionWithIdPersonneRequest;
import tn.essatin.erp.payload.response.MessageResponse;
import tn.essatin.erp.util.RandomStringGenerator;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/inscription/")
public class InscriptionRest {

    @Autowired
    SessionDao sessionDao;
    @Autowired
    InscriptionDao inscriptionDao;
    @Autowired
    PersonneDao personneDao;
    @Autowired
    ContactEtudiantDao contactEtudiantDao;
    @Autowired
    DiplomeEtudiantDao diplomeEtudiantDao;
    @Autowired
    EnregistrementDao enregistrementDao;
    @Autowired
    TypeIdentificateurDao typeIdentificateurDao;
    @Autowired
    NationaliteDao nationaliteDao;
    @Autowired
    EtudiantsDao etudiantsDao;
    @Autowired
    EtatInscriptionDao etatInscriptionDao;
    @Autowired
    NiveauDao niveauDao;
    @Autowired
    CycleDao cycleDao;
    @Autowired
    ParcoursDao parcoursDao;
    @Autowired
    CompteDao compteDao;
    @Autowired
    RoleDao roleDao;


    @PostMapping("/nouveauetudiant")
    public ResponseEntity<?> nouveauEtudiant(@Valid @RequestBody InscriptionRequest inscriptionRequest) {
        Session session = sessionDao.findTopByOrderByIdSessionDesc();
        int numeroInscriptionInt = inscriptionDao.countAllByNumeroInscriptionEndsWith(session.getSession().substring(0, 4)) + 1;
        String numeroInscription = "" + numeroInscriptionInt + "/" + session.getSession().substring(0, 4);
        LocalDate today = LocalDate.now();
        String nom = inscriptionRequest.getNom();
        String prenom = inscriptionRequest.getPrenom();
        String mail = inscriptionRequest.getMail();
        String adresse = inscriptionRequest.getAdresse();
        String telephonne = inscriptionRequest.getTelephonne();
        LocalDate dateNaissance = inscriptionRequest.getDateNaissance();
        String lieuNaissance = inscriptionRequest.getLieuNaissance();
        TypeIdentificateur idTypeIdentificateur = typeIdentificateurDao.findById(inscriptionRequest.getIdTypeIdentificateur()).get();
        String ididentif = inscriptionRequest.getIdidentif();
        String sexe = inscriptionRequest.getSexe();
        Nationalite idNationalite = nationaliteDao.findById(inscriptionRequest.getIdNationalite()).get();

        List<ContactEtudiant> contacts = inscriptionRequest.getContactEtudiantList();
        List<DiplomeEtudiant> diplomes = inscriptionRequest.getDiplomeEtudiantList();

        Personne p = new Personne(nom, prenom, mail, adresse, telephonne, dateNaissance, lieuNaissance, idTypeIdentificateur, ididentif, sexe, idNationalite);
        personneDao.save(p);
        Etudiants e = new Etudiants(p);
        etudiantsDao.save(e);
        for (ContactEtudiant ce : contacts) {
            ce.setIdEtudiant(e);
            contactEtudiantDao.save(ce);
        }
        for(DiplomeEtudiant de : diplomes){
            de.setIdEtudiant(e);
            diplomeEtudiantDao.save(de);
        }
        Inscription i = new Inscription(e,numeroInscription,today,etatInscriptionDao.findById(2).get());
        inscriptionDao.save(i);
        Enregistrement enr = new Enregistrement(i,niveauDao.findById(inscriptionRequest.getNiveauxInscrit()).get(),session,today,0);
        enregistrementDao.save(enr);
        Compte c = new Compte(p.getNumeroIdentificateur(), RandomStringGenerator.generateRandomString(10),p);
        Set<Role> rl= new HashSet<Role>();
        rl.add(roleDao.findById(5).get());
        c.setRoles(rl);
        compteDao.save(c);
        return ResponseEntity.ok(new MessageResponse("votre compte est crée avec succée!!"));
    }

    @PostMapping("/nouveauetudiantwithidp")
    public ResponseEntity<?> nouveauEtudiantWithIdPersonne(@Valid @RequestBody InscriptionWithIdPersonneRequest inscriptionWithIdPersonneRequest) {
        Session session = sessionDao.findTopByOrderByIdSessionDesc();
        int numeroInscriptionInt = inscriptionDao.countAllByNumeroInscriptionEndsWith(session.getSession().substring(0, 4)) + 1;
        String numeroInscription = "" + numeroInscriptionInt + "/" + session.getSession().substring(0, 4);
        LocalDate today = LocalDate.now();

        Personne p = personneDao.findById(inscriptionWithIdPersonneRequest.getIdPersonne()).get();
        Etudiants e = etudiantsDao.findByIdPersonne(p).get();

        Inscription i = new Inscription(e,numeroInscription,today,etatInscriptionDao.findById(2).get());
        inscriptionDao.save(i);
        Enregistrement enr = new Enregistrement(i,niveauDao.findById(inscriptionWithIdPersonneRequest.getNiveauxInscrit()).get(),session,today,0);
        enregistrementDao.save(enr);
        Compte c = new Compte(p.getNumeroIdentificateur(), RandomStringGenerator.generateRandomString(10),p);
        Set<Role> rl= new HashSet<Role>();
        rl.add(roleDao.findById(5).get());
        c.setRoles(rl);
        compteDao.save(c);
        return ResponseEntity.ok(new MessageResponse("votre compte est crée avec succée!!"));
    }

}
