package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Formateur;

@Repository
public interface FormateurDao extends JpaRepository<Formateur, Integer> {

}
