package kz.kstu.coffee.logic;


import kz.kstu.coffee.dao.UserDAO;
import kz.kstu.coffee.entities.User;

public class LoginLogic {

    public static boolean authentification(String checkLogin, String checkPass) {

        User user = null;
        UserDAO userDAO = new UserDAO();
        user = userDAO.findUserByLoginAndPassword(checkLogin, checkPass);

        return user != null;
    }
}
