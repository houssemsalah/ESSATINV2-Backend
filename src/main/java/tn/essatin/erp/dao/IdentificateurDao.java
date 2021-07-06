package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.Identificateur;

@Repository
public interface IdentificateurDao extends JpaRepository<Identificateur, Integer> {
    Identificateur findByTypeIdentificateur(String typeIdentificateur);
}
