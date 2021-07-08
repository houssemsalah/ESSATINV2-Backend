package tn.essatin.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.TypeIdentificateur;

@Repository
public interface IdentificateurDao extends JpaRepository<TypeIdentificateur, Integer> {
    TypeIdentificateur findByIdTypeidentificateur(String typeIdentificateur);
}
