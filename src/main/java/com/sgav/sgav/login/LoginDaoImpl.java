package com.sgav.sgav.login;

import com.sgav.sgav.usuario.Usuario;
import com.sgav.sgav.usuario.UsuarioRepository;
import com.sgav.sgav.util.MySQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.*;

@Repository(value = "loginDao")
public class LoginDaoImpl implements LoginDao {

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    private PreparedStatement preparedStatement;
    private CallableStatement callableStatement;
    private String query;
    private Connection connection;
    private Statement statement;

    @Override
    public void addLogin(Login login) {

    }

    @Override
    public Login getLogin(String username, String password) throws IOException, SQLException {

        MySQL mySQL = new MySQL();
        this.connection = mySQL.getConnection();
        query = "select * from login where username=? and password=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet set = preparedStatement.executeQuery();

        Login login = new Login();
        Usuario usuario = new Usuario();

        while (set.next()) {
            login.setId(set.getInt("id"));
            login.setUsername(set.getString("username"));
            login.setPassword(set.getString("password"));
            usuario.setId(set.getInt("usuario_id"));

            if(usuario.getId() == null || usuario.getId()==0){
                login.setUsuarioId(null);
            }else{
                login.setUsuarioId(usuario);
                usuario = usuarioRepository.getById(usuario.getId());
                login.setUsuarioId(usuario);
            }

            login.setLoggedIn(set.getBoolean("logged_in"));
        }
        set.close();
        preparedStatement.close();
        connection.close();
        return login;
    }

    @Override
    public void updateLogin(Login login) {

    }

    @Override
    public void deleteLogin(Login login) {

    }

    @Override
    public Login getLoginByUsername(String username) throws IOException {
        Login l = loginRepository.findLoginByUsername(username);
        return l;
    }

    @Override
    public Login getLoginByEmail(String email) {
        return null;
    }
}
