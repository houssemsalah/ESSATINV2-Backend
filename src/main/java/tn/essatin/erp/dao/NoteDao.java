package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Matiere;
import tn.essatin.erp.model.Note;


import java.util.Collection;
import java.util.Optional;

@Repository
public interface NoteDao extends JpaRepository<Note, Integer> {
   Optional<Note> findByIdNote(Integer id);
    Collection<Note> findByMatiere(Matiere matiere);
    Collection<Note> findByEtudiant(Integer idEtudiant);
    Collection<Note>  findNotesByMatiere(Matiere matiere);


}