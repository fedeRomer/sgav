package com.sgav.sgav.util;

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
        if(stringDNI.length() >= 7 && stringDNI.length() <9){
            return false;
        }else{
            return true;
        }
    }


}
