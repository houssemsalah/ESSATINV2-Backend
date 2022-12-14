package tn.essatin.erp.rest.scolarite;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.*;
import tn.essatin.erp.dao.scolarite.*;
import tn.essatin.erp.model.*;
import tn.essatin.erp.model.Scolarite.*;
import tn.essatin.erp.payload.request.GetByIdRequest;
import tn.essatin.erp.payload.request.scolarite.*;
import tn.essatin.erp.payload.response.CombinedResponse;
import tn.essatin.erp.payload.response.MessageResponse;
import tn.essatin.erp.util.RandomStringGenerator;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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

    @PostMapping("/preinscription")
    public ResponseEntity<?> nouveauEtudiantPreInscrit(@Valid @RequestBody PreInscriptionRequest preInscriptionRequest) {
        Etudiants e;
        Session session;
        Role studentRole;
        Personne p;
        Optional<EtatInscription> etatInscrit = etatInscriptionDao.findById(2);
        if (typeIdentificateurDao.findById(preInscriptionRequest.getIdTypeIdentificateur()).isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Probleme de ressource", 403), HttpStatus.FORBIDDEN);
        }
        if (etatInscrit.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Probleme de ressource", 403), HttpStatus.FORBIDDEN);
        }
        Optional<Niveau> niveau = niveauDao.findById(preInscriptionRequest.getNiveauxInscrit());
        if (niveau.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Probleme de ressource", 403), HttpStatus.FORBIDDEN);
        }
        if (roleDao.findById(5).isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Probleme de ressource", 403), HttpStatus.FORBIDDEN);
        }
        Optional<Personne>personne=personneDao.findByNumeroIdentificateur(preInscriptionRequest.getIdidentif());
        if(personne.isPresent()){
            return new ResponseEntity<>(
                    new MessageResponse("etudiant deja inscrit", 403), HttpStatus.FORBIDDEN);
        }
        studentRole = roleDao.findById(5).get();
        session = sessionDao.findTopByOrderByIdSessionDesc();
        String nom = preInscriptionRequest.getNom();
        String prenom = preInscriptionRequest.getPrenom();
        TypeIdentificateur idTypeIdentificateur = typeIdentificateurDao.findById(preInscriptionRequest
                .getIdTypeIdentificateur()).get();
        String ididentif = preInscriptionRequest.getIdidentif();

        p = new Personne(nom, prenom, null, null, null, null,
                null, idTypeIdentificateur, ididentif, null, null);
        personneDao.save(p);
        e = new Etudiants(p);
        etudiantsDao.save(e);
        inscriptionCompteEtEnregistrement(
                e, session, niveau.get(), p,
                studentRole, etatInscrit.get());
        return new ResponseEntity<>(new MessageResponse("Succ??e d'inscription!!"), HttpStatus.OK);
    }

    @PostMapping("/nouveauetudiant")
    public ResponseEntity<?> nouveauEtudiant(@Valid @RequestBody InscriptionRequest inscriptionRequest) {
        Etudiants e;
        Session session;
        Role studentRole;
        Personne p;
        if (typeIdentificateurDao.findById(inscriptionRequest.getIdTypeIdentificateur()).isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Probleme de ressource", 403), HttpStatus.FORBIDDEN);
        }
        if (nationaliteDao.findById(inscriptionRequest.getIdNationalite()).isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Probleme de ressource", 403), HttpStatus.FORBIDDEN);
        }
        if (etatInscriptionDao.findById(2).isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Probleme de ressource", 403), HttpStatus.FORBIDDEN);
        }
        if (niveauDao.findById(inscriptionRequest.getNiveauxInscrit()).isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Probleme de ressource", 403), HttpStatus.FORBIDDEN);
        }
        if (roleDao.findById(5).isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Probleme de ressource", 403), HttpStatus.FORBIDDEN);
        }
        Optional<Personne>personne=personneDao.findByNumeroIdentificateur(inscriptionRequest.getIdidentif());
        if(personne.isPresent()){
            return new ResponseEntity<>(
                    new MessageResponse("etudiant deja inscrit", 403), HttpStatus.FORBIDDEN);
        }

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
        return new ResponseEntity<>(new MessageResponse("Succ??e d'inscription!!"), HttpStatus.OK);

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
                return new ResponseEntity<>(new MessageResponse("Inscription effectu?? avec succ??e!!"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(
                        new CombinedResponse(new MessageResponse("l'etudiant est deja inscrit cet ann??e", 403),
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
                return new ResponseEntity<>(new CombinedResponse(new MessageResponse("true", 1), "Enregistrement", en), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new MessageResponse("false", 0), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new MessageResponse("Ressource Indisponible", 403), HttpStatus.FORBIDDEN);
    }

    @PostMapping("/modifier")
    public ResponseEntity<?> modifierInscription(@Valid @RequestBody ModifierInscriptionRequest modifierInscriptionRequest) {

        if (
                inscriptionDao.findById(modifierInscriptionRequest.getIdInscription()).isPresent()
                        && niveauDao.findById(modifierInscriptionRequest.getIdNiveaux()).isPresent()
        ) {
            Inscription ins = inscriptionDao.findById(modifierInscriptionRequest.getIdInscription()).get();
            Niveau niv = niveauDao.findById(modifierInscriptionRequest.getIdNiveaux()).get();
            List<Enregistrement> len = enregistrementDao.findByIdInscription(ins);
            if (len.size() > 1) {
                return new ResponseEntity<>(
                        new MessageResponse("Etudiant n'est plus a sa premiere ann??e a l'??cole." +
                                " Impossible de modifier son inscription", 403),
                        HttpStatus.FORBIDDEN);
            } else if (len.size() == 0) {
                return new ResponseEntity<>(new MessageResponse("Ressource Indisponible", 403),
                        HttpStatus.FORBIDDEN);
            } else if (!len.get(0).getIdSession().getIdSession().equals(sessionDao.findTopByOrderByIdSessionDesc().getIdSession())) {
                return new ResponseEntity<>(new MessageResponse("l'inscrit ne date pas de cette session, il ne peut plus etre modifier!", 403),
                        HttpStatus.FORBIDDEN);
            } else {
                Enregistrement e = len.get(0);
                e.setIdNiveau(niv);
                enregistrementDao.save(e);
                return new ResponseEntity<>(new MessageResponse("Inscription modifer avec succ??e!!"), HttpStatus.OK);
            }
        } else
            return new ResponseEntity<>(
                    new MessageResponse("Ressource Indisponible", 403), HttpStatus.FORBIDDEN);
    }


    @PostMapping("/fusionner")
    public ResponseEntity<?> fusionnerEtudiant(@Valid @RequestBody FusionnerRequest fusionnerRequest) {
        if (
                personneDao.findById(fusionnerRequest.getIdOldPersonne()).isPresent()
                        && personneDao.findById(fusionnerRequest.getIdNewPersonne()).isPresent()
        ) {
            //r??cuperer les personnes
            Personne oldPersonne = personneDao.findById(fusionnerRequest.getIdOldPersonne()).get();
            Personne newPersonne = personneDao.findById(fusionnerRequest.getIdNewPersonne()).get();
            if (
                    etudiantsDao.findByIdPersonne(newPersonne).isPresent()
                            && etudiantsDao.findByIdPersonne(oldPersonne).isPresent()
            ) {
                //r??cup??rer les ??tudiants
                Etudiants newEtudiant = etudiantsDao.findByIdPersonne(newPersonne).get();
                Etudiants oldEtudiant = etudiantsDao.findByIdPersonne(oldPersonne).get();

                //r??cuperer la nouvelle inscription
                Inscription newInscription = inscriptionDao.findTopByIdEtudiantOrderByDateDesc(newEtudiant);

                // modifier l'inscrit au nom de l'ancien ??tudiant
                newInscription.setIdEtudiant(oldEtudiant);
                inscriptionDao.save(newInscription);

                // modifier les nouveaux contacte ??tudiant au nom de l'ancien ??tudiant
                List<ContactEtudiant> ce = contactEtudiantDao.findByIdEtudiant(newEtudiant);
                for (ContactEtudiant c : ce) {
                    c.setIdEtudiant(oldEtudiant);
                    contactEtudiantDao.save(c);
                }

                // modifier les nouveaux Diplomes ??tudiant au nom de l'ancien ??tudiant
                List<DiplomeEtudiant> de = diplomeEtudiantDao.findByIdEtudiant(newEtudiant);
                for (DiplomeEtudiant d : de) {
                    d.setIdEtudiant(oldEtudiant);
                    diplomeEtudiantDao.save(d);
                }

                //supprimer le nouvel ??tudiant
                etudiantsDao.delete(newEtudiant);

                //supprimer la nouvelle personne
                personneDao.delete(newPersonne);
                return new ResponseEntity<>(
                        new MessageResponse("Fusion effectu?? avec succ??e!!"), HttpStatus.OK);
            } else {
                //pas d'??tudiants
                return new ResponseEntity<>(
                        new MessageResponse("Ressource Indisponible", 403), HttpStatus.FORBIDDEN);
            }
        } else {
            //pas de personne
            return new ResponseEntity<>(
                    new MessageResponse("Ressource Indisponible", 403), HttpStatus.FORBIDDEN);
        }
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
