package tn.essatin.erp.dao.financier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.financier.Employer;

import java.util.Optional;

@Repository
public interface EmployerDao extends JpaRepository<Employer, Integer> {
    Optional<Employer> findByNumeroCNSS(String numCnss);
}
