package tn.essatin.erp.dao.financier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.essatin.erp.model.financier.Employer;

@Repository
public interface EmployerDao extends JpaRepository<Employer, Integer> {

}
