package com.sgav.sgav.util;

import java.util.Locale;

public class PermisosUsuarios {

    public static boolean checkUserPermissions(String typeOfUser, String moduleToAccess) {

        moduleToAccess = moduleToAccess.replace("/","");
        moduleToAccess = moduleToAccess.toLowerCase();
        Boolean hasAccess = false;
        if (typeOfUser.equalsIgnoreCase("admin") || typeOfUser.equalsIgnoreCase("propietario")) {
            return true;
        }

        if (typeOfUser.equalsIgnoreCase("clubhouse")) {
            if (moduleToAccess.equalsIgnoreCase("mascotasPerdidas") || moduleToAccess.equalsIgnoreCase("clubhousevisitas")) {
                return true;
            } else {
                return false;
            }
        }

        if (typeOfUser.equalsIgnoreCase("administracion")) {

            if (moduleToAccess.equalsIgnoreCase("sos") || moduleToAccess.equalsIgnoreCase("visitas") || moduleToAccess.equalsIgnoreCase("clubhousevisitas")) {
                return false;
            } else {
                return true;
            }
        }

        if (typeOfUser.equalsIgnoreCase("guardia")) {


            switch (moduleToAccess) {
                case "chat":
                case "modulousuario":
                case "mascotasperdidas":
                case "sosguardia":
                case "visitas":
                    hasAccess = true;
                    break;
                default:
                    hasAccess = false;
                    break;
            }
        }


        return hasAccess;
    }
}
