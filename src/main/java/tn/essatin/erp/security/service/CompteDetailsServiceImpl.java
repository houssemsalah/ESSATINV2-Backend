package tn.essatin.erp.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.essatin.erp.dao.CompteDao;
import tn.essatin.erp.model.Compte;

@Service
public class CompteDetailsServiceImpl implements UserDetailsService {
    @Autowired
    CompteDao compteDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Compte compte = compteDao.findByLogin(login)
            .orElseThrow(() -> new UsernameNotFoundException("user not found: " + login));
        return CompteDetailsImpl.build(compte);
    }
}
