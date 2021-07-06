package tn.essatin.erp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tn.essatin.erp.dao.RoleDao;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class FinancierApplication {
    @Autowired
    RoleDao roleDao;

    public static void main(String[] args) {
        SpringApplication.run(FinancierApplication.class, args);
    }

    @PostConstruct
    public void enregistrer() {
        /*ERole[] values = ERole.values();
        roleDao.deleteAll();
        roleDao.flush();
       for(int i=0; i< values.length ;i++){
            Role role = new Role(values[i]);
        //    roleDao.save(role);

        Arrays.stream(values).forEach(eRole -> roleDao.save(new Role(eRole)));*/
        System.out.println("hehehehe");
    }
}
