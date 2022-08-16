package tn.essatin.erp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import tn.essatin.erp.dao.MatiereDao;
import tn.essatin.erp.dao.NoteDao;
import tn.essatin.erp.model.Matiere;
import tn.essatin.erp.model.Note;
import tn.essatin.erp.payload.response.MessageResponse;

import javax.validation.Valid;
import java.util.Optional;

public class NoteRest {

    final MatiereDao matiereDao;
    final NoteDao noteDao;
    @Autowired
    public NoteRest(MatiereDao matiereDao, NoteDao noteDao) {
        this.matiereDao = matiereDao;
        this.noteDao = noteDao;
    }



    @GetMapping("/getnotesbymatiere")
    public ResponseEntity<?> getNotesByMatiere(Matiere idMatiere) {
        return new ResponseEntity<>(noteDao.findNotesByMatiere( idMatiere), HttpStatus.OK);
    }
    @PostMapping("/addnote")
    public ResponseEntity<?> addNote(@Valid Note note){

            noteDao.save(note) ;

            return new ResponseEntity<>(
                    new MessageResponse("Note ajouter avec succée!!"), HttpStatus.OK);
        }
        // else
  //          return new ResponseEntity<>();
//                    new  MessageResponse("Ressources indisponibles", 403), HttpStatus.FORBIDDEN
    //}



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
