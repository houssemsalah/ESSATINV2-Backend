package tn.essatin.erp.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.essatin.erp.Service.CompteService;
import tn.essatin.erp.dao.CompteDao;
import tn.essatin.erp.dao.PersonneDao;
import tn.essatin.erp.model.Compte;
import tn.essatin.erp.model.Personne;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

@Service
public class CompteServiceImpl implements CompteService {
    @Autowired
    CompteDao compteDao;

    @Autowired
    PersonneDao personneDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public boolean existByLogin(String login) {
        return compteDao.existsByLogin(login);
    }

    @Override
    public Compte addCompte(Compte compte) {
        return compteDao.save(compte);
    }


}
