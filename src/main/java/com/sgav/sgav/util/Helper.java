package com.sgav.sgav.util;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }

    public static boolean isValidName(String str){
        //Pattern pattern = Pattern.compile("^[\\p{L} .'-]+$"); //palabras con espacios
        //Pattern pattern = Pattern.compile("^[ A-Za-z]+$");
        Pattern pattern = Pattern.compile("^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isValidStringWithNumbers(String str){
        Pattern pattern = Pattern.compile("^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$" );
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(String str) {
        Pattern pattern = Pattern.compile("^\\d{9,}");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isValidDNI(int dni) {
        String stringDNI = String.valueOf(dni);
        if(stringDNI.length() >= 7 && stringDNI.length() <= 9){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isValidPatente(String patente){
        patente = patente.toLowerCase();
        Pattern patternViejo = Pattern.compile("[a-z]{3}[\\d]{3}");
        Pattern patternNuevo = Pattern.compile("[a-z]{2}[\\d]{3}[a-z]{2}");

        Matcher matcherPatenteVieja = patternViejo.matcher(patente);
        Matcher matcherPatenteNueva = patternNuevo.matcher(patente);

        if(matcherPatenteVieja.matches()){
            return true;
        }else if(matcherPatenteNueva.matches()){
            return true;
        }else{
            return false;
        }
    }


}
