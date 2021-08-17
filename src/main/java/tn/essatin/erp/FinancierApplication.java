package tn.essatin.erp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tn.essatin.erp.dao.NationaliteDao;
import tn.essatin.erp.dao.RoleDao;
import tn.essatin.erp.dao.TypeIdentificateurDao;
import tn.essatin.erp.model.ERole;
import tn.essatin.erp.model.Nationalite;
import tn.essatin.erp.model.Role;
import tn.essatin.erp.model.TypeIdentificateur;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FinancierApplication {
    final NationaliteDao nationaliteDao;
    final RoleDao roleDao;
    final TypeIdentificateurDao typeIdentificateurDao;

    public FinancierApplication(NationaliteDao nationaliteDao, RoleDao roleDao, TypeIdentificateurDao typeIdentificateurDao) {
        this.nationaliteDao = nationaliteDao;
        this.roleDao = roleDao;
        this.typeIdentificateurDao = typeIdentificateurDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(FinancierApplication.class, args);
    }

    @PostConstruct
    public void enregistrer() {
        String[] nationnalite = {"Tunisienne", "Congolaise", "Camerounaise", "Sud Africaine", "Nigériane", "Ivoirienne", "Française", "Canadienne", "Algérienne", "Libiènne", "Gabonaise", "Comorienne"};
        String[] indentificateurs = {"CIN", "Passport"};
        List<Nationalite> nationaliteList = nationaliteDao.findAll();
        if (nationaliteList.isEmpty()) {
            Arrays.stream(nationnalite).forEach(nat -> nationaliteDao.save(new Nationalite(nat)));
        }
        List<Role> roles = roleDao.findAll();
        if (roles.isEmpty()) {
            Arrays.stream(ERole.values()).forEach(eRole -> roleDao.save(new Role(eRole)));
        }
        List<TypeIdentificateur> typeIdentificateurs = typeIdentificateurDao.findAll();
        if(typeIdentificateurs.isEmpty()){
            Arrays.stream(indentificateurs).forEach(id -> typeIdentificateurDao.save(new TypeIdentificateur(id)));
        }
    }
}
