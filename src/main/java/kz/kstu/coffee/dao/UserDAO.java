package kz.kstu.coffee.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import kz.kstu.coffee.entities.User;
import kz.kstu.coffee.pool.ConnectionPool;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class UserDAO extends AbstractDAO<User> {

    private Logger log;
    public static final String SQL_SELECT_ALL_USERS = "SELECT * FROM user";
    public static final String SQL_FIND_USER_BY_LOGIN_AND_PASSWORD = ""
            + " SELECT * FROM user "
            + " WHERE Login = ? AND Password = ?";

    @Override
    public List<User> findAll() {
        log = Logger.getRootLogger();
        BasicConfigurator.configure();

        List<User> users = null;
        Connection cn = null;
        Statement st = null;
        try {
            cn = ConnectionPool.getConnection();
            st = cn.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_USERS);
            users = new ArrayList<User>();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("UserID"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setEmailAddress(resultSet.getString("EmailAddress"));
                user.setLogin(resultSet.getString("Login"));
                user.setPassword(resultSet.getString("Password"));
                user.setRole(resultSet.getString("Role"));
                users.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            log.error("SQL error: " + ex.toString());
        } finally {
            close(st);
            close(cn);
        }

        return users;
    }

    public User findUserByLoginAndPassword(String login, String pass) {
        log = Logger.getRootLogger();
        BasicConfigurator.configure();
        User user = null;
        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = ConnectionPool.getConnection();
            ps = cn.prepareStatement(SQL_FIND_USER_BY_LOGIN_AND_PASSWORD);
            ps.setString(1, login);
            ps.setString(2, pass);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("UserID"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setEmailAddress(resultSet.getString("EmailAddress"));
                user.setLogin(resultSet.getString("Login"));
                user.setPassword(resultSet.getString("Password"));
                user.setRole(resultSet.getString("Role"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.error("SQL error: " + ex.toString());
        } finally {
            close(ps);
            close(cn);
        }

        return user;
    }

    @Override
    public User findEntityById(int id) {
        return null;
    }

    @Override
    public int findEntityByID(User user) {
        return 0;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public boolean create(User user) {
        return false;
    }

    @Override
    public User update(User user) {
        return null;
    }
}
