
package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Matiere;
import tn.essatin.erp.model.Scolarite.Niveau;


import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface MatiereDao extends JpaRepository<Matiere, Integer> {
    Optional<Matiere> findByNomMatiere(String nomMatiere);
    Matiere findByIdMatiere(int idMatiere);
    Optional<Matiere> findMatiereByNomMatiere(String name);
    Collection<Matiere> findByNiveau(Niveau niveau);


}