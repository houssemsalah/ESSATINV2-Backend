package tn.essatin.erp.util;


import tn.essatin.erp.model.Scolarite.Cycle;
import tn.essatin.erp.model.Scolarite.Niveau;
import tn.essatin.erp.model.Scolarite.Parcours;
import tn.essatin.erp.model.Scolarite.Specialite;

public class GenericFunctions {

    public static String nomNiveaux(Niveau niveau) {
        String niveauString="";
        Parcours parcours = niveau.getParcours();
        Specialite specialite = parcours.getSpecialite();
        Cycle cycle = specialite.getCycle();
        String niveauxC = niveau.getDesignation();
        String designationNiveaux = cycle.getDescription() + " " + parcours.getDesignation();
        niveauString+=niveauxC+" ";
        if (Integer.parseInt(niveauxC) == 1) {
            niveauString+="ère ";
        } else {
            niveauString+="ème ";
        }
        niveauString+=" Année ";
        niveauString+=designationNiveaux;
        return niveauString;
    }
}
