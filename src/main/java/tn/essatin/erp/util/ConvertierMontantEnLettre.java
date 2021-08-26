package tn.essatin.erp.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Pattern;

import com.ibm.icu.text.RuleBasedNumberFormat;

public class ConvertierMontantEnLettre {
    public static String enTouteLettre(double montant) {
        int entier=(int)montant;
        int decimal = (int) ((Math.round((montant-entier)*1000)));
        BigDecimal entier1 = new BigDecimal(entier);
        BigDecimal decimal1 = new BigDecimal(decimal);
        RuleBasedNumberFormat formatter =  new RuleBasedNumberFormat(RuleBasedNumberFormat.SPELLOUT);
        String entierS = upperCaseFirst(formatter.format(entier1));
        String decimalS = upperCaseFirst(formatter.format(decimal1));

        entierS += (entier == 1 || entier == 0) ? " Dinar" : " Dinars" ;
        decimalS += (decimal == 1) ? " Millime" : " Millimes" ;
        return ""+((entier == 0 && decimal != 0)?"":entierS)+((entier>0 && decimal>0)?" et ":"")+((decimal==0)?"":decimalS);
    }

    private static String upperCaseFirst(String val) {
        char[] arr = val.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        for (int i=1;i<arr.length;i++){
            if(arr[i-1]==' '||arr[i-1]=='-')
                arr[i] = Character.toUpperCase(arr[i]);
        }
        return new String(arr);
    }
}