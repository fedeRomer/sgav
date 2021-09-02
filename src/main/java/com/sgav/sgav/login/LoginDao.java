package com.sgav.sgav.login;

import com.sgav.sgav.login.Login;

import java.io.IOException;
import java.sql.SQLException;


public interface LoginDao {

    void addLogin(Login login);

    Login getLogin(String username, String password) throws IOException, SQLException;

    void updateLogin(Login login);

    void deleteLogin(Login login);

    Login getLoginByUsername(String username);

    Login getLoginByEmail(String email);

}
