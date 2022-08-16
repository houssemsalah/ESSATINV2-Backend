package tn.essatin.erp.Service;

import tn.essatin.erp.model.Compte;


public interface CompteService {
    boolean existByLogin(String login);

    Compte addCompte(Compte compte);


}
