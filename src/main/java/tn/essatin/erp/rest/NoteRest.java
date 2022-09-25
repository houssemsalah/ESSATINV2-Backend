package tn.essatin.erp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.MatiereDao;
import tn.essatin.erp.dao.NoteDao;
import tn.essatin.erp.model.Matiere;
import tn.essatin.erp.model.Note;
import tn.essatin.erp.payload.request.GetByIdRequest;
import tn.essatin.erp.payload.response.MessageResponse;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins ="*", maxAge = 3600)
@RestController
@RequestMapping("/api/note/")
public class NoteRest {

    final MatiereDao matiereDao;
    final NoteDao noteDao;
    @Autowired
    public NoteRest(MatiereDao matiereDao, NoteDao noteDao) {
        this.matiereDao = matiereDao;
        this.noteDao = noteDao;
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllNotes() {
        return new ResponseEntity<>(noteDao.findAll(), HttpStatus.OK);

    }

    @GetMapping("/getnotesbymatiere")
    public ResponseEntity<?> getNotesByIdMatiere(  @Valid @RequestBody GetByIdRequest getByIdRequest) {
       Matiere matiere= matiereDao.findByIdMatiere(getByIdRequest.getId());
        return new ResponseEntity<>(noteDao.findNotesByMatiere(matiere), HttpStatus.OK);
    }
    @PostMapping("/addnotes")
    public ResponseEntity<?> addNote(  @Valid @RequestBody List<Note> notes) {
        if (!notes.isEmpty()){
        for (Note note : notes) {

            noteDao.save(note);

        }
        return new ResponseEntity<>("Notes ajotéés avesc succées", HttpStatus.OK);
    }
         else
            return new ResponseEntity<>(
                    new  MessageResponse("Ressources indisponibles", 403), HttpStatus.FORBIDDEN);
    }



    @PutMapping("/modifierNote")
    public ResponseEntity<?> modifierMatiere(@Valid Note note){
       // if (!note.isEmpty()) {
            noteDao.save(note) ;

            return new ResponseEntity<>(
                    new MessageResponse("Matiere modifier avec succée!!"), HttpStatus.OK);
        }
        // else
       //     return new ResponseEntity<>(
      //              new MessageResponse("Ressources indisponibles", 403), HttpStatus.FORBIDDEN);

 //  }

    @DeleteMapping ("/supprimernote")
    public ResponseEntity<?> supprimerNote(@Valid int idNote){
        Optional<Note> note =noteDao.findById(idNote);
        if (!note.isEmpty()) {
            noteDao.deleteById(idNote);

            return new ResponseEntity<>(
                    new MessageResponse("Note supprimée avec succée!!"), HttpStatus.OK);
        } else
            return new ResponseEntity<>(
                    new MessageResponse("Ressources indisponibles", 403), HttpStatus.FORBIDDEN);

    }

}
