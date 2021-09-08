package com.sgav.sgav.login;

import java.io.IOException;
import java.sql.SQLException;


public interface LoginDao {

    void addLogin(Login login);

    Login getLogin(String username, String password) throws IOException, SQLException;

    void updateLogin(Login login);

    void deleteLogin(Login login);

    Login getLoginByUsername(String username) throws IOException;

    Login getLoginByEmail(String email);

}
