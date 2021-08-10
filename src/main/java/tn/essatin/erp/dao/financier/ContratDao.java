package tn.essatin.erp.dao.financier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.financier.Contrat;
import tn.essatin.erp.model.financier.Employer;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContratDao extends JpaRepository<Contrat, Integer> {
    List<Contrat> findContratByEmployer(Employer employer);
    Optional<Contrat> findTopByEmployerOrderByIdDesc(Employer employer);

}
