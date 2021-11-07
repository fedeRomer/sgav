package com.sgav.sgav.util;

import com.sun.org.apache.xpath.internal.operations.Bool;

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



    public static boolean isValidUsername(String str){
        String regex = "^[a-zA-Z0-9._-]{6,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isValidPassword(String str){
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isValidStringWithNumbers(String str){
        String accentedCharacters = "àèìòùÀÈÌÒÙáéíóúýÁÉÍÓÚÝâêîôûÂÊÎÔÛãñõÃÑÕäëïöüÿÄËÏÖÜŸçÇßØøÅåÆæœ";
        Pattern pattern = Pattern.compile("^[A-Za-z"+accentedCharacters+"0-9 _,;/-]*[A-Za-z"+accentedCharacters+"0-9][A-Za-z"+accentedCharacters+"0-9 _]*$" );
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isValidEmail(String str){

        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String regexOptional = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        Boolean firstFilter = matcher.matches();

        pattern = Pattern.compile(regexOptional);
        matcher = pattern.matcher(str);

        Boolean secondFilter = matcher.matches();
        if( firstFilter && secondFilter){
            return true;
        }else{
            return false;
        }
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
