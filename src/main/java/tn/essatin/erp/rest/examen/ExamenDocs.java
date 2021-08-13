package tn.essatin.erp.rest.examen;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.PersonneDao;
import tn.essatin.erp.dao.scolarite.EnregistrementDao;
import tn.essatin.erp.model.Scolarite.Enregistrement;
import tn.essatin.erp.payload.request.examen.AttestationDeReussiteRequest;
import tn.essatin.erp.payload.request.examen.DemandeDeStageRequest;
import tn.essatin.erp.payload.response.MessageResponse;
import tn.essatin.erp.util.DocumentGenerators.AttestationDeReussite;
import tn.essatin.erp.util.DocumentGenerators.FeuilleDEvaluatioDunPFE;
import tn.essatin.erp.util.DocumentGenerators.FeuilleDeDemandeDeStage;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/examdocs/")
public class ExamenDocs {

    final EnregistrementDao enregistrementDao;
    final PersonneDao personneDao;

    public ExamenDocs(EnregistrementDao enregistrementDao,PersonneDao personneDao) {

        this.enregistrementDao = enregistrementDao;
        this.personneDao=personneDao;
    }

    @PostMapping("/getdemandedestage")
    public ResponseEntity<?> getFeuilleDeDemandeDeStage
            (@Valid @RequestBody DemandeDeStageRequest demandeDeStageRequest) {
        Optional<Enregistrement> etudiant = enregistrementDao.findById(demandeDeStageRequest.getIdenregistrement());
        if (etudiant.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Enregistrement introuvable", 403), HttpStatus.FORBIDDEN);
        }
        ByteArrayOutputStream os = FeuilleDeDemandeDeStage.createDoc(etudiant.get(), demandeDeStageRequest.getNomSociete(), demandeDeStageRequest.getNumCase(),demandeDeStageRequest.getDesignantionEntreprise());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
        ByteArrayResource resource = new ByteArrayResource(os.toByteArray());
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @PostMapping("/getattestationdereussite")
    public ResponseEntity<?> getAttestationDeReussite
            (@Valid @RequestBody AttestationDeReussiteRequest attetationDeReussiteRequest) {
        Optional<Enregistrement> etudiant = enregistrementDao.findById(attetationDeReussiteRequest.getIdEnregitrement());
        if (etudiant.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse("Enregistrement introuvable", 403), HttpStatus.FORBIDDEN);
        }
        ByteArrayOutputStream os = AttestationDeReussite.createDoc(etudiant.get(), attetationDeReussiteRequest.getTypeSession(), attetationDeReussiteRequest.getNumMention() );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
        ByteArrayResource resource = new ByteArrayResource(os.toByteArray());
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }





}
