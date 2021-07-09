package tn.essatin.erp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.Service.CompteService;
import tn.essatin.erp.dao.*;
import tn.essatin.erp.model.*;
import tn.essatin.erp.payload.request.LoginRequest;
import tn.essatin.erp.payload.request.SignupRequest;
import tn.essatin.erp.payload.request.StudentRequest;
import tn.essatin.erp.payload.response.JwtResponse;
import tn.essatin.erp.payload.response.MessageResponse;
import tn.essatin.erp.security.jwt.JwtUtils;
import tn.essatin.erp.security.service.CompteDetailsImpl;


import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/")
public class AuthRest {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CompteService compteService;
    @Autowired
    RoleDao roleDao;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    PersonneDao personneDao;
    @Autowired
    TypeIdentificateurDao typeIdentificateurDao;
    @Autowired
    NationaliteDao nationaliteDao;
    @Autowired
    EtudiantsDao etudiantsDao;
    @Autowired
    ContactEtudiantDao contactEtudiant;
    @Autowired
    InscriptionDao inscriptionDao;
    @Autowired
    DiplomeEtudiantDao diplomeEtudiantDao;
    @Autowired
    DiplomeDao diplomeDao;
    @Autowired
    EtatInscriptionDao etatInscriptionDao;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getLogin(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        CompteDetailsImpl compteDetails = (CompteDetailsImpl) authentication.getPrincipal();
        List<String> role = compteDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt, compteDetails.getId(), role, compteDetails.getFirstname(), compteDetails.getLastname()));
    }

    @PostMapping("/signup")
    //@PreAuthorize("hasRole('ADMIN')")
    public Object signup(@Valid @RequestBody SignupRequest signupRequest) {
        if (compteService.existByLogin(signupRequest.getLogin())) {
            return ResponseEntity.badRequest().body(new MessageResponse("login indisponible"));
        }
        if (personneDao.existsByNumeroIdentificateur(signupRequest.getNumeroIdentificateur())) {
            return ResponseEntity.badRequest().body(new MessageResponse("numero identification deja existan!!"));
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(signupRequest.getDateDeNaissance(), formatter);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("date de naissance invalide!!"));

        }
        TypeIdentificateur typeIdentificateur = typeIdentificateurDao.findByIdTypeidentificateur(signupRequest.getTypeIdentificateur());
        //identificateurDao.save(identificateur);
        Nationalite nationalite = nationaliteDao.findByLibelle(signupRequest.getNationalite());
        //nationaliteDao.save(nationalite);
        Compte compte = new Compte(signupRequest.getLogin(), passwordEncoder.encode(signupRequest.getPassword()),
            new Personne(signupRequest.getNom(), signupRequest.getPrenom(), signupRequest.getMail(),
                signupRequest.getAdresse(), signupRequest.getTel(),
                localDate, signupRequest.getLieuDeNaissance(),
                    typeIdentificateur,
                signupRequest.getNumeroIdentificateur(),
                signupRequest.getSexe(),
                nationalite));
        Set<String> role = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();
        role.forEach(s -> {
            switch (s) {
                case "admin":
                    Role role1 = roleDao.findByRole(ERole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("erreur role not found!"));
                    roles.add(role1);
                    break;
                case "scolarite":
                    Role role2 = roleDao.findByRole(ERole.ROLE_SCOLARITE)
                        .orElseThrow(() -> new RuntimeException("erreur role not found!"));
                    roles.add(role2);
                    break;
                case "financier":
                    Role role3 = roleDao.findByRole(ERole.ROLE_FINANCIER)
                        .orElseThrow(() -> new RuntimeException("erreur role not found!"));
                    roles.add(role3);
                    break;
                case "examen":
                    Role role4 = roleDao.findByRole(ERole.ROLE_EXAMEN)
                        .orElseThrow(() -> new RuntimeException("erreur role not found!"));
                    roles.add(role4);
                    break;
                case "etudiant":
                    Role role5 = roleDao.findByRole(ERole.ROLE_ETUDIANT)
                        .orElseThrow(() -> new RuntimeException("erreur role not found!"));
                    roles.add(role5);
                    break;
                case "directeur":
                    Role role6 = roleDao.findByRole(ERole.ROLE_DIRECTEUR)
                        .orElseThrow(() -> new RuntimeException("erreur role not found!"));
                    roles.add(role6);
                    break;
                default:

                    break;
            }
        });
        compte.setRoles(roles);
        return compteService.addCompte(compte);
        //return ResponseEntity.ok(new MessageResponse("votre compte est crée avec succée!!"));
    }

    @PostMapping("/addStudent")
    public void addStudent(@Valid @RequestBody StudentRequest studentRequest) {
        Optional<Personne> personne = personneDao.findById(studentRequest.getIdPersonne());
        personne.ifPresent(personne1 -> {
                Etudiants etudiants = etudiantsDao.save(new Etudiants(personne1));
                studentRequest.getDiplomeRequest().forEach(diplomeRequest -> {
                    Diplome diplome = diplomeDao.save(new Diplome(diplomeRequest.getNomDiplome()));
                    diplomeEtudiantDao.save(new DiplomeEtudiant(diplome, etudiants, diplomeRequest.getAnnee(), diplomeRequest.getSpecialite(),
                        diplomeRequest.getNiveau(), diplomeRequest.getStatus(), diplomeRequest.getEtablissement()));
                });
                studentRequest.getContactEtudiants().forEach(contactEtudiant1 -> {
                    contactEtudiant.save(new ContactEtudiant(etudiants, contactEtudiant1.getNumero()
                        , contactEtudiant1.getNom(), contactEtudiant1.getDesignation()));
                });
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date;
                date = LocalDate.parse(studentRequest.getDate(), formatter);
                EtatInscription etatInscription = etatInscriptionDao.findByNom(studentRequest.getEtatInscrit());
                inscriptionDao.save(new Inscription(etudiants, studentRequest.getNumeroInscrit(), date, etatInscription));
            }

        );

    }
}





