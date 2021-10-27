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
        return str.matches("[a-zA-Z]+");
    }

    public static boolean isValidStringWithNumbers(String str){
        return str.matches("[a-zA-Z0-9]*");
    }

    public static boolean isValidPhoneNumber(String str) {
        Pattern pattern = Pattern.compile("^\\d{15}$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }


}
